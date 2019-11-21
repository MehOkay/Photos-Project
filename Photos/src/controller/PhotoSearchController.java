package controller;

import java.text.*;
import java.time.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import javafx.scene.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Calendar;
import java.util.ArrayList;
import model.User;
import model.Photo;
import model.SerializeData;
import model.Tag;
import model.Album;
import model.ImageCell;

public class PhotoSearchController {
	@FXML
	TextField tagName1, tagName2, tagValue1, tagValue2;
	@FXML
	Button Single, Either, Both, Date, Add, createAlbum;
	@FXML
	DatePicker FromDate, ToDate;
	@FXML
	ListView<Photo> photoDisplay;
	@FXML
	ListView<String> TagNames;
	@FXML
	ListView<String> TagValues;

	ListView<Tag> tags;
	ArrayList<User> users;
	private User user;

	public void start(User user, ArrayList<User> users) {
		this.user = user;
		this.users = users;

		// all tagNames and tagValues
		ArrayList<String> tagNames = new ArrayList<String>();
		ArrayList<String> tagValues = new ArrayList<String>();
		tagNames.add(0, "Tag Name");
		tagValues.add(0, "Tag Value");
		ArrayList<Album> albums = user.getAlbums();
		for (int i = 0; i < albums.size(); i++) {
			ArrayList<Photo> photos = albums.get(i).getPhotos();
			for (int j = 0; j < photos.size(); j++) {
				ArrayList<Tag> tags = photos.get(j).getTags();
				for (int k = 0; k < tags.size(); k++) {
					if (!tagNames.contains(tags.get(k).getName()))
						tagNames.add(tags.get(k).getName());
					if (!tagValues.contains(tags.get(k).getValue()))
						tagValues.add(tags.get(k).getValue());
				}
			}
		}
		
		photoDisplay.setCellFactory(
				new Callback<ListView<Photo>, ListCell<Photo>>() {
					@Override
					public ListCell<Photo> call(ListView<Photo> photoList) {
						return new ImageCell();
					}
			});

		TagNames.setItems(FXCollections.observableArrayList(tagNames));
		TagValues.setItems(FXCollections.observableArrayList(tagValues));
	}

	public void search(ActionEvent e) {
		photoDisplay.getItems().clear();
		ArrayList<Album> albums = user.getAlbums();
		ArrayList<Photo> displayList = new ArrayList<Photo>();
		Tag input1, input2;

		boolean one, two;
		if (tagName1.getText().isEmpty() && tagValue1.getText().isEmpty())
			one = false;
		else
			one = true;
		if (tagName2.getText().isEmpty() && tagValue2.getText().isEmpty())
			two = false;
		else
			two = true;

		Button b = (Button) e.getSource();
		if (b == Single) {
			if (one && !two) {
				input1 = new Tag(tagName1.getText(), tagValue1.getText());

				for (int i = 0; i < albums.size(); i++) {
					ArrayList<Photo> photos = albums.get(i).getPhotos();
					for (int j = 0; j < photos.size(); j++) {
						ArrayList<Tag> tags = photos.get(j).getTags();
						for (int k = 0; k < tags.size(); k++) {
							if (tags.get(k).equals(input1)) {
								photoDisplay.getItems().add(photos.get(j));
								break;
							}
						}
					}
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Alert");
				alert.setHeaderText("Error Searching for Tag");
				alert.setContentText("Tag Name 2 and Tag Value 2 text field must be empty");

				alert.showAndWait();
				return;
			}
		} else if (b == Either) {
			if (one && two) {
				input1 = new Tag(tagName1.getText(), tagValue1.getText());
				input2 = new Tag(tagName2.getText(), tagValue2.getText());

				for (int i = 0; i < albums.size(); i++) {
					ArrayList<Photo> photos = albums.get(i).getPhotos();
					for (int j = 0; j < photos.size(); j++) {
						ArrayList<Tag> tags = photos.get(j).getTags();
						for (int k = 0; k < tags.size(); k++) {
							if (tags.get(k).equals(input1) || tags.get(k).equals(input2)) {
								photoDisplay.getItems().add(photos.get(j));
								break;
							}
						}
					}
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Alert");
				alert.setHeaderText("Error Searching for Tag");
				alert.setContentText("At least 1 text field is empty");

				alert.showAndWait();
				return;
			}
		} else if (b == Both) {
			if (one && two) {
				input1 = new Tag(tagName1.getText(), tagValue1.getText());
				input2 = new Tag(tagName2.getText(), tagValue2.getText());

				// reuse for whether photo has both tags
				one = false;
				two = false;

				for (int i = 0; i < albums.size(); i++) {
					ArrayList<Photo> photos = albums.get(i).getPhotos();
					for (int j = 0; j < photos.size(); j++) {
						ArrayList<Tag> tags = photos.get(j).getTags();
						for (int k = 0; k < tags.size(); k++) {
							if (tags.get(k).equals(input1))
								one = true;
							if (tags.get(k).equals(input2))
								two = true;
						}
						if (one && two)
							photoDisplay.getItems().add(photos.get(j));
						one = false;
						two = false;
					}
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Alert");
				alert.setHeaderText("Error Searching for Tag");
				alert.setContentText("At least 1 text field is empty");

				alert.showAndWait();
				return;
			}
		} else if (b == Date) {
			if (checkDates()) {
				
				String[] from = FromDate.getValue().toString().split("-");
				String[] to = ToDate.getValue().toString().split("-");
				LocalDate fr = LocalDate.of(Integer.parseInt(from[0]), Integer.parseInt(from[1]),
						Integer.parseInt(from[2]));
				LocalDate t = LocalDate.of(Integer.parseInt(to[0]), Integer.parseInt(to[1]), 
						Integer.parseInt(to[2]));

				for (int i = 0; i < albums.size(); i++) {
					ArrayList<Photo> photos = albums.get(i).getPhotos();
					for (int j = 0; j < photos.size(); j++) {
						LocalDate localDate = LocalDateTime.ofInstant(photos.get(j).getDate().toInstant(), 
								photos.get(j).getDate().getTimeZone().toZoneId()).toLocalDate();
						if (localDate.isAfter(fr) && localDate.isBefore(t)) {
							System.out.println("here");
							if (!displayList.contains(photos.get(j))) {
							
								photoDisplay.getItems().add(photos.get(j));
							}
				}}}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Alert");
				alert.setHeaderText("Error Searching for Tag");
				alert.setContentText("Empty Calendar Field");

				alert.showAndWait();
				return;
			}
		} 
		photoDisplay.refresh();
		
		photoDisplay.getSelectionModel().select(0);
		tagName1.clear();
		tagValue1.clear();
		tagName2.clear();
		tagValue2.clear();

	}

	public boolean checkDates() {
		if (FromDate.getValue() == null || ToDate.getValue() == null) {
			return false;
		} else
			return true;
	}

	public void createFromSearch() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar date = Calendar.getInstance();
		Album album = new Album("Search Result " + dateFormat.format(date.getTime()).toString());
		user.getAlbums().add(album);

		for (int i = 0; i < photoDisplay.getItems().size(); i++)
			album.getPhotos().add(photoDisplay.getItems().get(i));
		
		SerializeData.writeData(users);
	}
}
