package com.test.htec.service;

import com.test.htec.DTO.UserDTO;
import com.test.htec.entity.Token;

public interface UserService {

	Token registrationUser(UserDTO userDTO);

	Token loginUser(UserDTO userDTO);
	
}
