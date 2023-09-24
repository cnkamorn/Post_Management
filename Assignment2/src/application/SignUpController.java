package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Exception.BlankInputException;
import application.Exception.UsernameExistException;
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

	private ErrorView error = ErrorView.getInstance();
	private UserDatabase userDb = UserDatabase.getInstance();
	private Account userAccount = Account.getInstance();

	@FXML
	private void signUp(ActionEvent event) {
		userAccount.setUsername(username.getText());
		userAccount.setPassword(password.getText());
		userAccount.setFirstname(firstname.getText());
		userAccount.setLastname(lastname.getText());
		userAccount.setUserPlan("NML");
		try {
			if (!checkBlank() && !checkInputWhiteSpace(username.getText(), password.getText(), firstname.getText(),
					lastname.getText()) && !checkUsernameExists(username.getText())) { // check input blank?
				userDb.insertUser(userAccount);
				SuccessView successAlert = SuccessView.getInstance();
				successAlert.alertRegisterSuccess();
				signup.getScene().getWindow().hide();
			}
		} catch (BlankInputException e) {
			error.alertBlankInput();
			System.out.println(e.getMessage());
		} catch (UsernameExistException e) {
			error.alertUsernameExists();
			System.out.println(e.getMessage());
		}
	}

	public boolean checkBlank() throws BlankInputException {
		if (firstname.getText().isBlank() || lastname.getText().isBlank() || password.getText().isBlank()
				|| username.getText().isBlank()) {
			throw new BlankInputException("The input fields can't be empty");
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

	public boolean checkUsernameExists(String username) throws UsernameExistException {
		String query = "SELECT username FROM User WHERE username='" + username + "'";
		try {
			Connection con = UserDatabase.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			con.close();
			stmt.close();
			if (rs.next()) {
				throw new UsernameExistException("Error : Username exists in the database");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
