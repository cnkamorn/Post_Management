package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

	@FXML
	private void login(ActionEvent event) {
		Alert alert;
		DashBoardView dashboard = new DashBoardView();
		String userNamefield = username.getText();
		String passwordfield = password.getText();
		// to connect with DB
		if (userNamefield.isEmpty() || passwordfield.isBlank()) {
			alert = new Alert(AlertType.ERROR);
			alert.setContentText("Invalid username or password");
			alert.showAndWait();
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
