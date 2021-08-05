package com.test.htec.entity;

public class Token {

	private String token;
	
	private String role;
	
	public Token(String token, String role, Long id) {
		
		this.token = token;
		this.role = role;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
