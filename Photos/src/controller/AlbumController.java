package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import helper.SerializeData;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Album;
import model.Photo;
import model.User;
import helper.PhotoCell;

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

		photos.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo>>() {
			@Override
			public ListCell<Photo> call(ListView<Photo> photoList) {
				return new PhotoCell();
			}
		});

		photos.setItems(FXCollections.observableArrayList(selectedAlbum.getPhotos()));
		photos.getSelectionModel().select(0);
		//disableInput(true);

		ArrayList<String> albumnames = new ArrayList<String>();
		albumnames.add(0, " ");
		ArrayList<Album> allalbums = user.getAlbums();
		for (Album curralbum : allalbums) {
			albumnames.add(curralbum.getName());
		}

		//albumNameField.setItems(FXCollections.observableArrayList(albumnames));
		//albumNameField.setValue(" ");

		//albumNameField.setDisable(true);
		//albumNameLabel.setDisable(true);
		//captionLabelFXML.setDisable(true);
		//captionField.setDisable(true);
	}
	
	public void addPhoto() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select an Image");
		chooser.getExtensionFilters().addAll(
				new ExtensionFilter("Image Files", "*.bmp", "*.BMP", "*.gif", "*.GIF", "*.jpg", "*.JPG", "*.png","*.PNG"),				
				new ExtensionFilter("GIF Files", "*.gif", "*.GIF"),
				new ExtensionFilter("PNG Files", "*.png", "*.PNG"),
				new ExtensionFilter("Bitmap Files", "*.bmp", "*.BMP"),
				new ExtensionFilter("JPEG Files", "*.jpg", "*.JPG"));
		File selectedFile = chooser.showOpenDialog(null);

		if (selectedFile != null) {
			
			String name = selectedFile.getName();
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis(selectedFile.lastModified());
			Image image = new Image(selectedFile.toURI().toString());
			
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
