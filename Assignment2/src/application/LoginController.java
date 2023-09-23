package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Exception.InvalidLoginException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private Button loginButton;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	private ErrorView error = new ErrorView();

	public static String currentUserName;

	@FXML
	private void login(ActionEvent event) {
		DashBoardController dbc = new DashBoardController();
		String userNameField = username.getText();
		String passwordField = password.getText();
		// to connect with DB
		try {
			if (verifyLogin(userNameField, passwordField)) {
				loginButton.getScene().getWindow().hide();
				currentUserName = userNameField;
				dbc.userDashBoardControl(userNameField, passwordField);
			} else if (userNameField.isBlank() || passwordField.isBlank()) {
				error.alertBlankInput();
			}
		} catch (InvalidLoginException e) {
			// TODO Auto-generated catch block
			error.alertLogin();
			System.out.println(e.getMessage());
		}

		// if not vip

		// if vip
		// dashboard.showViewVip();
	}

	@FXML
	private void register(ActionEvent event) {
		SignUpView signUpPage = SignUpView.getInstance();
		signUpPage.showView();
	}

	private boolean verifyLogin(String username, String password) throws InvalidLoginException {
		String query = "SELECT username, password FROM " + UserDatabase.TABLE_NAME + " " + "WHERE username = '"
				+ username + "' AND password ='" + password + "' ";
		try {
			Connection con = UserDatabase.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			con.close();
			stmt.close();
			if (!rs.next()) {
				throw new InvalidLoginException("Invalid username or password");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
}
