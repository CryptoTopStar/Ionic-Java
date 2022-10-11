package com.smart.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.model.LoginUser;
import com.smart.model.Role;
import com.smart.model.User;
import com.smart.service.IRoleService;
import com.smart.service.IUserService;
import com.smart.util.Util;
import com.smart.util.UtilMessages;
import com.smart.util.model.SearchResponse;

import freemarker.template.Configuration;



@RestController
@CrossOrigin("*")
@RequestMapping(value = "/user")
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	 
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IRoleService roleService;
	
	@GetMapping(value={"/findAll"})
	public ResponseEntity<List<User>> findAll() {
		LOGGER.info("inside @class UserController @method findAll entry..");
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
		
	}
	
	@GetMapping(value={"/findAllRoles"})
	public List<Role>  findAllRoles() {
		LOGGER.info("inside @class UserController @method findAllRoles entry..");
		return roleService.findAllRoles();
		
	}
	
	@PostMapping("/addUser")
	public String createNewUser(@RequestBody User user, BindingResult bindingResult) {
		LOGGER.info("inside @class UserController @method signup entry..");
		if (bindingResult.hasErrors()) {
        	return Util.getMessage(UtilMessages.USER_CREATION_ERROR, false);
            
        } else {
            return userService.save(user);
        }
       
    }
	
	@GetMapping(value={"/search"})
	public SearchResponse search(@RequestParam(value="q") String q,@RequestParam(value="sortBy",defaultValue="id") String sortBy,@RequestParam(value="sortType", defaultValue="desc") String sortType,@RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="limit",defaultValue="5") int limit) {
		LOGGER.info("inside @class UserController @method search entry..");
		return userService.search(q,sortBy,sortType,page,limit);
		
	}

	@PostMapping("/enable/{userId}/{enabled}")
	public String createNewUser(@PathVariable("userId") Long userId, @PathVariable("enabled") Boolean enabled) {
		LOGGER.info("inside @class UserController @method signup entry..");
		return userService.enableUser(userId, enabled);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable("userId") Long userId) {
		LOGGER.info("inside @class UserController @method signup entry..");
		return userService.deleteUser(userId);
	}
	
	@PostMapping("/signup")
	public String signup(@RequestBody User user, BindingResult bindingResult) {
		LOGGER.info("inside @class UserController @method signup entry..");
		if (bindingResult.hasErrors()) {
        	return Util.getMessage(UtilMessages.USER_CREATION_ERROR, false);
        } else {
            return userService.signup(user);
        }
       
    }
	
	
	@PutMapping("/updateProfile")
	public String updateProfile(@RequestBody User user, BindingResult bindingResult) {
		LOGGER.info("inside @class UserController @method updateProfile entry..");
		if (bindingResult.hasErrors()) {
        	return Util.getMessage(UtilMessages.USER_CREATION_ERROR, false);
        } else {
            return userService.updateProfile(user);
        }
       
    }
	
	@PutMapping("/updatePassword")
	public String updatePassword(@RequestBody LoginUser user, BindingResult bindingResult) {
		LOGGER.info("inside @class UserController @method updatePassword entry..");
		if (bindingResult.hasErrors()) {
        	return Util.getMessage(UtilMessages.USER_CREATION_ERROR, false);
        } else {
            return userService.updatePassword(user);
        }
       
    }
	
	@GetMapping("/activation")
	
	public String activateUser(@RequestParam(value="token") String token) {
		LOGGER.info("inside @class UserController @method activation entry..");
		return userService.activateUser(token);
        
    }
	
	
	@PutMapping("/forgetPassword")
	public String forgetPassword(@RequestBody LoginUser user) {
		LOGGER.info("inside @class UserController @method forgetPassword entry..");
		String msg = userService.forgetPassword(user);
        
        return msg != null ? 
        		Util.getMessage(msg, false) : 
        			Util.getMessage(UtilMessages.PASSWORD_RESET_LINK_SEND_SUCCESS, true);
        
    }
	
	@PutMapping("/resetPassword")
	public String resetPassword(@RequestBody LoginUser user,@RequestParam(value="token") String token) {
		LOGGER.info("inside @class UserController @method updatePassword entry..");
		String msg = userService.resetPassword(user,token);
		return msg != null ? 
				Util.getMessage(msg, false) : 
					Util.getMessage(UtilMessages.PASSWORD_RESET_SUCCESS, true);
    }
	
	
	
	
	
	
	
}

