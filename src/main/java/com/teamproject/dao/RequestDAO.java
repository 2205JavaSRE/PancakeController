package com.teamproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import com.teamproject.models.CustomerRequest;
import com.teamproject.service.RequestService;
import com.teamproject.util.ConnectionFactory;

import io.javalin.http.Context;


public class RequestDAO implements RequestService {

	public RequestDAO() {
		super();	
	}
	
	public void getAccount(Context ctx, String username) throws SQLException{
		
			String sql = ("SELECT * FROM accounts WHERE username='"+username+"'"); //returns account balance
			Connection connection = ConnectionFactory.connectUser();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
						CustomerRequest e = new CustomerRequest(rs.getInt("acctnum"), rs.getString("username"),
						 rs.getDouble("balance"), rs.getString("timestamp"));
						ctx.json(e);
						System.out.println(e);
						}
						
	}
	
	public void deposit(Context ctx, String username) throws SQLException {
		
	
			String sql = "SELECT balance FROM accounts WHERE username='"+username+"'";
			Connection connection = ConnectionFactory.connectUser();
			Statement stmt = connection.createStatement();
			ResultSet acct = stmt.executeQuery(sql);
			
			
			double balance;	
					while (acct.next())
					{
					balance = acct.getDouble("balance");
					balance += Double.parseDouble(ctx.formParam("deposit"));
					Statement stmt2 = connection.createStatement();
					stmt2.execute("UPDATE accounts SET balance = "+balance+" WHERE username= "+"'"+username+"'");
					} 
							
}
	
	public void withdraw(Context ctx, String username) {
		
		try {
			String SQLquery = "SELECT balance FROM accounts WHERE username="+"'"+username+"'";
			Connection connection = ConnectionFactory.connectUser();
			Statement stmt = connection.createStatement();
			ResultSet acct = stmt.executeQuery(SQLquery);
			
			
			double balance;		
					while (acct.next())
					{
					balance = acct.getDouble("balance");
					balance -= Double.parseDouble(ctx.formParam("withdraw"));
					Statement stmt2 = connection.createStatement();
					stmt2.execute("UPDATE accounts SET balance="+balance+" WHERE username="+"'"+username+"'");
					} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}
	
	public void transfer(Context ctx, String username, int acctNum) throws SQLException {
		boolean x = true; //if the account balance is positive after the withdrawal, the transfer will continue
		//First, withdraw from this account
		
			String SQLquery = "SELECT balance FROM accounts WHERE username = "+"'"+username+"'";
			Connection connection = ConnectionFactory.connectUser();
			Statement stmt = connection.createStatement();
			ResultSet acct = stmt.executeQuery(SQLquery);
			
			
			double balance;
				
					while (acct.next())
					{
					balance = acct.getDouble("balance");
					if (!(balance - Double.parseDouble(ctx.formParam("amount")) < 0))            //check for negative account balance
					   {
						balance -= Double.parseDouble(ctx.formParam("amount"));
					   } else { 
						   x = false;
						   ctx.status(418);
						   }
					if (x == true) {
					Statement stmt2 = connection.createStatement();
					stmt2.execute("UPDATE accounts SET balance = "+balance+" WHERE username = "+"'"+username+"'");
			
					}
				
		
	if (x == true) {
		//Second, deposit in another account
	
			String sql = "SELECT balance FROM accounts WHERE acctnum = "+"'"+acctNum+"'";
			Statement stmt2 = connection.createStatement();
			ResultSet acct2 = stmt2.executeQuery(sql);
			
			
			double balance1;		
					while (acct2.next())
					{
					balance1 = acct2.getDouble("balance");
					balance1 += Double.parseDouble(ctx.formParam("amount"));
					Statement stmt3 = connection.createStatement();
					stmt3.execute("UPDATE accounts SET balance = "+balance1+" WHERE acctnum = "+"'"+acctNum+"'");				
					} 
					
				}
	
			}
					
		
	}
	
	
	public void createAcct(Context ctx, String username, String password, double balance) throws SQLException {
		
		
		String sql = "INSERT INTO users VALUES ('"+username+"', '"+password+"')"; //table used for authentication
		Connection connection = ConnectionFactory.connectUser();
		Statement stmt= connection.createStatement();
		stmt.executeUpdate(sql);
		
		  Random rand = new Random();  //assigns a random account number
	      int limit = 9999999;
	      int acctNum = rand.nextInt(limit);
	      
	      String sql2 = "INSERT INTO accounts VALUES ("+acctNum+")"; //account info
	      String sql3 = "UPDATE accounts SET username='"+username+"', balance="+balance+" WHERE acctnum="+acctNum;
	      Statement stmt2 = connection.createStatement();
	      Statement stmt3 = connection.createStatement(); 
	      stmt2.executeUpdate(sql2);
	      stmt3.executeUpdate(sql3);

		
		
	}
	
}
