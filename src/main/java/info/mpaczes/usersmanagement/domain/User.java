package info.mpaczes.usersmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import info.mpaczes.usersmanagement.domain.Role;
import info.mpaczes.usersmanagement.domain.EnumYesNo;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	
	@Column(name = "USER_NAME", nullable = false, length = 20)
	private String userName;
	
	@Column(name = "PASSWORD", nullable = false, length = 20)
	private String password;
	
	@OneToOne
	private Role role;
	
	@Column(name = "IS_ADMIN", length = 3)
	@Enumerated(EnumType.STRING)
	private EnumYesNo isAdmin;
	
	@Column(name = "AUTHENTICATION_ID")
	private Integer authenticationId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public EnumYesNo getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(EnumYesNo isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getAuthenticationId() {
		return authenticationId;
	}
	public void setAuthenticationId(Integer authenticationId) {
		this.authenticationId = authenticationId;
	}	
}
