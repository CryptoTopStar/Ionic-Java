package com.smart.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smart.listener.JPAListener;

import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NamedQueries({
	@NamedQuery(name="findByName",query="select u from User u where username=:userName"),
	@NamedQuery(name="findByEmail",query="select u from User u where email=:email"),
	@NamedQuery(name="enableUser", query="update User set enabled= :enabled where id= :userId"),
	@NamedQuery(name="deleteUser", query="update User set deleted= :deleted where id= :userId")
})

@Entity
@Table(name="user")
@EntityListeners(JPAListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User  implements UserDetails{
	
	
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@Column(name="username")
	private String username;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "locked")
	private boolean locked;
	
	@Column(name = "accountNonExpired")
	private boolean accountNonExpired;
	
	@Column(name = "credentialsNonExpired")
	private boolean credentialsNonExpired;
	
	@Column(name = "deleted")
	private boolean deleted;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	
	
	  @OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	  @JoinColumn(name="activation_detail_id")
	  private UserActivationDetail activationDetail;
	 
	  @OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	  @JoinColumn(name="profile_image")
	  private FileUpload profileImage;
	  
	  @OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	  @JoinColumn(name="address")
	  private Address address;
	  
	  @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	  private Set<AccountDetails> accountDetails;
	  
	  @OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	  private Wallet wallet;
	  
	 @JsonIgnore
	public FileUpload getProfileImage() {
		return profileImage;
	}

	@JsonProperty
	public void setProfileImage(FileUpload profileImage) {
		this.profileImage = profileImage;
	}
	
	public String getProfilePic() {
		return this.profileImage != null ? this.profileImage.getPath() : null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		 if(!Collections.isEmpty(this.getRoles())) {
			 this.getRoles().forEach(role -> {
					authorities.add(new SimpleGrantedAuthority(role.getName()));
			 });
		 }
		 return authorities;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.isLocked();
	}
	
	
	@JsonIgnore 
	public UserActivationDetail getActivationDetail() { 
		  return activationDetail; 
	  }
	  
	  @JsonProperty 
	  public void setActivationDetail(UserActivationDetail activationDetail) { 
		  this.activationDetail = activationDetail; 
	  }
	 
 
    
}
