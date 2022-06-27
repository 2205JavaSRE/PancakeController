package com.teamproject.service;

public interface JWTService {
	
	public String createJWT(String username, String role);

}
