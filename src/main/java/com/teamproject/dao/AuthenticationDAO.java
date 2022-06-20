package com.teamproject.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.teamproject.util.ConnectionFactory;

public interface AuthenticationDAO {
	
	public boolean authenticateUser(String username, String password);
}
