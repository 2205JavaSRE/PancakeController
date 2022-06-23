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
		//System.out.println("DEBUG FORM PARAM USERNAME: " + username);
		String password = ctx.formParam("password");
		
			if(true/*authDao.authenticateUser(username, password)*/) {
				//ctx.sessionAttribute("username", username);
				//System.out.println(ctx.sessionAttribute("username").toString());
				//ctx.sessionAttribute("password", password);
				
				ctx.status(201);
				ctx.result("Welcome to the Pancake Bank!");
				} else {
					ctx.status(403);
				}
}

}
