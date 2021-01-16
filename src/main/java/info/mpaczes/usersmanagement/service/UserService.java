package info.mpaczes.usersmanagement.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.mpaczes.usersmanagement.domain.EnumPermission;
import info.mpaczes.usersmanagement.domain.EnumYesNo;
import info.mpaczes.usersmanagement.domain.Permission;
import info.mpaczes.usersmanagement.domain.User;
import info.mpaczes.usersmanagement.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User isUserPresentInDb(String userName, String password) {
		User user = this.userRepository.findByUserNameAndPassword(userName, password);
		
		if (user != null) {
			return user;
		}
		
		return null;
	}
	
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}
	
	public User findByAuthenticationId(int authenticationId) {
		return this.userRepository.findByAuthenticationId(authenticationId);
	}
	
	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}
	
	public void deleteUser(long userId) {
		this.userRepository.deleteById(userId);
	}
	
	public Optional<User> findById(long userId) {
		return this.userRepository.findById(userId);
	}
	
	public User findByUserName(String userName) {
		return this.userRepository.findByUserName(userName);
	}
	
	public boolean haUserPermision(User user, EnumPermission permission) {
		boolean hasPermission = false;
		
		Iterator<Permission> permissionsToFilter = user.getRole().getPermissions().iterator();
		while (permissionsToFilter.hasNext()) {
			Permission permissionFound = permissionsToFilter.next();
			if (permission.equals(permissionFound.getName())) {
				hasPermission = true;
				break;
			}
		}
		return hasPermission;
	}
	
	public int generateRandomNumber() {
		Random generator = new Random();
		int randomNumber = generator.nextInt(Integer.MAX_VALUE);
		return randomNumber;
	}
	
}
