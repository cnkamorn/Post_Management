package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorView {
	private Alert alert = new Alert(AlertType.ERROR);;

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
}
