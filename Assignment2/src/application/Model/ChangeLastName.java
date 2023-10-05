package application.Model;

import application.Exception.BlankInputException;
import application.Exception.RetypeException;

/**
 * This class is a change last name class for account setting page It's an
 * extended class from the main Account dash board controller class
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class ChangeLastName {
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
		ErrorAlert alert = ErrorAlert.getInstance();
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
