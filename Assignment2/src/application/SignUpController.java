package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

	@FXML
	private TextField firstname;

	@FXML
	private TextField lastname;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	@FXML
	private Button signup;

	private ErrorView error = new ErrorView();
	private UserDatabase userDb = new UserDatabase();
	private Account userAccount;

	@FXML
	private void signUp(ActionEvent event) {
		String firstNameField = firstname.getText();
		String lastNameField = lastname.getText();
		String usernameField = username.getText();
		String passwordField = password.getText();
		userAccount = new Account(firstNameField, lastNameField, usernameField, passwordField, "NML"); // normal user
		if (checkBlank()) { // check input blank?
			error.alertBlankInput();
		} else if (!userDb.insertRow(userAccount)) { // error while insert
			error.alertUsernameExists();
		} else {
			SuccessView successAlert = new SuccessView();
			successAlert.alertRegisterSuccess();
			signup.getScene().getWindow().hide();
		}
	}

	public boolean checkBlank() {
		if (firstname.getText().isBlank() || lastname.getText().isBlank() || password.getText().isBlank()
				|| username.getText().isBlank()) {
			return true;
		}
		return false;
	}
}
