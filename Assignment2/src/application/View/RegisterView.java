package application.View;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterView {

	private static RegisterView Instance;

	private RegisterView() {
	};

	public static RegisterView getInstance() {
		if (Instance == null) {
			Instance = new RegisterView();
		}
		return Instance;
	}

	public void getScene() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
			Parent parentNode = loader.load();
			Scene scene = new Scene(parentNode);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
