package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Album;
import model.Photo;
import model.User;

public class AlbumController {
	@FXML
	private Button add, remove, move, copy, CaptionEdit,
			 buttonType;
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
		
	}
	
	public void addPhoto() {
		
	}
	
	public void removePhoto() {
		
	}
	
	public void copyPhoto() {
		
	}
	
	public void MovePhoto() {
		
	}
	
	public void edit() {
		
	}
}
