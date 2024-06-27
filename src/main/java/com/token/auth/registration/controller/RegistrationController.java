package com.token.auth.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.token.auth.registration.model.User;
import com.token.auth.registration.service.UserService;
import com.token.auth.response.dto.DataResponse;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		User apiResponse = userService.createUser(user);
		DataResponse respose = new DataResponse("success", "User save successfully", apiResponse);
		return ResponseEntity.ok().body(respose);
	}

}
