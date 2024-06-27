package com.token.auth.password.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.token.auth.password.model.PasswordResetToken;
import com.token.auth.password.repository.PasswordResetTokenRepository;
import com.token.auth.registration.model.User;
import com.token.auth.registration.repository.UserRepository;

@Service
public class PasswordResetTokenService {

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	public String forgotPassword(String email, Model model) {
		final int MINUTES = 30;
		Optional<User> userPresent = userRepository.findByEmail(email);
		User user = userPresent.get();
		PasswordResetToken token = new PasswordResetToken();
		String isUserPresent = passwordResetTokenService.isUserPresent(user.getId());
        if(isUserPresent == "exist") {
        	String updateToken = UUID.randomUUID().toString();
        	LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(MINUTES);
        	passwordResetTokenRepository.updateToken(updateToken, expirationTime, user.getId());
        	return updateToken;
        }
        else {          
    		token.setUser(user);
    		token.setToken(UUID.randomUUID().toString());
    		token.setExpirationTime(LocalDateTime.now().plusMinutes(MINUTES));
    		token = passwordResetTokenService.save(token);
    		return token.getToken();
        }
	}

	public PasswordResetToken save(PasswordResetToken token) {
		return passwordResetTokenRepository.save(token);
	}

	public String validatePasswordResetToken(String token) {
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token).get();
		if (passwordResetToken == null) {
			return "Invalid password reset token ";
		}
		if ((passwordResetToken.getExpirationTime().getMinute()) <= 0) {
			return "Link already expired, resend link";
		}
		return "valid";
	}
	
	public String isUserPresent(Long emp_id) {
		Optional<PasswordResetToken> passwordtokenresult = passwordResetTokenRepository.findUserById(emp_id);
		if (!passwordtokenresult.isEmpty())
			return "exist";
		else
			return "notexist";
	}
	
	public String updatePassword(PasswordResetToken passwordResetToken, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		User user = passwordResetToken.getUser();
		if (user != null) {
			user.setPassword(encodedPassword);
			userRepository.save(user);
			passwordResetTokenRepository.deleteTokenByUserId(user.getId());
			passwordResetTokenRepository.save(passwordResetToken);
			return user.getEmail();
		} else {
			throw new UsernameNotFoundException("Could not find any customer with the email ");
		}
	}

}
