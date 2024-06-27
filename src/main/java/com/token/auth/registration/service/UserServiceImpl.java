package com.token.auth.registration.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.token.auth.exception.custom.RegistrationModuleException;
import com.token.auth.registration.model.User;
import com.token.auth.registration.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		Optional<User> existsUserByEmail = userRepository.findByEmail(user.getEmail());
		if (existsUserByEmail.isPresent()) {
			throw new RegistrationModuleException("User already present", HttpStatus.BAD_REQUEST);
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		User saveUser = userRepository.save(user);
		return saveUser;
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user;
	}

}
