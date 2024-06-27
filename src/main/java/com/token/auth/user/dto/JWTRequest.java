package com.token.auth.user.dto;

public class JWTRequest {

	private String email;
	private String password;

	public JWTRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static JWTRequest builder() {
		return new JWTRequest();
	}

	@Override
	public String toString() {
		return "JWTRequest [email=" + email + ", password=" + password + "]";
	}

}
