package application;

import application.Exception.BlankInputException;
import application.Exception.UsernameMismatchException;
import application.Exception.UsernameRetypeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AccountDashboardController extends DashBoardController {

	@FXML
	private MenuItem lastNameMenu;

	@FXML
	private Button back;

	@FXML
	private TextField currentUsernameField;

	@FXML
	private MenuItem firstNameMenu;

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
	private MenuItem passwordMenu;

	@FXML
	private Pane passwordView;

	@FXML
	private TextField reTypeUsername;

	@FXML
	private Button usernameBtn;

	@FXML
	private MenuItem usernameMenu;

	@FXML
	private Button usernameSaveBtn;

	@FXML
	private Pane usernameView;

	protected ErrorView alert = new ErrorView();

	protected SuccessView alertSuccess = new SuccessView();

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
	public void switchPage(ActionEvent event) {
		logoView.setVisible(false);
		if ((event.getSource() == usernameBtn) || (event.getSource() == usernameMenu)) {
			usernameView.setVisible(true);
			passwordView.setVisible(false);
			firstNameView.setVisible(false);
			lastNameView.setVisible(false);
		} else if ((event.getSource() == passwordBtn) || (event.getSource() == passwordMenu)) {
			usernameView.setVisible(false);
			passwordView.setVisible(true);
			firstNameView.setVisible(false);
			lastNameView.setVisible(false);
		} else if ((event.getSource() == firstnameBtn) || (event.getSource() == firstNameMenu)) {
			usernameView.setVisible(false);
			passwordView.setVisible(false);
			firstNameView.setVisible(true);
			lastNameView.setVisible(false);
		} else if ((event.getSource() == lastnameBtn) || (event.getSource() == lastNameMenu)) {
			usernameView.setVisible(false);
			passwordView.setVisible(false);
			firstNameView.setVisible(false);
			lastNameView.setVisible(true);
		}
	}

	@FXML
	public void changeUserName(ActionEvent event) {
		ChangeUsername changeUnController = ChangeUsername.getInstance();
		String currentUn = currentUsernameField.getText();
		String newUn = newUsernameField.getText();
		String reTypeUn = reTypeUsername.getText();
		try {
			changeUnController.checkBlankField(currentUn, newUn, reTypeUn);
			changeUnController.checkMatchingCurrentUsername(currentUn, currentUserAccount.getUsername());
			changeUnController.checkMatchingRetypeUsername(newUn, reTypeUn);
			changeUnController.checkUsernameExist(currentUserAccount.getUsername(), newUn);
			// pass all exception fields, change the username
			currentUserAccount.setUsername(newUn);
			// update database
			UserDatabase udb = UserDatabase.getInstance();
			udb.updateUsername(currentUn, newUn);
			alertSuccess.alertUpdateUsernameSuccess();
		} catch (UsernameMismatchException e) {
			alert.alertInvalidUsername();
		} catch (UsernameRetypeException e) {
			alert.alertRetypeUsername();
		} catch (BlankInputException e) {
			alert.alertBlankInput();
		}
	}

	@FXML
	public void changePassword(ActionEvent event) {

	}
}
