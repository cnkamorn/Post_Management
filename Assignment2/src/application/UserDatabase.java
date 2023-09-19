package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDatabase {
	private static final String DB_URL = "jdbc:sqlite:data.db"; // driver

	final static String TABLE_NAME = "User";

	// run this to create a table
	private static void createTableUser() {

		try (Connection con = getConnection(); Statement stmt = con.createStatement();) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(username VARCHAR(20) NOT NULL,"
					+ "password VARCHAR(20) NOT NULL," + "first_name VARCHAR(20) NOT NULL,"
					+ "last_name VARCHAR(20) NOT NULL," + "user_plan VARCHAR(3) NOT NULL," + "PRIMARY KEY (username))");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) throws SQLException {
		UserDatabase a = new UserDatabase();
		Account b = new Account("tee", "pass", "c", "a", "VIP");
		a.insertRow(b);
		/*
		 * Connection v = getConnection(); Statement d = v.createStatement();
		 * d.executeUpdate("DROP TABLE User");
		 */
	}
	// to create table post

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL); // connection
	}

	public boolean deleteRow() {
		return false;
	}

	public boolean insertRow(Account user) {
		String sql = "INSERT INTO " + TABLE_NAME + " (username, password, first_name, last_name,user_plan)"
				+ " VALUES (?, ?, ?, ?, ?)";

		try (Connection con = UserDatabase.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFirstname());
			stmt.setString(4, user.getLastname());
			stmt.setString(5, user.getUserPlan());

			int result = stmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return false;
	}

	public boolean updateRow() {
		try (Connection con = UserDatabase.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "Select * from " + TABLE_NAME + " WHERE ";
			int result = stmt.executeUpdate(sql);
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void queryRow(String sql) {
		try (Connection con = UserDatabase.getConnection(); Statement stmt = con.createStatement();) {
			String query = "SELECT * FROM " + TABLE_NAME;
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					System.out.printf("Id: %d | Student Number: %s | First Name: %s | Last Name: %s\n",
							resultSet.getInt("id"), resultSet.getString("student_number"),
							resultSet.getString("first_name"), resultSet.getString("last_name"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
