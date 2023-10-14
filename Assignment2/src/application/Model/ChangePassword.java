package application.Model;

import application.Exception.BlankInputException;
import application.Exception.RetypeException;
import application.Exception.WrongPasswordException;

/**
 * This class is a change password class for account setting page It's an
 * extended class from the main Account dash board controller class
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class ChangePassword {
	private static ChangePassword Instance;

	private ChangePassword() {
	};

	public static ChangePassword getInstance() {
		if (Instance == null) {
			Instance = new ChangePassword();
		}
		return Instance;
	}

	/**
	 * Method to check if the retype password is matched with the password field
	 * 
	 * @param newLastName
	 * @param reTypeLastName
	 * @throws RetypeException
	 */
	public void checkMatchingRetypePassword(String newPassword, String reTypePassword) throws RetypeException {
		if (!newPassword.equals(reTypePassword)) {
			throw new RetypeException("New Username mismatches");
		}
	}

	/**
	 * Method to check if there is any blank field
	 * 
	 * @param currentPassword
	 * @param newPassword
	 * @param reTypePassword
	 * @throws BlankInputException
	 */
	public void checkBlankField(String currentPassword, String newPassword, String reTypePassword)
			throws BlankInputException {
		if (currentPassword.isBlank() || newPassword.isBlank() || reTypePassword.isBlank()) {
			throw new BlankInputException("Error : Blank Input found");
		}
	}

	/**
	 * Method to check the password
	 * 
	 * @param currentPassword
	 * @throws WrongPasswordException
	 */
	public void checkCurrentPassword(String loggedinUserPassword, String currentPassword)
			throws WrongPasswordException {
		if (!loggedinUserPassword.equals(currentPassword)) {
			throw new WrongPasswordException("Error : Wrong current password input");
		}
	}

	/**
	 * Method to check if the string contains white space
	 * 
	 * @param newLastName
	 * @param reTypeLastName
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
	 * Method to check if the input contains any white space
	 * 
	 * @param currentPassword
	 * @param newPassword
	 * @param reTypePassword
	 * @return boolean
	 */
	public boolean checkInputWhiteSpace(String currentPassword, String newPassword, String reTypePassword) {
		ErrorAlert alert = ErrorAlert.getInstance();
		if (checkWhiteSpace(currentPassword)) { // error if contains whitespace
			alert.alertWhiteSpaceFound("current password");
			return true;
		} else if (checkWhiteSpace(newPassword)) {
			alert.alertWhiteSpaceFound("new password");
			return true;
		} else if (checkWhiteSpace(reTypePassword)) {
			alert.alertWhiteSpaceFound("retype password");
			return true;
		}
		return false;
	}
}
