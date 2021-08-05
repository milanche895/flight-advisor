package com.test.htec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.htec.entity.Token;
import com.test.htec.repository.UserRepository;
import com.test.htec.entity.AdvisorUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	
	@Autowired
	public UserRepository userRepository;

	public Token generate(AdvisorUser user) {

		Token token = new Token("Token "+ Jwts.builder()
				.setSubject(user.getUserName())
		.claim("role", user.getRole())
		.claim("id", user.getId())
		.signWith(SignatureAlgorithm.HS512, "htec")
		.compact(), user.getRole(), user.getId());
		
	return	token;
	}
}
