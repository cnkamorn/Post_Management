package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashBoardView {

	Stage stage = new Stage();
	Scene scene;
	Parent root;

	// show the dashboard after successfully login
	public void showView(Account user) {
		try {
			root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showViewVip() {
		try {
			root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
