package com.token.auth.password.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.token.auth.password.dto.PasswordReset;
import com.token.auth.password.model.PasswordResetToken;
import com.token.auth.password.repository.PasswordResetTokenRepository;
import com.token.auth.password.service.PasswordResetTokenService;
import com.token.auth.response.dto.DataResponse;
import com.token.auth.response.dto.Response;

@Controller
@RequestMapping("api/auth")
public class ResetPasswordController {

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@PostMapping("/reset-password")
	public ResponseEntity<Object> resetPassword(@RequestBody PasswordReset request) {
		String tokenVerificationResult = passwordResetTokenService.validatePasswordResetToken(request.getToken());
		if (!tokenVerificationResult.equalsIgnoreCase("valid")) {
			Response responsebody = new Response("Error", "Invalid parameter");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsebody);
		}
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(request.getToken()).get();
		String email = passwordResetTokenService.updatePassword(passwordResetToken, request.getConfirmationPassword());

		DataResponse response = new DataResponse("Success", "Password has been reset successfully", email);
		return ResponseEntity.ok().body(response);
	}

}

