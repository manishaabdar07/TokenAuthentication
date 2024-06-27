package com.token.auth.registration.service;

import java.util.Optional;

import com.token.auth.registration.model.User;

public interface UserService {
	
	public User createUser(User user);

	public Optional<User> findUserByEmail(String email);
}
