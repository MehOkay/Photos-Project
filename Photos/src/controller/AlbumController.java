package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import model.Album;
import model.ImageCell;
import model.Photo;
import model.SerializeData;
import model.User;

public class AlbumController {
	@FXML
	private Button add, remove, move, copy, CaptionEdit, openPhoto;
	@FXML
	private TextField editField;
	@FXML
	private Label albumNameLabel, captionLabelFXML, AlbumName;
	@FXML
	private ListView<Photo> photos;
	private ArrayList<User> users;
	private User user;
	private Album selectedAlbum;
	
	public void start(ArrayList<User> users, User user, Album selectedAlbum) {
		
		this.users = users;
		
		this.user = user;
		
		this.selectedAlbum = selectedAlbum;
		
		
		AlbumName.setText(selectedAlbum.getName());

		photos.setCellFactory(
			new Callback<ListView<Photo>, ListCell<Photo>>() {
				@Override
				public ListCell<Photo> call(ListView<Photo> photoList) {
					return new ImageCell();
				}
		});

		photos.setItems(FXCollections.observableArrayList(selectedAlbum.getPhotos()));
		photos.getSelectionModel().select(0);

		ArrayList<String> albumnames = new ArrayList<String>();
		albumnames.add(0, " ");
		ArrayList<Album> allalbums = user.getAlbums();
		
		for (Album curralbum : allalbums) {
			albumnames.add(curralbum.getName());
		}

	}
	
	public void addPhoto() {
		FileChooser c = new FileChooser();
		
		c.setTitle("Select an Image");
		c.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg", "*.JPG", "*.png","*.PNG", "*.bmp", "*.BMP", "*.gif", "*.GIF" ));				
		
		File selectedImage = c.showOpenDialog(null);

		if(selectedImage == null) {
			
		}
		else if (selectedImage != null) {
			
			String name = selectedImage.getName();
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis(selectedImage.lastModified());
			
			Image image = new Image(selectedImage.toURI().toString());
			
			Photo newPhoto = new Photo(name, image, date);

			for (Photo p : selectedAlbum.getPhotos()) {
				if (p.equals(newPhoto)) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Alert");
					
					alert.setHeaderText("Photo Add Error.");
					
					alert.setContentText("A photo with this name already exists.");

					alert.showAndWait();
					return;
				}
			}

			photos.getItems().add(newPhoto);
			
			selectedAlbum.getPhotos().add(newPhoto);
			SerializeData.writeData(users);
		}
	}
	/**
	 * Delete selected photo from the user's album
	 */
	public void removePhoto() {
		selectedAlbum.getPhotos().remove(photos.getSelectionModel().getSelectedItem());
		photos.getItems().remove(photos.getSelectionModel().getSelectedItem());
		photos.refresh();
		SerializeData.writeData(users);
	}
	
	public void copyPhoto(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Copy.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			CopyPhotoController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
	
			//controller.start(users, photos, user, selectedAlbum);
			stage.showAndWait();

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		//SerializeData.writeData(users);
	}
	
	public void MovePhoto(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Copy.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			MovePhotoController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
	
			//controller.start(users, photos, user, selectedAlbum);
			stage.showAndWait();

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		//SerializeData.writeData(users);
	}
	
	public void edit() {
		photos.getSelectionModel().getSelectedItem().setCaption(editField.getText());
		photos.refresh();
		editField.clear();
		SerializeData.writeData(users);
	}
	
	public void openSlideShow(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Photo.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			PhotoContentController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
	
			controller.start(users, photos, user, selectedAlbum);
			stage.showAndWait();

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
