package info.mpaczes.usersmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import info.mpaczes.usersmanagement.domain.EnumPermission;
import info.mpaczes.usersmanagement.domain.Role;

@Entity
@Table(name = "PERMISSIONS")
public class Permission {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAME", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private EnumPermission name;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public EnumPermission getName() {
		return name;
	}
	public void setName(EnumPermission name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
