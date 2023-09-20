package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDatabase extends Database<Account> {
	private static final String DB_URL = "jdbc:sqlite:data.db"; // driver
	final static String TABLE_NAME = "User";
//singleton
	private static UserDatabase Instance;

	private UserDatabase() {
	};

	public static UserDatabase getInstance() {
		if (Instance == null) {
			Instance = new UserDatabase();
		}
		return Instance;
	}

	// run this to create a table
	@Override
	protected void createTableUser() {

		try (Connection con = getConnection(); Statement stmt = con.createStatement();) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(username VARCHAR(20) NOT NULL,"
					+ "password VARCHAR(20) NOT NULL," + "first_name VARCHAR(20) NOT NULL,"
					+ "last_name VARCHAR(20) NOT NULL," + "user_plan VARCHAR(3) NOT NULL," + "PRIMARY KEY (username))");
			con.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) throws SQLException {
		UserDatabase a = UserDatabase.getInstance();
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

	@Override
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
			con.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.getMessage();
		}
		return false;
	}

	@Override
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
}
