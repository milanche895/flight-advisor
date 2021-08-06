package com.test.htec.service;

import java.util.List;

import com.test.htec.DTO.UserDTO;
import com.test.htec.entity.Token;
import com.test.htec.entity.Route;

public interface UserService {

	Token registrationUser(UserDTO userDTO);

	Token loginUser(UserDTO userDTO);
	
}
