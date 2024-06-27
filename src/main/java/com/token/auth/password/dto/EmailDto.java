package com.token.auth.password.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmailDto {

	@Email(message = "please enter correct email")
	private String email;

	public EmailDto() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ForgotPassword [email=" + email + "]";
	}

}
