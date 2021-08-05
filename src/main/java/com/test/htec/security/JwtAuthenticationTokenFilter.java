package com.test.htec.security;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.test.htec.entity.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	EntityManager em;
	
    public JwtAuthenticationTokenFilter() {
        super("/advisor/auth/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

    		String header = httpServletRequest.getHeader("Authorization");
    		
    		if (header == null || !header.startsWith("Token")) {
    		    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Jwt token is missing");
    		} else {
    		
    		String authenticationToken = header.substring(6);
    		
    		JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
    		return getAuthenticationManager().authenticate(token); }
    		return null;
    }

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
}
