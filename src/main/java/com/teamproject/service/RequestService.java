package com.teamproject.service;

import java.sql.SQLException;

import io.javalin.http.Context;

public interface RequestService {
	
	public void getAccount(Context ctx, String username) throws SQLException;
	public void deposit(Context ctx, String username) throws SQLException;
	public void withdraw(Context ctx, String username);
	public void transfer(Context ctx, String username, int acctNum) throws SQLException;
	public void createAcct(Context ctx, String username, String password, double balance) throws SQLException;
	

}

