package application.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is a login view class. It contains the get scene method
 *
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class LoginView {
	private static LoginView Instance;

	private LoginView() {
	};

	public static LoginView getInstance() {
		if (Instance == null) {
			Instance = new LoginView();
		}
		return Instance;
	}

	public void getScene() {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
			Parent parentNode = loader.load();
			Scene scene = new Scene(parentNode);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Data Anylytics Hub");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
