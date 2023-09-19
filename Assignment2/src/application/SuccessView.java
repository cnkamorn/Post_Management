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

}
