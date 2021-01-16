package info.mpaczes.usersmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.mpaczes.usersmanagement.domain.Role;
import info.mpaczes.usersmanagement.repository.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;
	
	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<Role> findAllRoles() {
		return this.roleRepository.findAll();
	}
	
	public Role saveRole(Role role) {
		return this.roleRepository.save(role);
	}
	
	public Role findById(long id) {
		return this.roleRepository.findById(id);
	}
	
	public void deleteById(long id) {
		this.roleRepository.deleteById(id);
	}
}
