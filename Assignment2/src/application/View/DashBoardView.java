package application.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is a dash board view class. It contains the get scene method
 *
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class DashBoardView {

	private static DashBoardView Instance;

	private DashBoardView() {
	};

	public static DashBoardView getInstance() {
		if (Instance == null) {
			Instance = new DashBoardView();
		}
		return Instance;
	}

	public void getScene() {
		try {
			Stage stage = new Stage();
			Scene scene;
			Parent root;
			root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Data Anylytics Hub");
			stage.setResizable(true);
			stage.setMinWidth(570);
			stage.setMinHeight(440);
			stage.setMaxHeight(440);
			stage.setMaxWidth(600);
			stage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
