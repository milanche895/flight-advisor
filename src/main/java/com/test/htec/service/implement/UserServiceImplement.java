package com.test.htec.service.implement;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.test.htec.DTO.UserDTO;
import com.test.htec.constants.UserTypeEnum;
import com.test.htec.entity.AdvisorUser;
import com.test.htec.entity.Route;
import com.test.htec.entity.Token;
import com.test.htec.repository.RouteRepository;
import com.test.htec.repository.UserRepository;
import com.test.htec.security.JwtGenerator;
import com.test.htec.service.UserService;

@Service
public class UserServiceImplement implements UserService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	JwtGenerator jwtGenerator;

	@Override
	public Token registrationUser(UserDTO userDTO) {
		AdvisorUser user = new AdvisorUser();
		if (Objects.nonNull(userDTO)) {
			
			if (Objects.isNull(userRepository.findOneByUserName(userDTO.getUserName()))) {
				String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
				
				user.setFirstName(userDTO.getFirstName());
				user.setLastName(userDTO.getLastName());
				user.setPassword(encodedPassword);
				user.setUserName(userDTO.getUserName());
				user.setRole(UserTypeEnum.REGULAR_USER.toString());
				user.setSalt(userDTO.getSalt());
				
				userRepository.save(user);
				
				return jwtGenerator.generate(user);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserName already exist");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please insert  all and correct data");
		}
		
	}
	@Override
	public Token loginUser(UserDTO userDTO) {
		if (Objects.nonNull(userDTO.getUserName()) && Objects.nonNull(userDTO.getPassword())) {
			AdvisorUser user = userRepository.findOneByUserName(userDTO.getUserName());
			if (Objects.isNull(user)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User doesn`t exist");
			} else {
				boolean hashPassword = bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword());
				
				if(hashPassword) {
					Token token = jwtGenerator.generate(user);
					
					return token;
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong password");
				}
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please input username and password");
		}
	}

}
