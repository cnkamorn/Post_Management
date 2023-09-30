package application.Controller;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
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
	private Pane exportPostView;

	@FXML
	private Button exportPostbutton;

	@FXML
	private TableColumn<Post, String> postAuthorIDColumn;

	@FXML
	private TableColumn<Post, String> postAuthorIDColumnByN;

	@FXML
	private TextField postByNField;

	@FXML
	private TableColumn<Post, String> postContentColumn;

	@FXML
	private TableColumn<Post, String> postContentColumnByN;

	@FXML
	private TextField postContentField;

	@FXML
	private TableColumn<Post, String> postDateColumn;

	@FXML
	private TableColumn<Post, String> postDateColumnByN;

	@FXML
	private TextField postDateTimeField;

	@FXML
	private TableColumn<Post, Integer> postIDColumn;

	@FXML
	private TableColumn<Post, Integer> postIDColumnByN;

	@FXML
	private TextField postIDExportField;

	@FXML
	private TextField postIdField;

	@FXML
	private TableColumn<Post, Integer> postLikeColumn;

	@FXML
	private TableColumn<Post, Integer> postLikeColumnByN;

	@FXML
	private TextField postLikesField;

	@FXML
	private ImageView postLogoView;

	@FXML
	private TextField postSearchField;

	@FXML
	private TableColumn<Post, Integer> postShareColumn;

	@FXML
	private TableColumn<Post, Integer> postShareColumnByN;

	@FXML
	private TextField postSharesField;

	@FXML
	private TableView<Post> postTable;

	@FXML
	private TableView<Post> postTableByN;

	@FXML
	private Button removePostBtn;

	@FXML
	private TextField removePostIDfield;

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
	private Label subscriptionLabel;

	private UserDatabase userDb = UserDatabase.getInstance();
	private Post post = Post.getInstance();
	private Input inputValidate = Input.getInstance();
	private PostDatabase postDb = PostDatabase.getInstance();
	private SuccessAlert alertSuccess = SuccessAlert.getInstance();
	private ErrorAlert alertError = ErrorAlert.getInstance();
	private ArrayList<String> currentSearchPost = new ArrayList();
	private ExportPost exportPostController = ExportPost.getInstance();

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
			exportPostView.setVisible(false);
		} else if ((event.getSource() == retrievePostBtn) || (event.getSource() == retrievePostMenu)) {
			addPostView.setVisible(false);
			removePostView.setVisible(false);
			retrieveMultiPostView.setVisible(false);
			retrievePostView.setVisible(true);
			exportPostView.setVisible(false);
		} else if ((event.getSource() == removePostBtn) || (event.getSource() == removePostMenu)) {
			addPostView.setVisible(false);
			removePostView.setVisible(true);
			retrieveMultiPostView.setVisible(false);
			retrievePostView.setVisible(false);
			exportPostView.setVisible(false);
		} else if ((event.getSource() == exportPostbutton) || (event.getSource() == exportPostMenu)) {
			addPostView.setVisible(false);
			removePostView.setVisible(false);
			retrieveMultiPostView.setVisible(false);
			retrievePostView.setVisible(false);
			exportPostView.setVisible(true);
		} else if ((event.getSource() == retrieveMultiPostsBtn) || (event.getSource() == retrieveMultiPostMenu)) {
			addPostView.setVisible(false);
			removePostView.setVisible(false);
			retrieveMultiPostView.setVisible(true);
			retrievePostView.setVisible(false);
			exportPostView.setVisible(false);
		}
	}

	@FXML
	public void removePost(ActionEvent event) {
		String postId = removePostIDfield.getText();
		RemovePost remove = RemovePost.getInstance();
		try {
			remove.checkBlankField(postId);
			remove.removePost(postId);
			alertSuccess.alertRemovePostSuccess();
		} catch (BlankInputException e) {
			alert.alertBlankInput();
		} catch (PostNotFoundException e) {
			alertError.alertPostNotFound();
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
		String userInputSearchID = postSearchField.getText();
		SearchPost searchPostController = SearchPost.getInstance();
		try {
			searchPostController.checkBlankField(userInputSearchID);
			Post post = searchPostController.retrievePost(userInputSearchID);
			ObservableList<Post> posts = postTable.getItems();
			// prevent duplicate posts
			if (!currentSearchPost.contains(userInputSearchID)) {
				currentSearchPost.add(userInputSearchID);
				posts.add(post);
				postTable.setItems(posts);
			}
		} catch (BlankInputException e) {
			alertError.alertBlankInput();
		} catch (PostNotFoundException e) {
			alertError.alertPostNotFound();
		}
	}

	// ref https://www.youtube.com/watch?v=ZGFjaZLwqns
	@FXML
	public void exportPost(ActionEvent event) {
		String inputPost = postIDExportField.getText();
		SearchPost searchPostController = SearchPost.getInstance();
		try {
			exportPostController.checkBlankField(inputPost);
			Post post = searchPostController.retrievePost(inputPost); // get a post from postID
			ExportPost exportPost = ExportPost.getInstance();
			exportPost.exportFile(post);
			alertSuccess.alertExportPostSuccess();
		} catch (BlankInputException e) {
			alertError.alertBlankInput();
		} catch (PostNotFoundException e) {
			alertError.alertPostNotFound();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void retrievePostsByN(ActionEvent event) {
		postTableByN.getItems().clear();
		String inputNumber = postByNField.getText();
		RetrieveMultiPosts retrievePosts = RetrieveMultiPosts.getInstance();
		try {
			inputValidate.acceptIntegerInput(inputNumber);
			retrievePosts.checkBlankField(inputNumber);
			ArrayList<Post> posts = retrievePosts.retrievePostsCollection(); // get the post collects from this user
			ObservableList<Post> postCollection = postTableByN.getItems();
			if (Integer.parseInt(inputNumber) >= posts.size()) {
				alertError.alertNumberOfPost(posts.size());
				// input > posts show all
				postCollection.addAll(posts);
				postTableByN.setItems(postCollection);
			} else {
				for (int i = 0; i < Integer.parseInt(inputNumber); i++) {
					postCollection.add(posts.get(i));
					postTableByN.setItems(postCollection);
				}
			}
		} catch (NumberFormatException e) {
			alertError.alertNumberFormat();
		} catch (NegativeNumberException e) {
			alertError.alertNegativeNumber();
		} catch (BlankInputException e) {
			alertError.alertBlankInput();
		} catch (PostNotFoundException e) {
			alertError.alertZeroPost();
		}
	}

	// ref https://www.youtube.com/watch?v=qQcr_JMxWRw&list=LL&index=1&t=110s
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// add cell table value in the retrieve Post tab
		postIDColumn.setCellValueFactory(new PropertyValueFactory<Post, Integer>("postID"));
		postContentColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("postContent"));
		postLikeColumn.setCellValueFactory(new PropertyValueFactory<Post, Integer>("likes"));
		postShareColumn.setCellValueFactory(new PropertyValueFactory<Post, Integer>("shares"));
		postDateColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("postDateTime"));
		postAuthorIDColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("postAuthorID"));
		// add cell table value in the retrieve Post by N tab
		postIDColumnByN.setCellValueFactory(new PropertyValueFactory<Post, Integer>("postID"));
		postContentColumnByN.setCellValueFactory(new PropertyValueFactory<Post, String>("postContent"));
		postLikeColumnByN.setCellValueFactory(new PropertyValueFactory<Post, Integer>("likes"));
		postShareColumnByN.setCellValueFactory(new PropertyValueFactory<Post, Integer>("shares"));
		postDateColumnByN.setCellValueFactory(new PropertyValueFactory<Post, String>("postDateTime"));
		postAuthorIDColumnByN.setCellValueFactory(new PropertyValueFactory<Post, String>("postAuthorID"));
	}
}
