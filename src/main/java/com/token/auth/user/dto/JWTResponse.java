package com.token.auth.user.dto;

import java.util.ArrayList;
import java.util.List;

public class JWTResponse {

	private Long id;
	private String username;
	private String email;
	private String accessToken;
	private List<String> roles = new ArrayList<>();

	public JWTResponse(Long id, String username, String email, String accessToken, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.accessToken = accessToken;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public JWTResponse(String accessToken) {
		this.accessToken = accessToken;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "JWTResponse [id=" + id + ", username=" + username + ", email=" + email + ", accessToken=" + accessToken
				+ ", roles=" + roles + "]";
	}

}
