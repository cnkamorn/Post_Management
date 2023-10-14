package application.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Exception.BlankInputException;
import application.Exception.RetypeException;
import application.Exception.UsernameMismatchException;
import application.Model.DAO.UserDAO;

/**
 * This class is a change username class for account setting page It's an
 * extended class from the main Account dash board controller class
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class ChangeUsername {

	ErrorAlert alert = ErrorAlert.getInstance();
	private static ChangeUsername Instance;

	private ChangeUsername() {
	};

	public static ChangeUsername getInstance() {
		if (Instance == null) {
			Instance = new ChangeUsername();
		}
		return Instance;
	}

	/**
	 * Method to check if the username field is matched with the currect login user
	 * 
	 * @param currentUsername
	 * @param currentUserAccount
	 * @throws UsernameMismatchException
	 */
	public void checkMatchingCurrentUsername(String currentUsername, String currentUserAccount)
			throws UsernameMismatchException {
		if (!currentUsername.equals(currentUserAccount)) {
			throw new UsernameMismatchException("Current Username mismatches");
		}
	}

	/**
	 * Method to check if the input fields are matched.
	 * 
	 * @param currentUsername
	 * @param currentUserAccount
	 * @throws UsernameMismatchException
	 */
	public void checkMatchingRetypeUsername(String newUsername, String reTypeUsernameField) throws RetypeException {
		if (!newUsername.equals(reTypeUsernameField)) {
			throw new RetypeException("New Username mismatches");
		}
	}

	/**
	 * Method to check if the username is exist
	 * 
	 * @param currentUsername
	 * @param newUsername
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean checkUsernameExist(String currentUsername, String newUsername) throws SQLException {
		String query = "SELECT username FROM User WHERE username='" + newUsername + "' AND username != '"
				+ currentUsername + "';";
		Connection con = UserDAO.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		con.close();
		stmt.close();
		if (rs.next()) {
			return false;
		}
		return true;
	}

	/**
	 * Method to check the blank fields
	 * 
	 * @param currentUsername
	 * @param newUsername
	 * @param retypeUsername
	 * @throws BlankInputException
	 */
	public void checkBlankField(String currentUsername, String newUsername, String retypeUsername)
			throws BlankInputException {
		if (currentUsername.isBlank() || newUsername.isBlank() || retypeUsername.isBlank()) {
			throw new BlankInputException("Error : Blank Input found");
		}
	}

	/**
	 * Method to check the white space
	 * 
	 * @param text
	 * @return boolean
	 */
	public boolean checkWhiteSpace(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.isWhitespace(text.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to check if there's any input white space
	 * 
	 * @param currentUsername
	 * @param newUsername
	 * @param reTypeNewUsername
	 * @return boolean
	 */
	public boolean checkInputWhiteSpace(String currentUsername, String newUsername, String reTypeNewUsername) {
		ErrorAlert alert = ErrorAlert.getInstance();
		if (checkWhiteSpace(currentUsername)) { // error if contains whitespace
			alert.alertWhiteSpaceFound("current username");
			return true;
		} else if (checkWhiteSpace(newUsername)) {
			alert.alertWhiteSpaceFound("new username");
			return true;
		} else if (checkWhiteSpace(reTypeNewUsername)) {
			alert.alertWhiteSpaceFound("retype username");
			return true;
		}
		return false;
	}

}
