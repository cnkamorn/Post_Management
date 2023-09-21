package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private UserDatabase userDb = UserDatabase.getInstance();
	private Account userAccount = Account.getInstance();

	@FXML
	private boolean signUp(ActionEvent event) {
		userAccount.setUsername(username.getText());
		userAccount.setPassword(password.getText());
		userAccount.setFirstname(firstname.getText());
		userAccount.setLastname(lastname.getText());
		userAccount.setUserPlan("NML");
		if (checkBlank()) { // check input blank?
			error.alertBlankInput();
			return false;
		} else if (checkInputWhiteSpace(username.getText(), password.getText(), firstname.getText(),
				lastname.getText())) { // check any white spaces?
			return false;
		} else if (checkUsernameExists(username.getText())) { // checkusernameexist?
			error.alertUsernameExists();
			return false;
		}
		userDb.insertUser(userAccount);
		SuccessView successAlert = new SuccessView();
		successAlert.alertRegisterSuccess();
		signup.getScene().getWindow().hide();
		return true;
	}

	public boolean checkBlank() {
		if (firstname.getText().isBlank() || lastname.getText().isBlank() || password.getText().isBlank()
				|| username.getText().isBlank()) {
			return true;
		}
		return false;
	}

	public boolean checkWhiteSpace(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.isWhitespace(text.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public boolean checkInputWhiteSpace(String username, String password, String firstname, String lastname) {
		if (checkWhiteSpace(username)) { // error if contains whitespace
			error.alertWhiteSpaceFound("Username");
			return true;
		} else if (checkWhiteSpace(password)) {
			error.alertWhiteSpaceFound("Password");
			return true;
		} else if (checkWhiteSpace(firstname)) {
			error.alertWhiteSpaceFound("First name");
			return true;
		} else if (checkWhiteSpace(lastname)) {
			error.alertWhiteSpaceFound("Last name");
			return true;
		}
		return false;
	}

	public boolean checkUsernameExists(String username) {
		String query = "SELECT username FROM User WHERE username='" + username + "'";
		try {
			Connection con = UserDatabase.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			con.close();
			stmt.close();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
