package application;

import application.Exception.BlankInputException;
import application.Exception.RetypeException;

public class ChangeFirstName extends AccountDashboardController {
	private static ChangeFirstName Instance;

	private ChangeFirstName() {
	};

	public static ChangeFirstName getInstance() {
		if (Instance == null) {
			Instance = new ChangeFirstName();
		}
		return Instance;
	}

	public void checkBlankField(String newFirstName, String reTypeFirstName) throws BlankInputException {
		if (newFirstName.isBlank() || reTypeFirstName.isBlank()) {
			throw new BlankInputException("Error : Blank Input found");
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

	public boolean checkInputWhiteSpace(String newFirstName, String reTypeFirstName) {
		if (checkWhiteSpace(newFirstName)) { // error if contains whitespace
			alert.alertWhiteSpaceFound("new first name");
			return true;
		} else if (checkWhiteSpace(reTypeFirstName)) {
			alert.alertWhiteSpaceFound("retype first name");
			return true;
		}
		return false;
	}

	public void checkMatchingRetypeFirstName(String newFirstName, String reTypeFirstName) throws RetypeException {
		if (!newFirstName.equals(reTypeFirstName)) {
			throw new RetypeException("New First Name mismatches");
		}
	}
}
