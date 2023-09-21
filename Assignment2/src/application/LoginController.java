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
		if (verifyLogin(userNameField, passwordField)) {
			loginButton.getScene().getWindow().hide();
			currentUserName = userNameField;
			dbc.userDashBoardControl(userNameField, passwordField);
		} else if (userNameField.isBlank() || passwordField.isBlank()) {
			error.alertBlankInput();
		} else {
			error.alertLogin();
		}

		// if not vip

		// if vip
		// dashboard.showViewVip();
	}

	@FXML
	private void register(ActionEvent event) {
		SignUpVIew signUpPage = new SignUpVIew();
		signUpPage.showView();
	}

	private boolean verifyLogin(String username, String password) {
		// if username matches > true else false
		UserDatabase udb = UserDatabase.getInstance();
		String query = "SELECT username, password FROM " + udb.TABLE_NAME + " " + "WHERE username = '" + username
				+ "' AND password ='" + password + "' ";
		try {
			Connection con = udb.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			con.close();
			stmt.close();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
