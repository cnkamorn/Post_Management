package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PostDashboardController extends DashBoardController {

	@FXML
	private Button addPostBtn;

	@FXML
	private MenuItem addPostMenu;

	@FXML
	private Button addPostSaveBtn;

	@FXML
	private Pane addPostView;

	@FXML
	private Button back;

	@FXML
	private MenuItem backMenu;

	@FXML
	private Button exportPostBtn;

	@FXML
	private ImageView postLogoView;

	@FXML
	private MenuItem exportPostMenu;

	@FXML
	private Button removePostBtn;

	@FXML
	private MenuItem removePostMenu;

	@FXML
	private Button removePostSaveBtn;

	@FXML
	private Pane removePostView;

	@FXML
	private MenuItem retrieveMultiPostMenu;

	@FXML
	private Button retrieveMultiPostSaveBtn;

	@FXML
	private Pane retrieveMultiPostView;

	@FXML
	private Button retrieveMultiPostsBtn;

	@FXML
	private Button retrievePostBtn;

	@FXML
	private MenuItem retrievePostMenu;

	@FXML
	private Button retrievePostSaveBtn;

	@FXML
	private Pane retrievePostView;

	@FXML
	public void backToHomePage(ActionEvent event) {
		back.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
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

	@FXML
	public void changePage(ActionEvent event) {
		postLogoView.setVisible(false);
		if ((event.getSource() == addPostBtn) || (event.getSource() == addPostMenu)) {
			addPostView.setVisible(true);
			removePostView.setVisible(false);
			retrieveMultiPostView.setVisible(false);
			retrievePostView.setVisible(false);
		}
	}
}
