package com.teamproject.service;

import com.teamproject.dao.AuthenticationDAO;
import com.teamproject.util.Prometheus;
import io.javalin.http.Context;

public class AuthenticationServiceImpl implements AuthenticationService{
	public AuthenticationServiceImpl() {
		super();
	}
	
	public void login(Context ctx) {
		
		Prometheus.counter();	//updates counter for login attempts
		AuthenticationDAO authDao = new AuthenticationDAO();
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
			
			if (authDao.authenticateUser(username, password)) {
				String user = JWTServiceImpl.getUsername(authDao.getToken());
				ctx.status(201);
				ctx.result(user+", welcome to the Pancake Bank!");
				} else {ctx.status(403);}
				
}

}
