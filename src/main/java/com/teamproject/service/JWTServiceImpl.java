package com.teamproject.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.teamproject.dao.AuthenticationDAO;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class JWTServiceImpl implements JWTService{
	
	public JWTServiceImpl(){
		super();
	}
    

public static final String KEY = "8G56A5DF15A87CE";
static AuthenticationDAO authDao = new AuthenticationDAO();

//creates a JWT token
	public String createJWT(String username, String role) {
		
			return JWT.create()
					  .withSubject(username)
					  .withClaim("role", role)
					  .sign(Algorithm.HMAC256(KEY));	
	     
	}


//extracts the username from the JWT token
	public static String getUsername(String token) {
		
			DecodedJWT jwt = JWT.decode(authDao.getToken());
			String user = jwt.getSubject();
			return user;
		
	}

//extracts the role from the JWT token
	public static String getRole(String token) {
	   
			DecodedJWT jwt = JWT.decode(authDao.getToken());
			String role = jwt.getClaim("role").asString();
			return role;
	    
	    
	}
	
    //verifies the JWT token
    public static boolean verifyJWT(String token) {
    	
	    try {
	        JWT.require(Algorithm.HMAC256(KEY)).build().verify(token);
	        return true;
	    } catch (JWTVerificationException exception) {
	        return false;
	    }
	}
    
    
}
