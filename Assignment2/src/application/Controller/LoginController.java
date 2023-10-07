package application.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Exception.InvalidLoginException;
import application.Model.ErrorAlert;
import application.Model.DAO.UserDatabase;
import application.View.RegisterView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * This class is a login controller class It contains related methods about the
 * login process
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class LoginController {
	@FXML
	private Button loginButton;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	ErrorAlert alert = ErrorAlert.getInstance();
	public static String currentUserName;

	@FXML
	private void login(ActionEvent event) {
		DashBoardController dbc = new DashBoardController();
		String userNameField = username.getText();
		String passwordField = password.getText();
		try {
			if (verifyLogin(userNameField, passwordField)) {
				loginButton.getScene().getWindow().hide();
				currentUserName = userNameField;
				dbc.userDashBoardControl(userNameField, passwordField);
			} else if (userNameField.isBlank() || passwordField.isBlank()) {
				alert.alertBlankInput();
			}
		} catch (InvalidLoginException e) {
			alert.alertLogin();
			System.out.println(e.getMessage());
		}
	}

	@FXML
	private void register(ActionEvent event) {

		RegisterView registerScene = RegisterView.getInstance();
		registerScene.getScene();
	}

	private boolean verifyLogin(String username, String password) throws InvalidLoginException {
		String query = "SELECT username, password FROM User WHERE username = '" + username + "' AND password ='"
				+ password + "'; ";
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
