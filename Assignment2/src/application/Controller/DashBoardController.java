package application.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Model.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DashBoardController extends LoginController {

	Stage stage = new Stage();
	Scene scene;
	Parent root;
	protected Account currentUserAccount = Account.getInstance();

	@FXML
	private Label welcome;

	@FXML
	private Button editprofilebtn;

	@FXML
	private Button homepostbtn;

	@FXML
	private Button logoutbtn;

	@FXML
	private Label welcometxt;

	public DashBoardController() {
	};

	public void userDashBoardControl(String username, String password) {
		try {
			root = FXMLLoader.load(getClass().getResource("/application/dashboard.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Data Anylytics Hub");
			stage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	public void accountSetting(ActionEvent event) {
		editprofilebtn.getScene().getWindow().hide();
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

	@FXML
	public void postManagement(ActionEvent event) {
		editprofilebtn.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/postDashboard.fxml"));
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

	public void setUserDetails() {
		String query = "SELECT username,password,first_name,last_name,user_plan FROM User WHERE username='"
				+ currentUserName + "'";
		try {
			Connection con = UserDatabase.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				currentUserAccount.setUsername(rs.getString("username"));
				currentUserAccount.setPassword(rs.getString("password"));
				currentUserAccount.setFirstname(rs.getString("first_name"));
				currentUserAccount.setLastname(rs.getString("last_name"));
				currentUserAccount.setUserPlan(rs.getString("user_plan"));
			}
			con.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	public void logout(ActionEvent event) {
		logoutbtn.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/login.fxml"));
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

	public void setWelcomeText() {
		if (this.editprofilebtn != null) {
			welcome.setText(" Hello " + currentUserAccount.getFirstname().toUpperCase() + " "
					+ currentUserAccount.getLastname().toUpperCase());
			welcome.setFont(new Font("Arial", 24));
			welcome.setStyle("-fx-text-fill: CORNFLOWERBLUE;");
		}
	}

	public void initialize() {
		setUserDetails();
		setWelcomeText();
	}

}
