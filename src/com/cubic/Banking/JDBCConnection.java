package com.cubic.Banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "Glissade13");

		return con;

	}
}
