package application.Model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorAlert {
	private Alert alert = new Alert(AlertType.ERROR);;
	private static ErrorAlert Instance;
	private String header;
	private String content;

	private ErrorAlert() {
	};

	public static ErrorAlert getInstance() {
		if (Instance == null) {
			Instance = new ErrorAlert();
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

	// ref
	// https://java.hotexamples.com/examples/javafx.scene.control/Alert/getDialogPane/java-alert-getdialogpane-method-examples.html
	public void alertNumberFormat() {
		alert.getDialogPane().setPrefSize(300, 280);
		alert.setContentText(
				"Error: Invalid Input, please input only numbers for the post ID, likes and shares fields");
		alert.setHeaderText("Invalid Number Input");
		alert.showAndWait();
	}

	public void alertNegativeNumber() {
		alert.setContentText(
				"Error: Invalid Input, please input only positive numbers for the likes and shares fields");
		alert.setHeaderText("Invalid Negative Number Input");
		alert.showAndWait();
	}

	// ref
	// https://java.hotexamples.com/examples/javafx.scene.control/Alert/getDialogPane/java-alert-getdialogpane-method-examples.html
	public void alertInvalidDateTimeFormat() {
		alert.getDialogPane().setPrefSize(300, 280);
		alert.setContentText("Error: Invalid Input, please input date and time in the DD/MM/YYYY HH:MM format");
		alert.setHeaderText("Invalid Date-Time Input");
		alert.showAndWait();
	}

	public void alertPostIdExist() {
		alert.setContentText("Error: The post ID is already exists in the database");
		alert.setHeaderText("Invalid Post ID Input");
		alert.showAndWait();
	}

	public void alertPostNotFound() {
		alert.setContentText("Error: The post ID is not exist or you don't have access to it");
		alert.setHeaderText("Error Post ID Input");
		alert.showAndWait();
	}
}
