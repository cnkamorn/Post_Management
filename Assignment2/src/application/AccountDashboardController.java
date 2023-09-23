package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AccountDashboardController extends DashBoardController {

	@FXML
	private Button back;

	@FXML
	private TextField currentUsernameField;

	@FXML
	private Pane firstNameView;

	@FXML
	private Button firstnameBtn;

	@FXML
	private Pane lastNameView;

	@FXML
	private Button lastnameBtn;

	@FXML
	private Pane logoView;

	@FXML
	private TextField newUsernameField;

	@FXML
	private Button passwordBtn;

	@FXML
	private Pane passwordView;

	@FXML
	private TextField reTypeUsername;

	@FXML
	private Button usernameBtn;

	@FXML
	private Button usernameSaveBtn;

	@FXML
	private Pane usernameView;

	private ErrorView alert = new ErrorView();

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
		System.out.println(currentUserAccount.getFirstname());
	}

	@FXML
	public void switchPage(ActionEvent event) {
		logoView.setVisible(false);
		if (event.getSource() == usernameBtn) {
			usernameView.setVisible(true);
			passwordView.setVisible(false);
			firstNameView.setVisible(false);
			lastNameView.setVisible(false);
		} else if (event.getSource() == passwordBtn) {
			usernameView.setVisible(false);
			passwordView.setVisible(true);
			firstNameView.setVisible(false);
			lastNameView.setVisible(false);
		} else if (event.getSource() == firstnameBtn) {
			usernameView.setVisible(false);
			passwordView.setVisible(false);
			firstNameView.setVisible(true);
			lastNameView.setVisible(false);
		} else if (event.getSource() == lastnameBtn) {
			usernameView.setVisible(false);
			passwordView.setVisible(false);
			firstNameView.setVisible(false);
			lastNameView.setVisible(true);
		}
	}

	@FXML
	public void changeUserName(ActionEvent event) {
		String currentUsername = currentUsernameField.getText();
		String newUsername = newUsernameField.getText();
		String reTypeUsernameField = reTypeUsername.getText();
		System.out.println(currentUsername);
		System.out.println(currentUsernameField.getText());
		if (!currentUsername.equals(currentUserAccount.getUsername())) {
			alert.alertInvalidUsername();
		} else {
			if (!newUsername.equals(reTypeUsernameField)) {
				System.out.println("not matches");
			} else {
				String query = "SELECT username FROM User WHERE username='" + newUsername + "' AND username != '"
						+ currentUsername + "';";
				try {
					Connection con = UserDatabase.getConnection();
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					con.close();
					stmt.close();
					if (rs.next()) {
						alert.alertUsernameExists(); // found existing username
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}

		}
	}

}
