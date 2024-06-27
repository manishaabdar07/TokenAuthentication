package com.token.auth.mail.service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.token.auth.mail.config.EmailConfig;
import com.token.auth.mail.model.Email;

import ch.qos.logback.classic.Logger;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@ConfigurationPropertiesScan("com.token.auth.mail.config")
public class EmailSenderServiceImpl implements EmailSenderService{

	private static final Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory
			.getLogger(EmailSenderServiceImpl.class);

	@Autowired
	private EmailConfig emailConfig;

	@Autowired
	private JavaMailSender mailSender;

	@Value("${email.username}")
	private String emailUsername;

	public void printEmailConfig() {
		logger.info(emailConfig.toString());
	}

	public void send(Email mail, String username, String link) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			Context context = new Context();
			context.setVariables(mail.getProperties());
			helper.setTo(mail.getTo());
			try {
				helper.setFrom(mail.getFrom(), emailUsername);
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
			}
			helper.setSubject(mail.getSubject());
			String content = "<p>Hello " + username + ",</p>" + "<p>You have requested to reset your password.</p>"
					+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
					+ "\">Reset your password</a></p>" + "<br>"
					+ "<p>Ignore this email if you do remember your password, "
					+ "or you have not made the request.</p>";
			helper.setText(content, true);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}

	public void sendOTPEmail(Email email, String OTP, String username) throws MessagingException {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			helper.setFrom(email.getFrom(), emailUsername);

			helper.setTo(email.getTo());
			String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
			String content = "<p>Hello " + username + "</p>"
					+ "<p>For security reason, you're required to use the following "
					+ "One Time Password to login:</p>" + "<p><b>" + OTP + "</b></p>" + "<br>"
					+ "<p>Note: this OTP is set to expire in 5 minutes.</p>";
			helper.setSubject(subject);
			helper.setText(content, true);
			mailSender.send(mimeMessage);
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

}
