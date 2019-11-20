package controller;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Album;
import model.SerializeData;
import model.User;
//import util.CommonFunctions;

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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
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
		ObservableList<Album> albumList = albums.getItems();

		for (Album currentAlbum : albumList) {
			if (currentAlbum.getName().equals(albumField.getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Alert");
				alert.setHeaderText("Error adding album.");
				alert.setContentText("An album with this name already exists.");

				alert.showAndWait();
				return;
			}
		}
		
		if(!albumField.getText().equals("")) {
			
		Album newAlbum = new Album(albumField.getText());
		
		user.getAlbums().add(newAlbum);
		albums.getItems().add(newAlbum);
		albums.getSelectionModel().select(newAlbum);
		albums.refresh();
		SerializeData.writeData(users);
		albumField.clear();
		}
	}
	
	/**
	 * Opens Album stage that displays content for selected album
	 * @param event
	 */
	public void handleOpenAlbumButton(ActionEvent event) {
		
		Album selectedAlbum = albums.getSelectionModel().getSelectedItem();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Album.fxml"));
			//Parent parent = (Parent) loader.load();
			AnchorPane root = (AnchorPane) loader.load();
			AlbumController controller = loader.getController();
			Scene scene = new Scene(root);
			//Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			
			
			controller.start(users, user, selectedAlbum);
			
			stage.showAndWait();
			//stage.show();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Deletes selected album and serializes the data
	 */
	public void handleDeleteAlbumButton() {
		Album album = albums.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Action Alert");
		alert.setHeaderText("Confirmation Required");
		alert.setContentText("Are you sure you want to delete \"" + album.getName() + "\"?");
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get().equals(ButtonType.YES)) {
			user.getAlbums().remove(album);
			albums.getItems().remove(album);
			albums.refresh();
			SerializeData.writeData(users);
		}
	}
	
	/**
	 * Renames album to user inputed text
	 */
	public void handleRenameButton() {
		
		ObservableList<Album> albumList = albums.getItems();

		for (Album a : albumList) {
			if (a.getName().equals(albumField.getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Alert");
				alert.setHeaderText("Error renaming album.");
				alert.setContentText("An album with this name already exists.");

				alert.showAndWait();
				return;
			}
		}
		
		if(!albumField.getText().equals("")) {
			albums.getSelectionModel().getSelectedItem().setName(albumField.getText());
			albums.refresh();
			SerializeData.writeData(users);
			albumField.clear();
		}
	}
	
	/**
	 * Opens Search Photo stage for user to search their photos
	 * 
	 * @param event
	 */
	public void handlesearchPhotosButton(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Search Photo.fxml"));
			//Parent parent = (Parent) loader.load();
			AnchorPane root = (AnchorPane) loader.load();
			PhotoSearchController controller = loader.getController();
			Scene scene = new Scene(root);
			//Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			
			
			controller.start(user, users);
			
			stage.showAndWait();
			//stage.show();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
