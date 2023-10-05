package application.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Exception.BlankInputException;
import application.Exception.InputHeaderException;
import application.Exception.InvalidDateTimeFormatException;
import application.Exception.InvalidFileTypeException;
import application.Exception.NegativeNumberException;
import application.Exception.PostIdExistsException;
import application.Exception.PostNotFoundException;
import application.Model.AddPostValidation;
import application.Model.AnalyticsChart;
import application.Model.ErrorAlert;
import application.Model.ExportPost;
import application.Model.ImportFile;
import application.Model.Input;
import application.Model.Post;
import application.Model.PostDatabase;
import application.Model.RemovePost;
import application.Model.RetrieveMultiPosts;
import application.Model.SearchPost;
import application.Model.SuccessAlert;
import application.Model.UserDatabase;
import application.View.DashBoardView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class is a main post dash board class It contains related methods about
 * managing all actions related to the post
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class PostDashboardController extends DashBoardController implements Initializable {

	@FXML
	private Button addPostBtn;

	@FXML
	private MenuItem DataVisulizationMenu;

	@FXML
	private MenuItem addPostMenu;

	@FXML
	private Button addPostSaveBtn;

	@FXML
	private Pane addPostView;

	@FXML
	private Button back;

	@FXML
	private Button bulkImportBtn;

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
	private Button genPieBtn;

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
	private PieChart pieChart;

	@FXML
	private Button dataVisualizationBtn;

	@FXML
	private Pane dataVisualizationView;

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
		DashBoardView dashBoardScene = DashBoardView.getInstance();
		dashBoardScene.getScene();
	}

	public void addPostViewVisible() {
		addPostView.setVisible(true);
		removePostView.setVisible(false);
		retrieveMultiPostView.setVisible(false);
		retrievePostView.setVisible(false);
		exportPostView.setVisible(false);
		dataVisualizationView.setVisible(false);
	}

	public void retrievePostViewVisible() {
		addPostView.setVisible(false);
		removePostView.setVisible(false);
		retrieveMultiPostView.setVisible(false);
		retrievePostView.setVisible(true);
		exportPostView.setVisible(false);
		dataVisualizationView.setVisible(false);
	}

	public void removePostViewVisible() {
		addPostView.setVisible(false);
		removePostView.setVisible(true);
		retrieveMultiPostView.setVisible(false);
		retrievePostView.setVisible(false);
		exportPostView.setVisible(false);
		dataVisualizationView.setVisible(false);
	}

	public void exportPostViewVisible() {
		addPostView.setVisible(false);
		removePostView.setVisible(false);
		retrieveMultiPostView.setVisible(false);
		retrievePostView.setVisible(false);
		exportPostView.setVisible(true);
		dataVisualizationView.setVisible(false);
	}

	public void retrieveMultiPostViewVisible() {
		addPostView.setVisible(false);
		removePostView.setVisible(false);
		retrieveMultiPostView.setVisible(true);
		retrievePostView.setVisible(false);
		exportPostView.setVisible(false);
		dataVisualizationView.setVisible(false);
	}

	public void dataVisualizationViewVisible() {
		addPostView.setVisible(false);
		removePostView.setVisible(false);
		retrieveMultiPostView.setVisible(false);
		retrievePostView.setVisible(false);
		exportPostView.setVisible(false);
		if (!currentLoginUser.getUserPlan().equals("VIP")) {
			alert.alertUserPlan();
			postLogoView.setVisible(true);
		} else {
			dataVisualizationView.setVisible(true);
		}
	}

	@FXML
	public void changePage(ActionEvent event) {
		postLogoView.setVisible(false);
		if ((event.getSource() == addPostBtn) || (event.getSource() == addPostMenu)) {
			addPostViewVisible();
		} else if ((event.getSource() == retrievePostBtn) || (event.getSource() == retrievePostMenu)) {
			retrievePostViewVisible();
		} else if ((event.getSource() == removePostBtn) || (event.getSource() == removePostMenu)) {
			removePostViewVisible();
		} else if ((event.getSource() == exportPostbutton) || (event.getSource() == exportPostMenu)) {
			exportPostViewVisible();
		} else if ((event.getSource() == retrieveMultiPostsBtn) || (event.getSource() == retrieveMultiPostMenu)) {
			retrieveMultiPostViewVisible();
		} else if ((event.getSource() == dataVisualizationBtn) || (event.getSource() == DataVisulizationMenu)) {
			dataVisualizationViewVisible();
		}
	}

	@FXML
	public void removePost(ActionEvent event) {
		String postId = removePostIDfield.getText();
		RemovePost remove = RemovePost.getInstance();
		try {
			remove.checkBlankField(postId);
			remove.removePost(currentLoginUser.getUsername(), postId);
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
				post.setPostAuthorID(userDb.queryUserId(currentLoginUser.getUsername()));
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
		SearchPost searchPostFromDB = SearchPost.getInstance();
		try {
			searchPostFromDB.checkBlankField(userInputSearchID);
			Post post = searchPostFromDB.retrievePost(currentLoginUser.getUsername(), userInputSearchID);
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
		SearchPost searchPostFromDB = SearchPost.getInstance();
		try {
			exportPostController.checkBlankField(inputPost);
			Post post = searchPostFromDB.retrievePost(currentLoginUser.getUsername(), inputPost); // get a post
																									// from postID
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
			ArrayList<Post> posts = retrievePosts.retrievePostsCollection(currentLoginUser.getUsername());
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

	@FXML
	public void generateChart(ActionEvent event) {
		// pie chart
		// ref https://docs.oracle.com/javafx/2/charts/pie-chart.htm
		AnalyticsChart calculate = AnalyticsChart.getInstance();
		pieChart.setVisible(true);
		genPieBtn.setVisible(false);
		try {
			PieChart.Data zeroToNinetyNine = new PieChart.Data("0 - 99 shares",
					calculate.calculateShares(currentLoginUser.getUsername()).get("0-99"));
			PieChart.Data hundredToThousand = new PieChart.Data("100 - 999 shares",
					calculate.calculateShares(currentLoginUser.getUsername()).get("100-999"));
			PieChart.Data thousandPlus = new PieChart.Data("More than 999 shares",
					calculate.calculateShares(currentLoginUser.getUsername()).get("1000+"));
			pieChart.getData().addAll(zeroToNinetyNine, hundredToThousand, thousandPlus);
		} catch (PostNotFoundException e) {
			alertError.alertZeroPost();
		}
	}

	@FXML
	public void bulkImport(ActionEvent event) {
		// ref https://jenkov.com/tutorials/javafx/filechooser.html
		if (!currentLoginUser.getUserPlan().equals("VIP")) {
			alert.alertUserPlan();
		} else {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Select file");
			File file = chooser.showOpenDialog(new Stage());
			ImportFile manageFile = ImportFile.getInstance();
			AddPostValidation addPostCheck = AddPostValidation.getInstance();
			if (file != null) {
				try {
					ArrayList<Post> postList = manageFile.bulkImport(file);
					for (Post post : postList) {
						addPostCheck.checkPostIdExist(Integer.toString(post.getPostID()));
						inputValidate.acceptIntegerInput(Integer.toString(post.getPostID()));
						inputValidate.acceptIntegerInput(Integer.toString(post.getLikes()));
						inputValidate.acceptIntegerInput(Integer.toString(post.getShares()));
						inputValidate.acceptDateTime(post.getPostDateTime());
						postDb.insertPost(post);
						alertSuccess.alertAddedPostSuccess();
					}
				} catch (InvalidFileTypeException e) {
					alert.alertImportError("Error: The file type is not csv", "Invalid file format");
				} catch (FileNotFoundException e) {
					alert.alertImportError("Error: The file is not found", "File not found");
				} catch (InputHeaderException e) {
					alert.alertImportError("Error: The file doesn't contain header", "Invalid file format");
				} catch (NumberFormatException e) {
					alert.alertImportError(
							"Error: Invalid data found, please ensure the data is ordered as ID,content,author,likes,shares,date-time",
							"Invalid data format");
				} catch (PostIdExistsException e) {
					alert.alertPostIdExist();
				} catch (NegativeNumberException e) {
					// TODO Auto-generated catch block
					alert.alertImportError("Error: The file contains negative numbers", "Negative number found");
				} catch (InvalidDateTimeFormatException e) {
					alert.alertImportError("Error: Invalid date-time format found", "Invalid date-time format");
				}
			}
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
