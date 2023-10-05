package application.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccountDashBoardView {

	public AccountDashBoardView() {
	}

	public void getScene() {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/accountDashboard.fxml"));
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
