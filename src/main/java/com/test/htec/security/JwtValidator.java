package com.test.htec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.htec.entity.AdvisorUser;
import com.test.htec.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	@Autowired	
	public UserRepository userRepository;
	
	private String secret = "htec";
	
	public AdvisorUser validate(String token) {
		
		AdvisorUser user= null;
		try {
		Claims body = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
		
			body.get("role");
			
			user = new AdvisorUser();
		
		user.setUserName(body.getSubject());
		user.setId(new Long(body.get("id", Integer.class)));
//		jwtUser.setRole((String)body.get("role"));
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return user;
	}
}
