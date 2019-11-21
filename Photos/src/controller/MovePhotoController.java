package controller;

import javafx.fxml.FXML;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import model.Photo;
import model.SerializeData;
import model.User;
import model.Album;


public class MovePhotoController {
	@FXML ListView<String> AlbumList;
	@FXML Button MoveP;
	User user;
	ListView<Album> albums;
	ArrayList<User> users;
	Photo photo;
	Album album;
	Album d;
	
	public void start(Album album, Photo photo, User user, ArrayList<User> users) {
		this.user = user;
		this.users = users;
		this.photo = photo;
		this.albums = albums;
		AlbumList.setItems(FXCollections.observableArrayList(albums.getSelectionModel().getSelectedItem().getName()));
		AlbumList.getSelectionModel().select(0);

		MoveP.setOnAction(event -> {
			String dest = AlbumList.getSelectionModel().getSelectedItem(); 
			
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
		SerializeData.writeData(users);
	}
}