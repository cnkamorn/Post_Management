package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashBoardController extends LoginController {

	Stage stage = new Stage();
	Scene scene;
	Parent root;
	Account currentUserAccount = new Account();

	@FXML
	private Label welcome;

	public DashBoardController() {
	};

	public void userDashBoardControl(String username, String password) {
		try {
			root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// query
		// create account obj?
		// pass it to view
		// view handle account info
		/*
		 * Account user = new Account(); UserDatabase udb = UserDatabase.getInstance();
		 * String query = "SELECT username, password FROM " + udb.TABLE_NAME + " " +
		 * "WHERE username = '" + username + "' AND password ='" + password + "' "; /*
		 * try { Connection c = udb.getConnection(); Statement stmt =
		 * c.createStatement(); ResultSet rs = stmt.executeQuery(query); if (rs.next())
		 * { return true; } } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } return false; DashBoardView show = new
		 * DashBoardView(); show.showView(null, null); welcome.setText("AHOJ");
		 */
	}

	public void setUserDetails() {
		UserDatabase udb = UserDatabase.getInstance();
		String query = "SELECT username,password,first_name,last_name,user_plan FROM User WHERE username='"
				+ currentUserName + "'";
		try {
			Connection c = udb.getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String userPlan = rs.getString("user_plan");
				currentUserAccount.setUsername(username);
				currentUserAccount.setPassword(password);
				currentUserAccount.setFirstname(firstName);
				currentUserAccount.setLastname(lastName);
				currentUserAccount.setUserPlan(userPlan);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void setWelcomeText() {
		welcome.setText("Hello " + currentUserAccount.getFirstname() + " " + currentUserAccount.getLastname());
	}

	public void initialize() {
		setUserDetails();
		setWelcomeText();
	}
}
