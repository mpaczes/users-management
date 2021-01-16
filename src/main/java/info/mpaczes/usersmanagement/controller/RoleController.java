package info.mpaczes.usersmanagement.controller;

import java.util.ArrayList;
import java.util.List;

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

import info.mpaczes.usersmanagement.domain.EnumYesNo;
import info.mpaczes.usersmanagement.domain.Role;
import info.mpaczes.usersmanagement.domain.User;
import info.mpaczes.usersmanagement.service.RoleService;
import info.mpaczes.usersmanagement.service.UserService;

@RestController
@RequestMapping("/role")
public class RoleController {

	private RoleService roleService;
	
	private UserService userService;
	
	@Autowired
	public RoleController(RoleService roleService, UserService userService) {
		this.roleService = roleService;
		this.userService = userService;
	}
	
	@GetMapping(value = "/roles")
	public List<Role> findAllRoles(@CookieValue(value = AuthController.AUTHENTICATION_ID) Cookie cookie) {
		int authenticationId = Integer.parseInt(cookie.getValue());
		
		User user = this.userService.findByAuthenticationId(authenticationId);
		
		if (user != null && (EnumYesNo.YES).equals(user.getIsAdmin())) {
			return this.roleService.findAllRoles();
		} else {
			return new ArrayList<Role>();
		}
	}
	
	@PostMapping(value = "/roles")
	public Role createRole(@RequestBody Role role, @CookieValue(value = AuthController.AUTHENTICATION_ID) Cookie cookie) {
		int authenticationId = Integer.parseInt(cookie.getValue());
		
		User user = this.userService.findByAuthenticationId(authenticationId);
		
		if (user != null && (EnumYesNo.YES).equals(user.getIsAdmin())) {
			return this.roleService.saveRole(role);
		}
		
		return new Role();
	}
	
	@PutMapping(value = "/roles/{roleId}")
	public Role updateRole(@PathVariable long roleId, @RequestBody Role newRole, @CookieValue(value = AuthController.AUTHENTICATION_ID) Cookie cookie) {
		int authenticationId = Integer.parseInt(cookie.getValue());
		
		User user = this.userService.findByAuthenticationId(authenticationId);
		
		if (user != null && (EnumYesNo.YES).equals(user.getIsAdmin())) {
			Role oldRole = this.roleService.findById(roleId);
			
			if (oldRole != null) {
				oldRole.setName(newRole.getName());
				oldRole.setUser(newRole.getUser());
				oldRole.setPermissions(newRole.getPermissions());
				
				return this.roleService.saveRole(oldRole);
			} else {
				newRole.setId(roleId);
				
				return this.roleService.saveRole(newRole);
			}
		}
		
		return new Role();
	}
	
	@DeleteMapping("/roles/{roleId}")
	public void deleteRole(@PathVariable Long roleId, @CookieValue(value = AuthController.AUTHENTICATION_ID) Cookie cookie) {
		int authenticationId = Integer.parseInt(cookie.getValue());
		
		User user = this.userService.findByAuthenticationId(authenticationId);
		
		if (user != null && (EnumYesNo.YES).equals(user.getIsAdmin())) {
			this.roleService.deleteById(roleId);
		}
	}
	
}
