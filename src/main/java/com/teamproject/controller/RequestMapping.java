package com.teamproject.controller;
import com.teamproject.dao.AuthenticationDAO;

import io.javalin.Javalin;

public class RequestMapping {

	private static RequestController req = new RequestController();
	private static AuthenticationDAO authDao = new AuthenticationDAO();
	


	public static void configureRoutes(Javalin app) {
		
			//login	-----------------------------------------------------------------------------------------
			app.post("/login", ctx -> {	
						
				req.login(ctx);
						
				});
			
	
			//-----------------------------------------------------------------------------------------------
			//testing for authorization
			app.before("/customer/*", ctx -> {  
				
				if(!authDao.check()) {
				ctx.status(403);
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
			
			
			//----------------------------------------------------------------------------------------------
			//endpoint for logging out
			app.post("/logout", ctx -> {
				
				ctx.consumeSessionAttribute("username");
				ctx.status(200);
			});
			
			
			
			
	}
	
	
}
