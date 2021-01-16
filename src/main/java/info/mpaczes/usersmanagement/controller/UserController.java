package info.mpaczes.usersmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.mpaczes.usersmanagement.domain.EnumPermission;
import info.mpaczes.usersmanagement.domain.EnumYesNo;
import info.mpaczes.usersmanagement.domain.User;
import info.mpaczes.usersmanagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<User> findAllUsers(@CookieValue(value = AuthController.AUTHENTICATION_ID) Cookie cookie) {
		int authenticationId = Integer.parseInt(cookie.getValue());
		
		User user = this.userService.findByAuthenticationId(authenticationId);
		
		boolean hasPermission = this.userService.haUserPermision(user, EnumPermission.LIST_USERS);
		
		if (user != null && ((EnumYesNo.YES).equals(user.getIsAdmin()) || hasPermission)) {
			return this.userService.findAllUsers();
		} else {
			return new ArrayList<User>();
		}
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable long userId, @CookieValue(value = AuthController.AUTHENTICATION_ID) Cookie cookie) {
		int authenticationId = Integer.parseInt(cookie.getValue());
		
		User user = this.userService.findByAuthenticationId(authenticationId);
		
		boolean hasPermission = this.userService.haUserPermision(user, EnumPermission.DELETE_USER);
		
		if (user != null && ((EnumYesNo.YES).equals(user.getIsAdmin()) || hasPermission)) {
			this.userService.deleteUser(userId);
		}
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User inputUser, @CookieValue(value = AuthController.AUTHENTICATION_ID) Cookie cookie) {		
		int authenticationId = Integer.parseInt(cookie.getValue());
		
		User user = this.userService.findByAuthenticationId(authenticationId);
		
		boolean hasPermission = this.userService.haUserPermision(user, EnumPermission.CREATE_UER);
		
		if (user != null && ((EnumYesNo.YES).equals(user.getIsAdmin()) || hasPermission)) {
			return this.userService.saveUser(inputUser);
		}
		
		return new User();
	}
	
	@PutMapping("/users/{userId}")
	public User updateUser(@RequestBody User newUser, @PathVariable long userId, @CookieValue(value = AuthController.AUTHENTICATION_ID) Cookie cookie) {
		int authenticationId = Integer.parseInt(cookie.getValue());
		
		User user = this.userService.findByAuthenticationId(authenticationId);
		
		boolean hasPermission = this.userService.haUserPermision(user, EnumPermission.UPDATE_USER);
		
		if (user != null && ((EnumYesNo.YES).equals(user.getIsAdmin()) || hasPermission)) {
			Optional<User> oldUser = this.userService.findById(userId);
			
			if (oldUser.isPresent()) {
				User userToSave = oldUser.get();
				
				userToSave.setUserName(newUser.getUserName());
				userToSave.setRole(newUser.getRole());
				
				return this.userService.saveUser(userToSave);
			} else {
				newUser.setId(userId);
				
				return this.userService.saveUser(newUser);
			}
		}
		
		return new User();
	}
	
}
