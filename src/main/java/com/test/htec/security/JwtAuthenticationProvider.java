package com.test.htec.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.test.htec.DTO.UserDetailsDTO;
import com.test.htec.entity.JwtAuthenticationToken;
import com.test.htec.entity.AdvisorUser;
import com.test.htec.repository.UserRepository;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

	@Autowired
	private JwtValidator validator;
	
	@Autowired
	public UserRepository userRepository;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	EntityManager em;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
		
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
		
		String token = jwtAuthenticationToken.getToken();
		
		AdvisorUser user = validator.validate(token);	
		
		if(user == null) {
			throw new RuntimeException("Incorrect jwt token");
		}		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		return new UserDetailsDTO(user.getUserName(), user.getId(), token, grantedAuthorities);

	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
	}

}
