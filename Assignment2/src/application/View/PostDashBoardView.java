package application.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is a post dash board view class. It contains the get scene method
 *
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class PostDashBoardView {

	private static PostDashBoardView Instance;

	private PostDashBoardView() {
	};

	public static PostDashBoardView getInstance() {
		if (Instance == null) {
			Instance = new PostDashBoardView();
		}
		return Instance;
	}

	public void getScene() {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("postDashboard.fxml"));
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
