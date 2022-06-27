package com.teamproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.teamproject.util.ConnectionFactory;
import com.teamproject.service.JWTServiceImpl;

public class AuthenticationDAO {

	public AuthenticationDAO(){
		super();
	}
	
	private static String token;
	
	public void setToken(String token) {
		
		this.token = token;
	}
	
	public String getToken() {
		String token = this.token;
		return token;
	}
	
	public JWTServiceImpl jwt = new JWTServiceImpl();	
	public boolean authenticateUser(String username, String password) {
		try {
		Connection conn = ConnectionFactory.connectUser();
		String SQL = "SELECT * FROM users WHERE username = ? and password = ?";
			try(PreparedStatement ps = conn.prepareStatement(SQL)){
				
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					if (!rs.wasNull()) {
						if (rs.getBoolean("ismanager")) {//table contains employees and managers	
								this.setToken(jwt.createJWT(username, "manager"));
								return true;
								}
							this.setToken(jwt.createJWT(username, "employee"));
							return true;
						} 
					
					
			}
			
			} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean verify() {
		
		String role = "manager";
		String token = JWTServiceImpl.getRole(this.getToken());
		if (!token.equalsIgnoreCase(role)) {
		return false;
		} else {
		return true;
		}
		
		
	}
	
}
	

