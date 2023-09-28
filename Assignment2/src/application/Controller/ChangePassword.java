package application.Controller;

import application.Exception.BlankInputException;
import application.Exception.RetypeException;
import application.Exception.WrongPasswordException;

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

	public void checkMatchingRetypePassword(String newPassword, String reTypePassword) throws RetypeException {
		if (!newPassword.equals(reTypePassword)) {
			throw new RetypeException("New Username mismatches");
		}
	}

	public void checkBlankField(String currentPassword, String newPassword, String reTypePassword)
			throws BlankInputException {
		if (currentPassword.isBlank() || newPassword.isBlank() || reTypePassword.isBlank()) {
			throw new BlankInputException("Error : Blank Input found");
		}
	}

	public void checkCurrentPassword(String currentPassword) throws WrongPasswordException {
		if (!currentUserAccount.getPassword().equals(currentPassword)) {
			throw new WrongPasswordException("Error : Wrong current password input");
		}
	}

	public boolean checkWhiteSpace(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.isWhitespace(text.charAt(i))) {
				return true;
			}
		}
		return false;
	}

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
