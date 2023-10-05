package application.Model;

import application.Exception.BlankInputException;
import application.Exception.RetypeException;

/**
 * This class is a change first name class for account setting page It's an
 * extended class from the main Account dash board controller class
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class ChangeFirstName {
	private static ChangeFirstName Instance;

	private ChangeFirstName() {
	};

	public static ChangeFirstName getInstance() {
		if (Instance == null) {
			Instance = new ChangeFirstName();
		}
		return Instance;
	}

	/**
	 * Method to check the input blank fields
	 * 
	 * @param newFirstName
	 * @param reTypeFirstName
	 * @throws BlankInputException
	 */
	public void checkBlankField(String newFirstName, String reTypeFirstName) throws BlankInputException {
		if (newFirstName.isBlank() || reTypeFirstName.isBlank()) {
			throw new BlankInputException("Error : Blank Input found");
		}
	}

	/**
	 * Method to check the white space
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
	 * Method to alert if the white space is found
	 * 
	 * @param newFirstName
	 * @param reTypeFirstName
	 * @return
	 */
	public boolean checkInputWhiteSpace(String newFirstName, String reTypeFirstName) {
		ErrorAlert alert = ErrorAlert.getInstance();
		if (checkWhiteSpace(newFirstName)) { // error if contains whitespace
			alert.alertWhiteSpaceFound("new first name");
			return true;
		} else if (checkWhiteSpace(reTypeFirstName)) {
			alert.alertWhiteSpaceFound("retype first name");
			return true;
		}
		return false;
	}

	/**
	 * Method to check matching first name from the two fields
	 * 
	 * @param newFirstName
	 * @param reTypeFirstName
	 * @throws RetypeException
	 */
	public void checkMatchingRetypeFirstName(String newFirstName, String reTypeFirstName) throws RetypeException {
		if (!newFirstName.equals(reTypeFirstName)) {
			throw new RetypeException("New First Name mismatches");
		}
	}
}
