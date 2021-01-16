package info.mpaczes.usersmanagement.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.mpaczes.usersmanagement.domain.AuthenticationInfo;
import info.mpaczes.usersmanagement.domain.User;
import info.mpaczes.usersmanagement.domain.UserStatus;
import info.mpaczes.usersmanagement.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	public static final String AUTHENTICATION_ID = "authenticationId";
	
	private UserService userService;
	
	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "/login/{userName}/{password}")
	public AuthenticationInfo login(@PathVariable String userName, @PathVariable String password, HttpServletResponse response) {
		User user = this.userService.isUserPresentInDb(userName, password);
	
		if (user != null) {
			int randomNumber = this.userService.generateRandomNumber();
			
			user.setAuthenticationId(randomNumber);
			this.userService.saveUser(user);
			
			Cookie cookie = new Cookie(AUTHENTICATION_ID, Integer.toString(randomNumber));
			
			response.addCookie(cookie);
			
			return new AuthenticationInfo(userName, UserStatus.AUTHENTICATED.getUserStatuAsString());
		}
		
		return new AuthenticationInfo(userName, UserStatus.UNAUTHORIZED.getUserStatuAsString());
	}
	
	@GetMapping(value = "/resetPassword/{newPassword}")
	public AuthenticationInfo resetPassword(@PathVariable String newPassword, @CookieValue(value = AUTHENTICATION_ID) Cookie cookie, HttpServletResponse response) {
		int authenticationId = Integer.parseInt(cookie.getValue());
		
		User user = this.userService.findByAuthenticationId(authenticationId);
		
		if (user != null) {
			user.setPassword(newPassword);
			
			int randomNumber = this.userService.generateRandomNumber();
			
			user.setAuthenticationId(randomNumber);
			
			this.userService.saveUser(user);
			
			cookie = new Cookie(AUTHENTICATION_ID, Integer.toString(randomNumber));
			
			response.addCookie(cookie);
			
			return new AuthenticationInfo(user.getUserName(), UserStatus.PASSWORD_RESET.getUserStatuAsString());
		}
		
		return new AuthenticationInfo(UserStatus.UNAUTHORIZED.getUserStatuAsString());
	}
	
	@GetMapping(value = "/forgotPassword/{userName}")
	public AuthenticationInfo forgotPassword(@PathVariable String userName, HttpServletResponse response) {
		User foundUser = this.userService.findByUserName(userName);
		
		if (foundUser != null && foundUser.getAuthenticationId() > 0) {
			int randomNumber = this.userService.generateRandomNumber();
			
			foundUser.setAuthenticationId(randomNumber);
			
			this.userService.saveUser(foundUser);
			
			Cookie cookie = new Cookie(AUTHENTICATION_ID, Integer.toString(randomNumber));
			
			response.addCookie(cookie);
			
			return new AuthenticationInfo(foundUser.getUserName(), UserStatus.FORGOTTEN_PASWORD.getUserStatuAsString(), foundUser.getPassword());
		}
		
		return new AuthenticationInfo(UserStatus.UNAUTHORIZED.getUserStatuAsString());
	}
	
}
