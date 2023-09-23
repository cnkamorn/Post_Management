package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpView {

	private static SignUpView Instance;

	private SignUpView() {
	};

	public static SignUpView getInstance() {
		if (Instance == null) {
			Instance = new SignUpView();
		}
		return Instance;
	}

	public void showView() {
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
