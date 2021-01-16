package info.mpaczes.usersmanagement.domain;

public class AuthenticationInfo {
	
	public static final String NO_SUCH_USER = "no such user";
	
	private String userName = NO_SUCH_USER;
	
	private String userStatus;
	
	private String forgottenPaswordValue = "";
	
	public AuthenticationInfo() {
	}
	
	public AuthenticationInfo(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public AuthenticationInfo(String userName, String userStatus) {
		this.userName = userName;
		this.userStatus = userStatus;
	}
	
	public AuthenticationInfo(String userName, String userStatus, String forgottenPaswordValue) {
		this.userName = userName;
		this.userStatus = userStatus;
		this.forgottenPaswordValue = forgottenPaswordValue;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getForgottenPaswordValue() {
		return forgottenPaswordValue;
	}

	public void setForgottenPaswordValue(String forgottenPaswordValue) {
		this.forgottenPaswordValue = forgottenPaswordValue;
	}

}
