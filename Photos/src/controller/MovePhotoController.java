package controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.*;
import model.Photo;
import model.User;
import model.Album;


public class MovePhotoController {
	@FXML ListView<String> AlbumList;
	@FXML Button MoveP;
	User user;
	ListView<Album> albums;
	Photo photo;
	Album album;
	
	public void start(Album album, Photo photo, User user, ListView<Album> albums) {
		this.user = user;
		this.photo = photo;
		this.albums = albums;
		AlbumList.setItems(FXCollections.observableArrayList(albums.getSelectionModel().getSelectedItem().getName()));
		AlbumList.getSelectionModel().select(0);

		MoveP.setOnAction(event -> {
			String dest = AlbumList.getSelectionModel().getSelectedItem(); 
			Album d;
			for (int i = 0; i < albums.getItems().size(); i++) {
				if(albums.getItems().get(i).getName().equals(dest)) 
					d = albums.getItems().get(i);
			}
			
			d.getPhotos().add(photo);
			for(int i = 0; i < album.getPhotoCount(); i++)
			{
				if(album.getPhotos().get(i).equals(photo)) {
					album.getPhotos().remove(i);
					return;
				}
			}
			
		});

	}
}