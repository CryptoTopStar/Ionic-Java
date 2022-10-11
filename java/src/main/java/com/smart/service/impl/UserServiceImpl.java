package com.smart.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.IFileUploadDao;
import com.smart.dao.IRoleDao;
import com.smart.dao.IUserActivationDetailDao;
import com.smart.dao.IUserDao;
import com.smart.exception.AuthException;
import com.smart.exception.RecordNotFoundException;
import com.smart.model.FileUpload;
import com.smart.model.LoginUser;
import com.smart.model.Role;
import com.smart.model.User;
import com.smart.model.UserActivationDetail;
import com.smart.service.IFileStorageService;
import com.smart.service.IMailService;
import com.smart.service.IUserService;
import com.smart.util.Constants;
import com.smart.util.Util;
import com.smart.util.UtilMessages;
import com.smart.util.model.SearchResponse;

@Service(value = "userService")
@CacheConfig(cacheNames = "User")
public class UserServiceImpl implements IUserService,UserDetailsService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final String LOGGER_PRE = "inside @calss " + this.getClass().getName();
	
	@Autowired
	private IUserDao dao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	IMailService mailService;
	
	@Autowired
	IUserActivationDetailDao userActivationDetailDao;
	
	@Autowired
    private IFileStorageService fileStorageService;
	
	@Autowired
	private IFileUploadDao fileUploadDao;
	
	 
	
	
	
	@Cacheable
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public UserDetails loadUserByUsername(String username) throws LockedException {
		LOGGER.info("in load user by name:: " + username);
		User user = dao.findByUsername(username);
		
		if(user == null) {
			LOGGER.error("Invalid username or password.");
			throw new AuthException("Invalid username or password.");
		}
		
		return user;
	}
	
	
	
	
	@Override
	public User findOne(String username) {
		return dao.findByUsername(username);
	}
	
	
	
	private User setUserInitialDetails(User user) {
		user.setEnabled(false);
		user.setAccountNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.setLocked(false);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return user;
	}
	
	
	@Override
	public String save(User user) {
		try {
			 
			User userExists = dao.findByEmail(user.getEmail());
	        if (userExists != null) {
	            return Util.getMessage(UtilMessages.USER_EMAIL_EXIST, false);
	        }
			Role role = null;
			User oldUser = dao.findByUsername(user.getUsername());
			if( !user.getRoles().isEmpty() ) { 
				role = roleDao.findById(user.getRoles().iterator().next().getId());
				if(user.getUsername() != null && oldUser == null) {
					Set<Role> roles = new HashSet<Role>();
					roles.add(role);
					user.setRoles(roles);
					setUserInitialDetails(user);
					dao.save(user);
				}else {
					if(oldUser.isDeleted()) {
						oldUser.setDeleted(false);
						dao.save(oldUser);
					}else {
						return Util.getMessage(UtilMessages.USERNAME_ALREADY_EXIST,false);
					}
					
				}

			}else {
				return Util.getMessage(UtilMessages.USER_ROLE_NOT_EXIST,false);
			}
			
			
			 
		}catch(Exception ex) {
			LOGGER.error("Exception while creating user::"+ex.getLocalizedMessage());
			return Util.getMessage("failed",false);
		}
		return Util.getMessage(UtilMessages.USER_CREATION_SUCCESS,true);
	}

	

	

	@Override
	@Cacheable
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	@Cacheable
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public SearchResponse search(String string, String sortBy, String sortType,int page,int limit) {
		return dao.search(string, page, limit, sortBy,sortType);
		
	}




	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public User findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public String enableUser(Long userId, Boolean enabled) {
		
		try{
			 String result = dao.enableUser(userId, enabled);
			 if(result == null){
				return Util.getMessage(UtilMessages.SOMETHING_WENT_WRONG,false);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return Util.getMessage(UtilMessages.SOMETHING_WENT_WRONG,false);
		}
		return Util.getMessage(enabled ? UtilMessages.USER_ENABLED_SUCCESS : UtilMessages.USER_DISABLED_SUCCESS,true);
	}
	
	@Override
	public String deleteUser(Long userId) {
		boolean deleted = false;
		LOGGER.info(LOGGER_PRE + " delete userid {}",userId);
		try { 
				deleted = dao.deleteUser(userId);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return Util.getMessage(deleted ? UtilMessages.USER_DELETED_SUCCESS : UtilMessages.SOMETHING_WENT_WRONG,true);
	}




	@Override
	@Transactional
	public String signup(User user) {
		try {
			
			User userExists = dao.findByEmail(user.getEmail());
	        if (userExists != null) {
	            return Util.getMessage(UtilMessages.USER_EMAIL_EXIST, false);
	        }
            User oldUser = dao.findByUsername(user.getUsername());
			Role role = roleDao.findByName(Constants.ROLE_USER);
			
				if(user.getUsername() != null && oldUser == null) {
					Set<Role> roles = new HashSet<Role>();
					roles.add(role);
					user.setRoles(roles);
					dao.save(user);
					setUserInitialDetails(user);
					setActivationDetails(user);
					dao.save(user);
				}else {
					if(!oldUser.isDeleted()) {
						return Util.getMessage(UtilMessages.USERNAME_ALREADY_EXIST,false);
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			LOGGER.error("Exception while creating user::"+ex.getLocalizedMessage());
			return Util.getMessage("failed",false);
		}
		new Thread(() -> 
			{
				try {
					System.out.println("sending user mail....");
					LOGGER.info("sending activation  mail to {}....",user.getUsername());
					mailService.sendActivationEmail(user);
				} catch (Exception ex) {
					LOGGER.error("Exception while sending activation mail "+ex.getLocalizedMessage());
					ex.printStackTrace();
				}
			}
		).start();
		return Util.getMessage(UtilMessages.USER_SIGNUP_SUCCESS,true);

	}




	private User setActivationDetails(User user) {
		Date expireOn = new Date(System.currentTimeMillis() + Constants.USER_TOKEN_VALIDITY * 1000);
		String token = bCryptPasswordEncoder.encode(Util.generateRandom(Constants.USER_ACTIVATION_TOKEN_LENGHT));
		UserActivationDetail uad = new UserActivationDetail();
		uad.setActivationToken(token);
		uad.setTokenExpireOn(expireOn);
		uad.setValid(true);
		uad.setUserId(user.getId());
		user.setActivationDetail(uad);
		return user;
		
	}

	@Override
	@Transactional
	public String updateProfile(User user) {
		LOGGER.info(LOGGER_PRE +" entry..." );
		try {
			 User u = dao.findById(user.getId());
			 if(u != null && !u.isDeleted() && u.isEnabled() && !u.isLocked()) {
				 u.setLastName(user.getLastName());
				 u.setFirstName(user.getFirstName());
				 u.setMobile(user.getMobile());
				 u.setUsername(user.getUsername());
				 dao.save(u);
			 }
			 
		}catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception while update updateProfile::"+ex.getLocalizedMessage());
			return Util.getMessage("failed",false);
		}
		
		return Util.getMessage(UtilMessages.USER_PROFILE_UPDATE_SUCCESS,true);
	}




	@Override
	@Transactional
	public String updatePassword(LoginUser user) {
		LOGGER.info(LOGGER_PRE +" @method updatePassword entry..." );
		try {
			 User u = dao.findById(user.getUserId());
			 if(u != null && !u.isDeleted() && u.isEnabled() && !u.isLocked()) {
				 if(user.getPassword() != user.getOldPassword()) {
					 if(bCryptPasswordEncoder.matches(user.getOldPassword(), u.getPassword())) {
						 u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
						 dao.save(u);
					 }else {
						return Util.getMessage(UtilMessages.OLD_PASSWORD_NOT_MATCH_ERROR,false); 
					 }
					
				 }else {
					 return Util.getMessage(UtilMessages.OLD_NEW_PASSWORD_SAME,false); 
				 }
			}
			 
		}catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception while update updateProfile::"+ex.getLocalizedMessage());
			return Util.getMessage("failed",false);
		}
		
		return Util.getMessage(UtilMessages.PASSWORD_UPDATED_SUCCESS,true);
	}




	@Override
	@Transactional
	public String activateUser(String token) {
		UserActivationDetail uad = userActivationDetailDao.findByToken(token);
		Date currentDate = new Date(System.currentTimeMillis()*1000);
		String msg = null;
		if(uad != null) {
			if(uad.getTokenExpireOn().before(currentDate)) {
				User user = dao.findById(uad.getUserId());
				user.setEnabled(true);
				dao.save(user);
				uad.setUsedOn(new Date());
				uad.setValid(false);
				userActivationDetailDao.save(uad);
				
			}else {
				msg = UtilMessages.ACTIVATION_TOKEN_EXPIRED_ERROR;
			}
		}else {
			msg = UtilMessages.INVALID_ACTIVATION_TOKEN_ERROR;
		} 
		
		
		return  mailService.getactivateUserResponse(msg);
	}




	@Override
	@Transactional
	public String forgetPassword(LoginUser user) {
		String msg = null;
		try {
				User oldUser = dao.findByEmail(user.getEmail());
				if(oldUser != null) {
					UserActivationDetail uad = userActivationDetailDao.findByUserId(oldUser.getId());
					if(uad !=null) {
						Date expireOn = new Date(System.currentTimeMillis() + Constants.USER_TOKEN_VALIDITY * 1000);
						try {
							user.setUsername(oldUser.getUsername());
							LOGGER.info("sending user mail for forget password....");
							mailService.sendForgetPasswordEmail(uad,user);
						} catch (Exception ex) {
							LOGGER.error("Exception while sending forget passwrod  mail "+ex.getLocalizedMessage());
							ex.printStackTrace();
						}
						uad.setValid(true);
						uad.setTokenExpireOn(expireOn);
						userActivationDetailDao.save(uad);
					}else {
						LOGGER.error("error while forget password UserActivationDetail not found::"+oldUser.getId());
					}
				}else {
					msg = UtilMessages.USER_EMAIL_NOT_EXIST;
				}
		}catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception while update updateProfile::"+ex.getLocalizedMessage());
			msg = UtilMessages.SOMETHING_WENT_WRONG;
		}
		return msg;
	}




	@Override
	@Transactional
	public String resetPassword(LoginUser user, String token) {
		String msg = null;
		try {
			UserActivationDetail uad = userActivationDetailDao.findByToken(token);
			Date currentDate = new Date(System.currentTimeMillis()*1000);
			if(uad != null && uad.isValid() && uad.getTokenExpireOn().before(currentDate)) {
				User oldUser = dao.findById(uad.getUserId());
				if(user.getPassword() != null) {
					oldUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
					dao.save(oldUser);
				}
				uad.setUsedOn(new Date());
				uad.setValid(false);
				userActivationDetailDao.save(uad);
				
			}else {
				msg = UtilMessages.FORGET_PASSWORD_TOKEN_EXPIRED_ERROR;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception while update updateProfile::"+ex.getLocalizedMessage());
		}
		return msg;
	}




	@Override
	@Transactional
	public FileUpload uploadUserImage(MultipartFile file,Long userId) {
		User user = null;
		
		try {
			user = dao.findById(userId);
			fileStorageService.ensureDir(userId.toString());
		}catch (Exception e) {
			throw new RecordNotFoundException("user not found",e);
		}
		
		
		String fileName = fileStorageService.storeFile(file,userId.toString(),userId.toString());
		/*
		 * String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		 * .path("/downloadUserProfile/") .path(fileName) .toUriString();
		 */
        String fileDownloadUri = Constants.UPLOAD_PATH + "/" + userId.toString() + "/"+ fileName;
               

        FileUpload fu =   new FileUpload(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
        if(user != null) {
        	user.setProfileImage(fu);
        	dao.save(user);
        }
        return fileUploadDao.save(fu);
		}
        
	}
