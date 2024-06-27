package com.token.auth.password.dto;

public class PasswordReset {

	private String newPassword;
	private String confirmationPassword;
	private String token;

	public PasswordReset() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	@Override
	public String toString() {
		return "PasswordReset [newPassword=" + newPassword + ", confirmationPassword=" + confirmationPassword
				+ ", token=" + token + "]";
	}

}
