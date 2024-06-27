package com.token.auth.password.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.token.auth.exception.custom.ForgotPasswordModuleException;
import com.token.auth.mail.model.Email;
import com.token.auth.mail.service.EmailSenderService;
import com.token.auth.password.dto.EmailDto;
import com.token.auth.password.service.PasswordResetTokenService;
import com.token.auth.registration.model.User;
import com.token.auth.registration.service.UserService;
import com.token.auth.response.dto.Response;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("api/auth")
public class ForgotPasswordController {

	@Autowired
	private EmailSenderService emailService;

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@Autowired
	private UserService userService;

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${employee.portal.origin}")
	private String baseUrl;

	@PostMapping("/forgot-password")
	public ResponseEntity<Object> forgotPassword(@Valid @RequestBody EmailDto emailDto, BindingResult result, Model model,
			RedirectAttributes attributes, HttpServletRequest request) {

		if (emailDto.getEmail().isEmpty()) {
			throw new ForgotPasswordModuleException("Please enter email", HttpStatus.BAD_REQUEST);
		}

		Optional<User> user = userService.findUserByEmail(emailDto.getEmail());
		if (user.isPresent()) {
			User existUser = user.get();
			String response = passwordResetTokenService.forgotPassword(emailDto.getEmail(), model);
			String emaillink = baseUrl + "/#/resetPassword/" + response;
			Email mail = new Email();
			mail.setFrom(sender);
			mail.setTo(existUser.getEmail());
			mail.setSubject("Password reset request");
			emailService.send(mail, existUser.getUsername(), emaillink);
			Response responsebody = new Response("Success", "Verification mail sent on email");
			return ResponseEntity.ok().body(responsebody);
		} else {
			throw new ForgotPasswordModuleException("User not found", HttpStatus.NOT_FOUND);
		}

	}

}
