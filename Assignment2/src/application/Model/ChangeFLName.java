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
public class ChangeFLName {
	private static ChangeFLName Instance;

	private ChangeFLName() {
	};

	public static ChangeFLName getInstance() {
		if (Instance == null) {
			Instance = new ChangeFLName();
		}
		return Instance;
	}

	/**
	 * Method to check the input blank fields
	 * 
	 * @param newFLName
	 * @param reTypeFLName
	 * @throws BlankInputException
	 */
	public void checkBlankField(String newFLName, String reTypeFLName) throws BlankInputException {
		if (newFLName.isBlank() || reTypeFLName.isBlank()) {
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
	 * @param newFirstName    or newLastName
	 * @param reTypeFirstName or reTypeLastName
	 * @boolean check if true = firstname,else lastname
	 * @return
	 */
	public boolean checkInputWhiteSpace(String newFLName, String reTypeFLName, boolean check) {
		ErrorAlert alert = ErrorAlert.getInstance();
		if (checkWhiteSpace(newFLName)) { // error if contains whitespace
			if (check) {
				alert.alertWhiteSpaceFound("new first name");
			} else {
				alert.alertWhiteSpaceFound("new last name");
			}
			return true;
		} else if (checkWhiteSpace(reTypeFLName)) {
			if (check) {
				alert.alertWhiteSpaceFound("retype first name");
			} else {
				alert.alertWhiteSpaceFound("retype last name");
			}
			return true;
		}
		return false;
	}

	/**
	 * Method to check matching new first/last name from the two fields
	 * 
	 * @param newFirstName
	 * @param reTypeFirstName
	 * @check if true = firstname,else lastname
	 * @throws RetypeException
	 */
	public void checkMatchingRetype(String newFLName, String reTypeFLName, boolean check) throws RetypeException {
		if (!newFLName.equals(reTypeFLName)) {
			if (check) {
				throw new RetypeException("New First Name mismatches");
			} else {
				throw new RetypeException("New Last Name mismatches");
			}
		}
	}
}
