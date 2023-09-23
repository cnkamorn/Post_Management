package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SuccessView {
	private Alert alert = new Alert(AlertType.INFORMATION);;

	public void alertRegisterSuccess() {
		alert.setHeaderText("Successfully Sign-Up");
		alert.setContentText("Your account has been created successfully!");
		alert.showAndWait();
	}

	public void alertUpdateUsernameSuccess() {
		alert.setHeaderText("Successfully Changed Username");
		alert.setContentText("Your username has been updated successfully!");
		alert.showAndWait();
	}

	public void alertUpdatePasswordSuccess() {
		alert.setHeaderText("Successfully Changed Password");
		alert.setContentText("Your password has been updated successfully!");
		alert.showAndWait();
	}

	public void alertUpdatePlanSuccess() {
		alert.setHeaderText("Successfully Upgraded to VIP");
		alert.setContentText("Your plan has been upgraded successfully!");
		alert.showAndWait();
	}

	public void alertUpdateFirstNameSuccess() {
		alert.setHeaderText("Successfully Updated First Name");
		alert.setContentText("Your first name has been updated successfully!");
		alert.showAndWait();
	}

	public void alertUpdateLastNameSuccess() {
		alert.setHeaderText("Successfully Updated Last Name");
		alert.setContentText("Your last name has been updated successfully!");
		alert.showAndWait();
	}
}
