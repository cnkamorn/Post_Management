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
	public static void createTableUser() {
		try (Connection con = getConnection(); Statement stmt = con.createStatement();) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME
					+ "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(20) NOT NULL,"
					+ "password VARCHAR(20) NOT NULL," + "first_name VARCHAR(20) NOT NULL,"
					+ "last_name VARCHAR(20) NOT NULL," + "user_plan VARCHAR(3) NOT NULL)");
			con.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) throws SQLException {
	}
	// to create table post

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL); // connection
	}

	public boolean insertUser(Account user) {
		String sql = "INSERT INTO " + TABLE_NAME + " (username, password, first_name, last_name,user_plan)"
				+ " VALUES (?, ?, ?, ?, ?)";
		try (Connection con = UserDatabase.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFirstname());
			stmt.setString(4, user.getLastname());
			stmt.setString(5, user.getUserPlan());
			int result = stmt.executeUpdate();
			con.close();
			stmt.close();
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

	public String queryUserId(String username) {
		String userId = "";
		try (Connection con = UserDatabase.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "SELECT user_id FROM " + TABLE_NAME + " WHERE username = '" + username + "';";
			ResultSet rs = stmt.executeQuery(sql);
			con.close();
			stmt.close();
			if (rs.next()) {
				userId += rs.getString("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	public boolean updateUsername(String currentUsername, String newUsername) {
		try (Connection con = UserDatabase.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "UPDATE " + TABLE_NAME + " SET username = '" + newUsername + "' WHERE user_id = '"
					+ queryUserId(currentUsername) + "';";
			int result = stmt.executeUpdate(sql);
			con.close();
			stmt.close();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updatePassword(String currentUsername, String newPassword) {
		try (Connection con = UserDatabase.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "UPDATE " + TABLE_NAME + " SET password = '" + newPassword + "' WHERE user_id = '"
					+ queryUserId(currentUsername) + "';";
			int result = stmt.executeUpdate(sql);
			con.close();
			stmt.close();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updatePlan(String username) {
		try (Connection con = UserDatabase.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "UPDATE " + TABLE_NAME + " SET user_plan = 'VIP' WHERE username = '" + username + "';";
			int result = stmt.executeUpdate(sql);
			con.close();
			stmt.close();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateFirstName(String currentUsername, String firstName) {
		try (Connection con = UserDatabase.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "UPDATE " + TABLE_NAME + " SET first_name = '" + firstName + "' WHERE username = '"
					+ currentUsername + "';";
			int result = stmt.executeUpdate(sql);
			con.close();
			stmt.close();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateLastName(String currentUsername, String lastName) {
		try (Connection con = UserDatabase.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "UPDATE " + TABLE_NAME + " SET last_name = '" + lastName + "' WHERE username = '"
					+ currentUsername + "';";
			int result = stmt.executeUpdate(sql);
			con.close();
			stmt.close();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
