package com.teamproject.service;

import java.sql.SQLException;

import com.teamproject.dao.AuthenticationDAOImpl;
import com.teamproject.dao.RequestDAO;
import com.teamproject.util.Prometheus;

import io.javalin.http.Context;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class RequestServiceImpl implements RequestService, AuthenticationService{
	
	static PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
	static RequestDAO req = new RequestDAO();
	
	//-----------------------------------login
	public void login(Context ctx) {
		
				Prometheus prom = new Prometheus();
				prom.counter();	//updates prometheus for login attempts
				AuthenticationDAOImpl authDao = new AuthenticationDAOImpl();
				String username = ctx.formParam("username");
				String password = ctx.formParam("password");
				
					if(authDao.authenticateUser(username, password)) {
						ctx.sessionAttribute("username", username);
						ctx.sessionAttribute("password", password);
						
						ctx.status(201);
						ctx.result("Welcome to the Pancake Bank!");
						} else {
							ctx.status(403);
						}
	}
	
	public void getAcct(Context ctx){                          //gets account info
			
					String user = ctx.formParam("username");
					String check = ctx.cachedSessionAttribute("username");
					
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
			
					String user = ctx.formParam("username");
					String check = ctx.cachedSessionAttribute("username"); 
					
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
			
					String user = ctx.formParam("username");
					String check = ctx.cachedSessionAttribute("username");
					
					if(user.equalsIgnoreCase(check)) {
						 
						 req.withdraw(ctx, check);
						ctx.status(200);
						 
					}
			
			
		}
	
	public void custTransfer(Context ctx) throws SQLException {  //for transfering between accounts (based on account number)
			
					String user = ctx.formParam("username");
					String check = ctx.cachedSessionAttribute("username");
					int acctNum = Integer.parseInt(ctx.formParam("acctnum"));
					
					if(user.equalsIgnoreCase(check)) {
			
						 req.transfer(ctx, check, acctNum);
						ctx.status(200);
						 
					}
			
		}
	
	//--------------------------------------------------------------- for creating new accounts
		public void newAcct(Context ctx) throws SQLException {
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
			

					String user = ctx.formParam("user");
					req.closeAccount(ctx, user);
					ctx.status(200);
			
		}
		
		public void history(Context ctx) throws SQLException {
			
		
					req.getHistory(ctx);
					ctx.status(201);
			
			
		}

}
