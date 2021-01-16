package info.mpaczes.usersmanagement.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import info.mpaczes.usersmanagement.domain.User;
import info.mpaczes.usersmanagement.domain.Permission;

@Entity
@Table(name = "ROLES")
public class Role {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAME", nullable = false, length = 20)
	private String name;
	
	@OneToOne(mappedBy = "role")
	private User user;
	
	@OneToMany(mappedBy = "role")
	private List<Permission> permissions;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
