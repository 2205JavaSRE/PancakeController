package com.teamproject.service;

import java.sql.SQLException;
import com.teamproject.dao.RequestDAO;
import io.javalin.http.Context;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class RequestServiceImpl implements RequestService {
	
	static PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
	static RequestDAO req = new RequestDAO();
	
	public void getAcct(Context ctx){                          //gets account info
			
					String user = ctx.formParam("username"); //requires form parameter: username
					String check = ctx.cookieStore("username");
					
					
					if(user.equalsIgnoreCase(check)) {
			
						 
						 
						 try {
							req.getAccount(ctx, check);
							ctx.status(200);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						 
					}
		}
	
	public void custDeposit(Context ctx) {       //for deposits
			
					String user = ctx.formParam("username"); //requires form parameters: username, deposit
					String check = ctx.cookieStore("username"); 
					
					if(user.equalsIgnoreCase(check)) {
			
						 RequestDAO req = new RequestDAO();
						 
						 try {
							req.deposit(ctx, check);
							ctx.status(200);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						 
					}
		}
	
	public void custWithdraw(Context ctx) {  //for withdrawals
			
					String user = ctx.formParam("username"); //requires form parameters: username, withdraw
					String check = ctx.cookieStore("username");
					
					if(user.equalsIgnoreCase(check)) {
						 
						 req.withdraw(ctx, check);
						ctx.status(200);
						 
					}
			
			
		}
	
	public void custTransfer(Context ctx) throws SQLException {  //for transfering between accounts (based on account number)
			
					String user = ctx.formParam("username"); //requires form parameters: username, amount, acctnum
					String check = ctx.cookieStore("username");
					int acctNum = Integer.parseInt(ctx.formParam("acctnum"));
					
					if(user.equalsIgnoreCase(check)) {
			
						 req.transfer(ctx, check, acctNum);
						ctx.status(200);
						 
					}
			
		}
	
	//--------------------------------------------------------------- for creating new accounts
		public void newAcct(Context ctx) throws SQLException {  //requires form parameters: username, password, and balance (for creating account with a balance)
					String user = ctx.formParam("username");
					String pass = ctx.formParam("password");
					double balance = Double.parseDouble(ctx.formParam("balance"));
					
					req.createAcct(ctx, user, pass, balance);
					ctx.status(201);
		}
		
		//---------------------------------------------------------functions for managers	
		public void getAllAccts(Context ctx) throws SQLException { 
			
			
					req.getAllAccounts(ctx);
					ctx.status(200);
					
			
		}
		
		public void closeAcct(Context ctx) throws SQLException { 
			

					String user = ctx.formParam("user"); //requires form parameter: user
					req.closeAccount(ctx, user);
					ctx.status(200);
			
		}
		
		public void history(Context ctx) throws SQLException {
			
		
					req.getHistory(ctx);
					ctx.status(201);
			
			
		}

}
