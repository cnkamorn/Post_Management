package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Exception.BlankInputException;
import application.Exception.UsernameMismatchException;
import application.Exception.UsernameRetypeException;

public class ChangeUsername extends AccountDashboardController {

	private static ChangeUsername Instance;

	private ChangeUsername() {
	};

	public static ChangeUsername getInstance() {
		if (Instance == null) {
			Instance = new ChangeUsername();
		}
		return Instance;
	}

	public void checkMatchingCurrentUsername(String currentUsername, String currentUserAccount)
			throws UsernameMismatchException {
		if (!currentUsername.equals(currentUserAccount)) {
			throw new UsernameMismatchException("Current Username mismatches");
		}
	}

	public void checkMatchingRetypeUsername(String newUsername, String reTypeUsernameField)
			throws UsernameRetypeException {
		if (!newUsername.equals(reTypeUsernameField)) {
			throw new UsernameRetypeException("New Username mismatches");
		}
	}

	public void checkUsernameExist(String currentUsername, String newUsername) {
		String query = "SELECT username FROM User WHERE username='" + newUsername + "' AND username != '"
				+ currentUsername + "';";
		try {
			Connection con = UserDatabase.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			con.close();
			stmt.close();
			if (rs.next()) {
				alert.alertUsernameExists();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void checkBlankField(String currentUsername, String newUsername, String retypeUsername)
			throws BlankInputException {
		if (currentUsername.isBlank() || newUsername.isBlank() || retypeUsername.isBlank()) {
			throw new BlankInputException("Error : Blank Input found");
		}
	}
}
