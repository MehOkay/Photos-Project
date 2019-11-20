package controller;


import java.text.*;
import java.time.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
	@FXML Button SearchOr;
	@FXML Button SearchAnd;
	@FXML Button SearchDate;
	@FXML Button createAlbum;
	@FXML DatePicker FromDate;
	@FXML DatePicker ToDate;
	@FXML ListView photoDisplay;
	
	ArrayList<User> users;
	private User user;
	boolean dateCheck;
	boolean dateEmpty;
	
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
	}
	
	public void search(ActionEvent e) {
		photoDisplay.getItems().clear();
		if(checkDates())
			dateCheck = false;
		else 
			dateCheck = true;
		
		Button b = (Button)e.getSource();
		if(b == SearchOr) {
			if(dateCheck)
		}
		else if(b == SearchAnd) {
			
		}
		
	}
	
	public boolean checkDates() {
		if(FromDate.getValue() == null || ToDate.getValue() == null) {
			if(FromDate.getValue() == null && ToDate.getValue() == null)
				dateEmpty = true;
			return false;
		}
		else
			return true;
		
	}
	
}
