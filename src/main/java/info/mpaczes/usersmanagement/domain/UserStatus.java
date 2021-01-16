package info.mpaczes.usersmanagement.domain;

public enum UserStatus {
	AUTHENTICATED("AUTHENTICATED"), UNAUTHORIZED("UNAUTHORIZED"), PASSWORD_RESET("PASSWORD_RESET"), FORGOTTEN_PASWORD("FORGOTTEN_PASWORD");
	
	private String userStatuAsString;
	
	private UserStatus(String userStatuAsString) {
		this.userStatuAsString = userStatuAsString;
	}
	
	public String getUserStatuAsString() {
		return userStatuAsString;
	}
}
