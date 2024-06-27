package com.token.auth.mail.service;

import com.token.auth.mail.model.Email;

import jakarta.mail.MessagingException;

public interface EmailSenderService {

	public void send(Email mail, String username, String link);

	public void sendOTPEmail(Email email, String OTP, String username) throws MessagingException;

}
