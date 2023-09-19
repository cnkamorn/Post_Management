package application;

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

	@FXML
	private void login(ActionEvent event) {
		DashBoardView dashboard = new DashBoardView();
		String userNamefield = username.getText();
		String passwordfield = password.getText();
		// to connect with DB
		if (userNamefield.isEmpty() || passwordfield.isBlank()) {
			error.alertLogin();
		} else {
			// successfully login
			loginButton.getScene().getWindow().hide();
			dashboard.showView();

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
}
