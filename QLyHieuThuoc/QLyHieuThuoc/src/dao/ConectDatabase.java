package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectDatabase {
	public static Connection con = null;
	private static ConectDatabase instance = new ConectDatabase();

	public static ConectDatabase getInstance() {
		return instance;
	}

	public void connect() {
		String url = "jdbc:mysql://localhost:3306/qlht";
		String user = "root"; // Sửa lại user nếu khác
		String password = "12345689"; // Sửa lại password nếu khác
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void disconnect() {
		if(con != null) {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() {
		return con;
	}
}