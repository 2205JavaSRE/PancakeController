package com.teamproject.service;

import com.teamproject.dao.AuthenticationDAO;
import com.teamproject.util.Prometheus;

import io.javalin.http.Context;

public class AuthenticationServiceImpl implements AuthenticationService{
	public static Prometheus prom = new Prometheus();
	public AuthenticationServiceImpl() {
		super();
	}
	
	public void login(Context ctx) {
		
		prom.counter();	//updates prometheus for login attempts
		AuthenticationDAO authDao = new AuthenticationDAO();
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
			if(authDao.authenticateUser(username, password)) {
				ctx.sessionAttribute("username", username);
				ctx.sessionAttribute("password", password);
				
				ctx.status(201);
				} else {
					ctx.status(403);
				}
}

}
