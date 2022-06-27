package com.teamproject.controller;
import com.auth0.jwt.JWT;
import com.teamproject.dao.AuthenticationDAO;
import com.teamproject.service.JWTServiceImpl;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

public class RequestMapping {

	private static RequestController req = new RequestController();
	private static AuthenticationDAO authDao = new AuthenticationDAO();
	


	public static void configureRoutes(Javalin app) {
		

			//login	-----------------------------------------------------------------------------------------
			app.post("/login", ctx -> {	
						
				req.login(ctx);
						
				});
			
	
			//-----------------------------------------------------------------------------------------------
			//testing for authorization / verifying the JWTs before each request
	
			
			app.before("/admin/*", ctx -> {
				
				if(!authDao.verify()) {  //checks JWT for "manager" role
					ctx.status(403);
					ctx.redirect("/login");
				}
				
			});
				
			
			//customer endpoints
			//-----------------------------------------------------------------------------------------------
			app.get("/customer/accountinfo", ctx -> {  //account
				
					req.getAcct(ctx);

				});
			
			app.post("/customer/deposit", ctx -> { //make a deposit
				
					req.custDeposit(ctx);
			});
			
			app.post("/customer/withdrawal", ctx ->{ //make a withdrawal
				
					req.custWithdraw(ctx);
				
			});
			
			app.post("/customer/transfer", ctx ->{ //transfer to another account
				
				req.custTransfer(ctx);
			});
			
			//guest endpoint for new account
			//----------------------------------------------------------------------------------------------
			app.post("/guest/newaccount", ctx ->{ //create a new account
				
				req.newAcct(ctx);
				
			});
			
			//management endpoint for viewing all accounts
			//----------------------------------------------------------------------------------------------
			
			app.get("/admin/viewaccounts", ctx ->{
				
				
				req.getAllAccts(ctx);
				
			});
			
			app.post("/admin/closeaccount", ctx -> {
				
			
				req.closeAcct(ctx);
				
			});
			
			app.get("/admin/history", ctx -> {
			
				
					req.history(ctx);
			});
			
			
			//----------------------------------------------------------------------------------------------
			//endpoint for logging out
			app.post("/logout", ctx -> {
				
				authDao.setToken(null); //token active until /logout
				if (authDao.getToken()==null) {  //double check
				ctx.result("you have been logged out");
				ctx.status(200);
				} else  {
					ctx.status(500);
					}
				
			});
			
			
			
			
	}
	
	
}
