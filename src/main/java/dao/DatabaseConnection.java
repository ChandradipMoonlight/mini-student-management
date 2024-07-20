package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String SQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "Dipak@11";

	private static final String URL = "jdbc:mysql://localhost/mydb";

	private static Connection connection = null;

	public static void initConnection() {

		try {
			Class.forName(SQL_DRIVER);
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("SQL connection is ready!");
		} catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connection==null) {
			initConnection();
		}
		return connection;
	}

	public static void stopDbInstance() {
		try {
			connection.close();
			System.out.println("Db connection closed!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
