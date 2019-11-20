package controller;


import java.text.*;
import java.time.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import model.User;
import model.Photo;
import model.Tag;
import model.Album;

public class PhotoSearchController {
	@FXML TextField tagName1;
	@FXML TextField tagName2;
	@FXML TextField tagValue1;
	@FXML TextField tagValue2;
	@FXML Button Single;
	@FXML Button Either;
	@FXML Button Both;
	@FXML Button Date;
	@FXML Button Add;
	@FXML Button createAlbum;
	@FXML DatePicker FromDate;
	@FXML DatePicker ToDate;
	@FXML ListView photoDisplay;
	@FXML ListView TagNames;
	@FXML ListView TagValues;
	
	ListView<Tag> tags;
	ArrayList<User> users;
	private User user;
	
	public void start(User user, ArrayList<User> users) {
		this.user = user; 
		this.users = users;
		
		//all tagNames and tagValues
		ArrayList<String> tagNames = new ArrayList<String>();
		ArrayList<String> tagValues = new ArrayList<String>();
		tagNames.add(0, "Tag Name");
		tagValues.add(0, "Tag Value");
		ArrayList<Album> albums = user.getAlbums();
		for(int i = 0; i < albums.size(); i++) {
			ArrayList<Photo> photos = albums.get(i).getPhotos();
			for(int j = 0; j < photos.size(); j++) {
				ArrayList<Tag> tags = photos.get(j).getTags(); 
				for(int k = 0; k < tags.size(); k++) {
					if(!tagNames.contains(tags.get(k).getName()))
						tagNames.add(tags.get(k).getName());
					if(!tagValues.contains(tags.get(k).getValue()))
						tagValues.add(tags.get(k).getValue());}}}
		TagNames.setItems(FXCollections.observableArrayList(tagNames));
		TagValues.setItems(FXCollections.observableArrayList(tagValues));
	}
	
	public void addTag() {
		if(tagName2.getText().isBlank() && tagValue2.getText().isBlank() &&
				!tagName1.getText().isBlank() && !tagValue1.getText().isBlank()) {
			ObservableList<Tag> tagList = tags.getItems();
			Tag input = new Tag(tagName1.getText(), tagValue1.getText());
			for(int i = 0; i < tagList.size(); i++) {
				if(tagList.get(i).equals(input)) {
					//error
					return;
				}
			}
			
			tags.getItems().add(input);
			tags.refresh();
			tags.getSelectionModel().select(0);
			TagNames.getSelectionModel().select(0);
			TagValues.getSelectionModel().select(0);
			
		} else {
			//Error
		}
	}
	
	public void search(ActionEvent e) {
		photoDisplay.getItems().clear();
		ArrayList<Album> albums = user.getAlbums();
		ArrayList<Photo> displayList = new ArrayList<Photo>();
		Tag input1, input2;
		boolean one, two;
		if(tagName1.getText().isEmpty() && tagValue1.getText().isEmpty())
			one = false;
		else 
			one = true;
		if(tagName2.getText().isEmpty() && tagValue2.getText().isEmpty())
			two = false;
		else 
			two = true;
		
		Button b = (Button)e.getSource();
		if(b == Single) {
			if(one && !two) {
				input1 = new Tag(tagName1.getText(), tagValue1.getText());
				
				for(int i = 0; i < albums.size(); i++) {
					ArrayList<Photo> photos = albums.get(i).getPhotos();
					for(int j = 0; j < photos.size(); j++) {
						ArrayList<Tag> tags = photos.get(j).getTags();
						for(int k = 0; k < tags.size(); k++) {
							if(tags.get(k).equals(input1)) {
								displayList.add(photos.get(j));
								break;}}}}
			}
			else
				//error
				return;
		} else if(b == Either) {
			if(one && two) {
				input1 = new Tag(tagName1.getText(), tagValue1.getText());
				input2 = new Tag(tagName2.getText(), tagValue2.getText());
				
				for(int i = 0; i < albums.size(); i++) {
					ArrayList<Photo> photos = albums.get(i).getPhotos();
					for(int j = 0; j < photos.size(); j++) {
						ArrayList<Tag> tags = photos.get(j).getTags();
						for(int k = 0; k < tags.size(); k++) {
							if(tags.get(k).equals(input1) || tags.get(k).equals(input2)) {
								displayList.add(photos.get(j));
								break;}}}}
			}
			else 
				return;
		} else if(b == Both) {
			if(one && two) {
				input1 = new Tag(tagName1.getText(), tagValue1.getText());
				input2 = new Tag(tagName2.getText(), tagValue2.getText());
				
				//reuse for whether photo has both tags
				one = false;
				two = false; 
				
				for(int i = 0; i < albums.size(); i++) {
					ArrayList<Photo> photos = albums.get(i).getPhotos();
					for(int j = 0; j < photos.size(); j++) {
						ArrayList<Tag> tags = photos.get(j).getTags();
						for(int k = 0; k < tags.size(); k++) {
							if(tags.get(k).equals(input1)) 
								one = true;
							if(tags.get(k).equals(input2))
								two = true;
						}
						if(one && two)
							displayList.add(photos.get(j));
							one = false;
							two = false;
						}
					}
			}
			
		} else if(b == Date) {
			
		} else
			return;
		
		photoDisplay.setItems(FXCollections.observableArrayList(displayList));
		photoDisplay.getSelectionModel().select(0);
		
	}
	
	public boolean checkDates() {
		if(FromDate.getValue() == null || ToDate.getValue() == null) {
			return false;
		}
		else
			return true;
		
	}
	
}
