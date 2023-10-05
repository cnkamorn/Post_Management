package application.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Model.Account;
import application.Model.UserDatabase;
import application.View.AccountDashBoardView;
import application.View.DashBoardView;
import application.View.LoginView;
import application.View.PostDashBoardView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * This class is a dash board controller class It's an extended class from the
 * login controller class
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class DashBoardController extends LoginController {

	protected Account currentLoginUser = Account.getInstance();

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

		DashBoardView dashboard = DashBoardView.getInstance();
		dashboard.getScene();
	}

	@FXML
	public void accountSetting(ActionEvent event) {
		editprofilebtn.getScene().getWindow().hide();
		AccountDashBoardView accDashboard = AccountDashBoardView.getInstance();
		accDashboard.getScene();
	}

	/**
	 * @param event
	 */
	@FXML
	public void postManagement(ActionEvent event) {
		editprofilebtn.getScene().getWindow().hide();
		PostDashBoardView postDashboard = PostDashBoardView.getInstance();
		postDashboard.getScene();
	}

	public void setUserDetails() {
		String query = "SELECT username,password,first_name,last_name,user_plan FROM User WHERE username='"
				+ currentUserName + "'";
		try {
			Connection con = UserDatabase.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				currentLoginUser.setUsername(rs.getString("username"));
				currentLoginUser.setPassword(rs.getString("password"));
				currentLoginUser.setFirstname(rs.getString("first_name"));
				currentLoginUser.setLastname(rs.getString("last_name"));
				currentLoginUser.setUserPlan(rs.getString("user_plan"));
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
		LoginView loginScene = LoginView.getInstance();
		loginScene.getScene();
	}

	public void setWelcomeText() {
		if (this.editprofilebtn != null) {
			welcome.setText(" Hello " + currentLoginUser.getFirstname().toUpperCase() + " "
					+ currentLoginUser.getLastname().toUpperCase());
			welcome.setFont(new Font("Arial", 24));
			welcome.setStyle("-fx-text-fill: CORNFLOWERBLUE;");
		}
	}

	public void initialize() {
		setUserDetails();
		setWelcomeText();
	}

}
