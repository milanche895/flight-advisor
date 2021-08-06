package com.test.htec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.htec.DTO.CityDTO;
import com.test.htec.DTO.UserDTO;
import com.test.htec.entity.Route;
import com.test.htec.entity.Token;
import com.test.htec.service.CityService;
import com.test.htec.service.UserService;


@RestController
@RequestMapping("/advisor")
public class PublicController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CityService cityService;
	
	@PostMapping("/registration")
	public ResponseEntity<Token> registrationUser(@RequestBody UserDTO userDTO){
		return new ResponseEntity<Token>(userService.registrationUser(userDTO),HttpStatus.OK);
	}
	@PostMapping("/login")
	public ResponseEntity<Token> loginUser(@RequestBody UserDTO userDTO){
		return new ResponseEntity<Token>(userService.loginUser(userDTO),HttpStatus.OK);
	}

}
