package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Album;
import model.User;

/**
 * The non-admin controller allows regular users to manipulate their albums
 * and view them as a list. Users can also search their photos
 * 
 * @author Wesley Cheung
 * @author Dennis Yu
 *
 */
public class NonAdminController {
	
	@FXML
	private Button openAlbumButton, addAlbumButton, deleteAlbumButton, renameAlbumButton, searchPhotosButton,
			logOutButton;
	@FXML
	private TextField albumField;
	@FXML
	private ListView<Album> albums;
	private ArrayList<User> users;
	private User user;

	@FXML
	private Label Username;

	public boolean rename = false;
	public String albumname;
	
	/**
	 * Starts the new scene for non-admin users. Takes in 2 parameters to load a user's data. The scene displays 
	 * the list of user albums.
	 * 
	 * @param user is the user that logged in
	 * @param users is the list of users in the system. Used for serializing data.
	 */
	public void start(User user, ArrayList<User> users) {
		this.user = user;
		this.users = users;
		albums.setItems(FXCollections.observableArrayList(user.getAlbums()));
		albums.getSelectionModel().select(0);
		Username.setText("Hello, " + user.getUsername().toString());
	}
	
	/**
	 * returns user back to log in screen
	 * 
	 * @param event
	 */
	public void handleLogOutButton(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginScreen.fxml"));
			Parent parent = (Parent) loader.load();
			@SuppressWarnings("unused")
			LoginController controller = loader.getController();
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			stage.setScene(scene);
			stage.show();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
	
	/**
	 * clicking the add button takes the user inputed text and creates a new album
	 */
	public void handleAddAlbumButton() {
		
	}
	
	/**
	 * Opens Album stage that displays content for selected album
	 * @param event
	 */
	public void handleOpenAlbumButton(ActionEvent event) {
		
	}
	
	/**
	 * Deletes selected album and serializes the data
	 */
	public void handleDeleteAlbumButton() {
		
	}
	
	/**
	 * Renames album to user inputed text
	 */
	public void handleRenameButton() {
		
	}
	
	/**
	 * Opens Search Photo stage for user to search their photos
	 * 
	 * @param event
	 */
	public void handlesearchPhotosButton(ActionEvent event) {
		
	}
}
