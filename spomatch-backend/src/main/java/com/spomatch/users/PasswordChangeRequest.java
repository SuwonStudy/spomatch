package com.spomatch.users;

/**
 * 비밀번호 변경을 위한 DTO이다.
 * 
 * @author Seongbin Kim
 */
public class PasswordChangeRequest {
	
	private String oldPassword, newPassword;

	public PasswordChangeRequest() {
	}

	public PasswordChangeRequest(String oldPassword, String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
