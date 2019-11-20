package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.*;
import model.Photo;
import model.Tag;
import model.User;
import model.Album;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.text.SimpleDateFormat;


public class PhotoContentController {

	@FXML Button X;
	@FXML Button addTag;
	@FXML Button deleteTag;
	@FXML Button left;
	@FXML Button right;
	@FXML TextField tagName;
	@FXML TextField tagValue;
	@FXML Label DateModified;
	@FXML Label CaptionDisplay;
	@FXML Label PhotoName;
	@FXML ImageView Display;
	
	ListView<Photo> photos;
	ArrayList<User> users;
	private ListView<Tag> tags;
	private Album album;
	private User user;
	SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm a");
	
	public void start(ArrayList<User> users, ListView<Photo> photos, 
			User user, Album album) {
		this.users = users;
		this.photos = photos;
		this.album = album;
		this.user = user;
		Photo photo = photos.getSelectionModel().getSelectedItem();
		Display.setImage(photo.getImage());
		PhotoName.setText(photo.getName());
		CaptionDisplay.setText(photo.getCaption());
		DateModified.setText(date.format(photo.getDate().getTime()));
		tags.setItems(FXCollections.observableArrayList(photos.getSelectionModel().getSelectedItem().getTags()));
		tags.getSelectionModel().select(0);
	}
	
	public void switchPhoto(ActionEvent e) {
		Button b = (Button)e.getSource();
		int i = photos.getSelectionModel().getSelectedIndex();
		//Cycle through Photos
		if(b == right) {
			if(i < photos.getItems().size())
				i++;
			else
				i = 0;
		}
		else {
			if(i > 0)
				i--;
			else
				i = photos.getItems().size() - 1;
		}
		
		//Display
		photos.getSelectionModel().clearAndSelect(i);
		Photo photo = photos.getSelectionModel().getSelectedItem();
		Display.setImage(photo.getImage());
		PhotoName.setText(photo.getName());
		CaptionDisplay.setText(photo.getCaption());
		DateModified.setText(date.format(photo.getDate().getTime()));		
	}
	
	//Add input to tags for Photo 
	public void add() {
		ArrayList<Tag> tagList = photos.getSelectionModel().getSelectedItem().getTags();
		Tag input = new Tag(tagName.getText(), tagValue.getText());
		tagName.clear();
		tagValue.clear();
		
		//Check Duplicate
		for(int i = 0; i < tagList.size(); i++) {
			if(tagList.get(i).equals(input)){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Alert");
				alert.setHeaderText("Error Adding Tag to Photo");
				alert.setContentText("Duplicate Tag for Photo");

				alert.showAndWait();
				return;
			}
		}
		tagList.add(input);
		tags.getItems().add(input);
		tags.refresh();
		tags.getSelectionModel().select(0);
	}
	
	//Delete tag that matches input for Photo
	public void delete() {
		ArrayList<Tag> tagList = photos.getSelectionModel().getSelectedItem().getTags();
		Tag input = new Tag(tagName.getText(), tagValue.getText());
		tagName.clear();
		tagValue.clear();
		
		//Search for input 
		for(int i = 0; i < tagList.size(); i++) {
			if(tagList.get(i).equals(input)) {
				tags.getItems().remove(tagList.get(i));
				tags.refresh();
				tags.getSelectionModel().select(0);
				tagList.remove(i);
				return;
			}
		}
	}
}
