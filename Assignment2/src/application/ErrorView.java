package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorView {
	private Alert alert = new Alert(AlertType.ERROR);;
	private static ErrorView Instance;

	private ErrorView() {
	};

	public static ErrorView getInstance() {
		if (Instance == null) {
			Instance = new ErrorView();
		}
		return Instance;
	}

	public void alertLogin() {
		alert.setHeaderText("Invalid username or password");
		alert.setContentText("Invalid username or password");
		alert.showAndWait();
	}

	public void alertBlankInput() {
		alert.setContentText("Please input all fields");
		alert.setHeaderText("Blank Input Error");
		alert.showAndWait();
	}

	public void alertUsernameExists() {
		alert.setContentText("This username is already exist in our database.");
		alert.setHeaderText("Username Input Error");
		alert.showAndWait();
	}

	public void alertWhiteSpaceFound(String field) {
		alert.setContentText("Error: " + field + " should not contains white space.");
		alert.setHeaderText("White space Error");
		alert.showAndWait();
	}

	public void alertInvalidUsername() {
		alert.setContentText("Error: Invalid Username");
		alert.setHeaderText("Invalid Username");
		alert.showAndWait();
	}

	public void alertInvalidPassword() {
		alert.setContentText("Error: Invalid Current Password");
		alert.setHeaderText("Invalid Password");
		alert.showAndWait();
	}

	public void alertRetypePassword() {
		alert.setContentText("Error: Invalid Retype Password");
		alert.setHeaderText("Invalid Retype Password");
		alert.showAndWait();
	}

	public void alertRetypeUsername() {
		alert.setContentText("Error: Invalid Retype Username");
		alert.setHeaderText("Invalid Retype Username");
		alert.showAndWait();
	}

	public void alertRetypeFirstname() {
		alert.setContentText("Error: Invalid Retype First name");
		alert.setHeaderText("Invalid Retype First name");
		alert.showAndWait();
	}

	public void alertRetypeLastname() {
		alert.setContentText("Error: Invalid Retype Last name");
		alert.setHeaderText("Invalid Retype Last name");
		alert.showAndWait();
	}
}
