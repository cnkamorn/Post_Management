package application.Controller;

import application.Exception.BlankInputException;
import application.Exception.RetypeException;

public class ChangeLastName extends AccountDashboardController {
	private static ChangeLastName Instance;

	private ChangeLastName() {
	};

	public static ChangeLastName getInstance() {
		if (Instance == null) {
			Instance = new ChangeLastName();
		}
		return Instance;
	}

	public void checkBlankField(String newLastName, String reTypeLastName) throws BlankInputException {
		if (newLastName.isBlank() || reTypeLastName.isBlank()) {
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

	public boolean checkInputWhiteSpace(String newLastName, String reTypeLastName) {
		if (checkWhiteSpace(newLastName)) { // error if contains whitespace
			alert.alertWhiteSpaceFound("new last name");
			return true;
		} else if (checkWhiteSpace(reTypeLastName)) {
			alert.alertWhiteSpaceFound("retype last name");
			return true;
		}
		return false;
	}

	public void checkMatchingRetypeLastName(String newLastName, String reTypeLastName) throws RetypeException {
		if (!newLastName.equals(reTypeLastName)) {
			throw new RetypeException("New Last Name mismatches");
		}
	}

}
