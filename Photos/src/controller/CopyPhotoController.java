package controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.Photo;
import model.User;
import model.SerializeData;
import model.Album;
import java.util.ArrayList;

public class CopyPhotoController {
	@FXML ListView<Album> AlbumList;
	@FXML Button CopyP;
	User user;
	ArrayList<Album> albums;
	ArrayList<User> users;
	Photo photo;
	
	public void start(Photo photo, User user,ArrayList<User> users) {
		this.user = user;
		this.users = users;
		this.photo = photo;
		this.albums = user.getAlbums();
		AlbumList.setItems(FXCollections.observableArrayList(albums));
		AlbumList.getSelectionModel().select(0);
		AlbumList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		CopyP.setOnAction(event -> {
			ObservableList<String> selected = FXCollections
					.observableArrayList(AlbumList.getSelectionModel().getSelectedIndices().toString());
			for (int i = 0; i < selected.size(); i++) {
				for (int j = 0; j < albums.size(); j++) {
					if (selected.get(i).equals(albums.get(j))) {
						if (!albums.contains(photo))
							albums.get(j).getPhotos().add(photo);
						else {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Copy Alert");
							alert.setHeaderText("Copy photo error");
							alert.setContentText("Photo exists in " + albums.get(j));
							alert.showAndWait();
							return;
						}
					}
				}
			}
		});
		SerializeData.writeData(users);
	}
}
