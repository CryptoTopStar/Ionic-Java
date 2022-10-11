package com.smart.security;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Resource(name = "userService")
    private UserDetailsService userDetailsService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	/* for login using sprint form based authentication */
    
   /* 
      @Autowired
    private DataSource dataSource;
    
     @Value("${spring.queries.users-query}")
    private String usersQuery;
    
    @Value("${spring.queries.roles-query}")
    private String rolesQuery;*/
    
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    
    
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    
    
    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }
    
    
   
   
    /* for login using sprint form based authentication */
    
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
    	System.out.println("auth is::"+auth.toString());
    	// auth.userDetailsService(new UserServiceImpl());
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(encoder());
    }
    
     @Override
    protected void configure(HttpSecurity http) throws Exception {
				/*http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/token/*", "/signup").permitAll()
                 .antMatchers("/user/add").permitAll()  // uncomment when adding first user only
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/profile")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    */
    

   
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http
    	.cors()
    	.and().csrf().disable().
         authorizeRequests()
        .antMatchers("/token/*", 
        		"/user/signup",
        		"/user/activation",
        		"/user/forgetPassword",
        		"/user/resetPassword*",
        		"/actuator/*",
        		"/unauth/**"
         ).permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	http
        .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//    	.addFilterBefore(corsFilter(), SessionManagementFilter.class);
    	
     }
    
    
   
    /*CorsFilter corsFilter() {
    	CorsFilter filter = new CorsFilter();
        return filter;
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
        .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","token/**");
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
   
    
    

}
