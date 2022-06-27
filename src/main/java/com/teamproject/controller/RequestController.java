package com.teamproject.controller;

import java.sql.SQLException;

import com.teamproject.dao.AuthenticationDAO;
import com.teamproject.service.AuthenticationServiceImpl;
import com.teamproject.service.RequestServiceImpl;
import io.javalin.http.Context;

public class RequestController {

	public RequestServiceImpl req = new RequestServiceImpl();
	public AuthenticationServiceImpl auth = new AuthenticationServiceImpl();
	public AuthenticationDAO authDao = new AuthenticationDAO(); //only DAO dependency is for setting JWT / cookie

	public RequestController() {
		super();
	}
	
	
	//-----------------------------------login //the form parameters are: username, password
	public void login(Context ctx) {
		
		auth.login(ctx);
		ctx.cookie("user", authDao.getToken()); //sets the cookie
		
	}

	
	//-----------------------------------------------customer functions
	public void getAcct(Context ctx){                          //gets account info
															   //the form parameters for this are: username
		
		req.getAcct(ctx);
		
	}
	
	public void custDeposit(Context ctx) {       //for deposits
												 //the form parameters for this are: username, deposit
		
		req.custDeposit(ctx);
		
	}
	
	public void custWithdraw(Context ctx) {  //for withdrawals
											 //the form parameters for this are: username, withdraw
		
		req.custWithdraw(ctx);
		
		
	}
	
	public void custTransfer(Context ctx) throws SQLException {  //for transfering between accounts (based on account number)
																 //the form parameters for this are: username, amount, acctnum (the recipient)
		req.custTransfer(ctx);
		
	}
	
	//--------------------------------------------------------------- for creating new accounts
	public void newAcct(Context ctx) throws SQLException {
		
		req.newAcct(ctx);
	}
	
	//---------------------------------------------------------functions for managers	
	public void getAllAccts(Context ctx) throws SQLException { //this security checks the session to make sure "manager" is logged in
		
		req.getAllAccts(ctx);
		
	}
	
	public void closeAcct(Context ctx) throws SQLException { //the form parameters for this are: username (to be removed)
		
		req.closeAcct(ctx);
		
	}
	
	public void history(Context ctx) throws SQLException {
		
		req.history(ctx);
		
		
	}

	
}
