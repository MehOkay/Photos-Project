package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import model.Album;
import model.ImageCell;
import model.Photo;
import model.SerializeData;
import model.User;

public class AlbumController {
	@FXML
	private Button add, remove, move, copy, CaptionEdit, open, buttonType;
	@FXML
	private TextField captionField;
	@FXML
	private Label albumNameLabel, captionLabelFXML, AlbumName;
	@FXML
	private ChoiceBox<String> albumNameField;
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
	
	public void removePhoto() {
		SerializeData.writeData(users);
	}
	
	public void copyPhoto() {
		SerializeData.writeData(users);
	}
	
	public void MovePhoto() {
		SerializeData.writeData(users);
	}
	
	public void edit() {
		SerializeData.writeData(users);
	}
	
	public void openPhoto() {
		
	}
}
