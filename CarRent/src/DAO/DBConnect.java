package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	//use your credentials for the three variables below
	private static String url = "jdbc:mysql://localhost:3306/CarRental";
	private static String user = "root";
	private static final String password = "*insert your password here*";
	private Connection connection;
	private Statement statement;
	private ResultSet rs;
	
	public Connection setConnection () {
		try {
			this.connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
		return connection;
	}
	public ResultSet getResult (String sql, Connection connection) {
		this.connection = connection;
		try {
			this.statement = connection.prepareStatement(sql);
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
		return rs;
	}

}
