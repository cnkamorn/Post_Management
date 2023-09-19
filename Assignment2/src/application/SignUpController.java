package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

	@FXML
	private void signUp(ActionEvent event) {
		if (!errorAlert()) {
			System.out.println("test");
		} else {
			System.out.println("eoe");
		}
	}

	public boolean errorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		if (firstname.getText().isBlank() || lastname.getText().isBlank() || password.getText().isBlank()
				|| username.getText().isBlank()) {
			alert.setContentText("Please input all fields");
			alert.setHeaderText("Blank Input Error");
			alert.showAndWait();
			return true;
		} else if (username.getText().equals("tee")) {
			alert.setContentText("Username is exist in our database. Please input a new username");
			alert.setHeaderText("Username Input Error");
			alert.showAndWait();
			return true;
		}
		return false;
	}
}
