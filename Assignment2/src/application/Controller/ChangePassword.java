package application.Controller;

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
public class ChangePassword extends AccountDashboardController {
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
	 * 
	 * @param newPassword
	 * @param reTypePassword
	 * @throws RetypeException
	 */
	public void checkMatchingRetypePassword(String newPassword, String reTypePassword) throws RetypeException {
		if (!newPassword.equals(reTypePassword)) {
			throw new RetypeException("New Username mismatches");
		}
	}

	/**
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
	 * 
	 * @param currentPassword
	 * @throws WrongPasswordException
	 */
	public void checkCurrentPassword(String currentPassword) throws WrongPasswordException {
		if (!currentUserAccount.getPassword().equals(currentPassword)) {
			throw new WrongPasswordException("Error : Wrong current password input");
		}
	}

	/**
	 * 
	 * @param text
	 * @return
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
	 * 
	 * @param currentPassword
	 * @param newPassword
	 * @param reTypePassword
	 * @return
	 */
	public boolean checkInputWhiteSpace(String currentPassword, String newPassword, String reTypePassword) {
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
