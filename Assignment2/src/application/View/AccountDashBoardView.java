package application.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is an account dash board view class. It contains the get scene
 * method
 *
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class AccountDashBoardView {

	private static AccountDashBoardView Instance;

	private AccountDashBoardView() {
	};

	public static AccountDashBoardView getInstance() {
		if (Instance == null) {
			Instance = new AccountDashBoardView();
		}
		return Instance;
	}

	public void getScene() {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("accountDashboard.fxml"));
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
