package application.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Exception.BlankInputException;
import application.Exception.InvalidDateTimeFormatException;
import application.Exception.NegativeNumberException;
import application.Exception.PostIdExistsException;
import application.Exception.PostNotFoundException;
import application.Model.ErrorAlert;
import application.Model.Input;
import application.Model.Post;
import application.Model.SuccessAlert;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PostDashboardController extends DashBoardController implements Initializable {

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
	private MenuItem exportPostMenu;

	@FXML
	private TableColumn<Post, String> postAuthorIDcolumn;

	@FXML
	private TableColumn<Post, String> postContentColumn;

	@FXML
	private TextField postContentField;

	@FXML
	private TableColumn<Post, String> postDateColumn;

	@FXML
	private TextField postDateTimeField;

	@FXML
	private TableColumn<Post, Integer> postIDColumn;

	@FXML
	private TextField postIdField;

	@FXML
	private TableColumn<Post, Integer> postLikeColumn;

	@FXML
	private TextField postLikesField;

	@FXML
	private ImageView postLogoView;

	@FXML
	private TableColumn<Post, Integer> postShareColumn;

	@FXML
	private TextField postSharesField;

	@FXML
	private TableView<Post> postTable;

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
	private TextField postSearchField;

	@FXML
	private Label subscriptionLabel;

	private UserDatabase userDb = UserDatabase.getInstance();
	private Post post = Post.getInstance();
	private Input inputValidate = Input.getInstance();
	private PostDatabase postDb = PostDatabase.getInstance();
	private SuccessAlert alertSuccess = SuccessAlert.getInstance();
	private ErrorAlert alertError = ErrorAlert.getInstance();

	@FXML
	public void backToHomePage(ActionEvent event) {
		back.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/dashboard.fxml"));
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

	@FXML
	public void addPost(ActionEvent event) {
		// get texts and store in the variables
		String postId = postIdField.getText();
		String postContent = postContentField.getText();
		String postLikes = postLikesField.getText();
		String postShares = postSharesField.getText();
		String postDateTime = postDateTimeField.getText();
		AddPostValidation addPostCheck = AddPostValidation.getInstance();
		try {
			// check the blank input
			addPostCheck.checkBlankField(postId, postContent, postLikes, postShares, postDateTime);
			// check if the postID is exist
			addPostCheck.checkPostIdExist(postId);
			// check if the input fields are in the correct formats
			inputValidate.acceptIntegerInput(postId);
			inputValidate.acceptIntegerInput(postLikes);
			inputValidate.acceptIntegerInput(postShares);
			inputValidate.acceptDateTime(postDateTime);
			if (!addPostCheck.checkInputWhiteSpace(postId, postLikes, postShares)) {
				// create a post object then add it to the database
				post.setPostID(Integer.parseInt(postId));
				post.setLikes(Integer.parseInt(postLikes));
				post.setShares(Integer.parseInt(postShares));
				post.setPostAuthorID(userDb.queryUserId(currentUserAccount.getUsername()));
				post.setPostContent(postContent);
				post.setPostDateTime(postDateTime);
				postDb.insertPost(post);
				alertSuccess.alertAddedPostSuccess();
			}
		} catch (BlankInputException e) {
			alert.alertBlankInput();
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			alert.alertNumberFormat();
		} catch (NegativeNumberException e) {
			alert.alertNegativeNumber();
		} catch (InvalidDateTimeFormatException e) {
			alert.alertInvalidDateTimeFormat();
		} catch (PostIdExistsException e) {
			alert.alertPostIdExist();
		}
	}

	// ref https://www.youtube.com/watch?v=qQcr_JMxWRw&list=LL&index=1&t=110s
	@FXML
	public void searchPost(ActionEvent event) {
		SearchPost sp = SearchPost.getInstance();
		String userInputSearchID = postSearchField.getText();
		try {
			sp.checkBlankField(userInputSearchID);
			Post post = sp.retrievePost(userInputSearchID);
		} catch (BlankInputException e) {
			alertError.alertBlankInput();
		} catch (PostNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Post> posts = postTable.getItems();
		posts.add(post);
		postTable.setItems(posts);
	}

	// ref https://www.youtube.com/watch?v=qQcr_JMxWRw&list=LL&index=1&t=110s
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		postIDColumn.setCellValueFactory(new PropertyValueFactory<Post, Integer>("postID"));
		// postContentColumn.setCellValueFactory(new PropertyValueFactory<Post,
		// String>("postContent"));
		// postLikeColumn.setCellValueFactory(new PropertyValueFactory<Post,
		// Integer>("likes"));
		// postShareColumn.setCellValueFactory(new PropertyValueFactory<Post,
		// Integer>("shares"));
		// postDateColumn.setCellValueFactory(new PropertyValueFactory<Post,
		// String>("postDateTime"));
		// postAuthorIDcolumn.setCellValueFactory(new PropertyValueFactory<Post,
		// String>("postAuthorID"));

	}

}
