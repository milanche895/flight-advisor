package com.test.htec.service.implement;

import org.springframework.stereotype.Service;

import com.test.htec.constants.UserTypeEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class PermisionSystem {
	
	public boolean checkAdministratorAccess(String token) {
		
		String tokenTrim = token.substring(6);
		
		Claims claims =
		(Claims) Jwts.parser()
	    
		  .setSigningKey("htec") // <----
		  
		  .parseClaimsJws(tokenTrim).getBody();
		
		claims.get("role");
		claims.get("id");
		
		if (claims.get("role").equals(UserTypeEnum.ADMINISTRATOR.toString())) {
			
			return true;
		}
		else {
				
			return false;
			
		}
	}
	public boolean checkRegularUserAccess(String token) {
		
		String tokenTrim = token.substring(6);
		
		Claims claims =
		(Claims) Jwts.parser()
	    
		  .setSigningKey("htec") // <----
		  
		  .parseClaimsJws(tokenTrim).getBody();
		
		claims.get("role");
		claims.get("id");
		
		if (claims.get("role").equals(UserTypeEnum.REGULAR_USER.toString())) {
			
			return true;
		}
		else {
				
			return false;
			
		}
	}
	public Long checkUserId(String token){
	String tokenTrims = token.substring(6);
	
	Claims claims =
	(Claims) Jwts.parser()
    
	  .setSigningKey("htec") // <----
	  
	  .parseClaimsJws(tokenTrims).getBody();
	
	claims.get("role");
	claims.get("id", Integer.class);
	
	return 	new Long(claims.get("id", Integer.class));
	}
}
