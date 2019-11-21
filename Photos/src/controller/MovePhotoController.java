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
	//@FXML ListView<String> AlbumList;
	@FXML Button MoveP;
	User user;
	
	@FXML
	ListView<Album> albums;

	
	private ArrayList<User> users;
	private Photo selectedPhoto;
	private Album originalAlbum;

	Album d;
	

	//public void start(ArrayList<User> users, Album album, Photo photo, User user, ListView<Album> albums) {
	public void start(ArrayList<User> users, User user, Photo selectedPhoto, Album originalAlbum) {

		this.user = user;

		this.selectedPhoto = selectedPhoto;
		this.originalAlbum = originalAlbum;

		this.albums = albums;
		albums.setItems(FXCollections.observableArrayList(user.getAlbums()));
		albums.getSelectionModel().select(0);
		//AlbumList.setItems(FXCollections.observableArrayList(albums.getSelectionModel().getSelectedItem().getName()));
		//AlbumList.getSelectionModel().select(0);
/*
		MoveP.setOnAction(event -> {
			albums.getSelectionModel().getSelectedItem().getPhotos().add(this.selectedPhoto);
			
			for(int i = 0; i < this.originalAlbum.getPhotoCount(); i++){
				if(this.originalAlbum.getPhotos().get(i).equals(this.selectedPhoto)) {
					originalAlbum.getPhotos().remove(i);
					SerializeData.writeData(users);
					return;
				}
			}
			
			
			//String dest = AlbumList.getSelectionModel().getSelectedItem(); 
			/*
			for (int i = 0; i < albums.getItems().size(); i++) {
				if(albums.getItems().get(i).getName().equals(dest)) 
					d = albums.getItems().get(i);
			}
			
			
<<<<<<< HEAD
			d.getPhotos().add(photo);
			
			
		});*/


	}
	
	public void move() {
		albums.getSelectionModel().getSelectedItem().getPhotos().add(this.selectedPhoto);
		
		for(int i = 0; i < this.originalAlbum.getPhotoCount(); i++){
			if(this.originalAlbum.getPhotos().get(i).equals(this.selectedPhoto)) {
				originalAlbum.getPhotos().remove(i);
				SerializeData.writeData(users);
				return;
			}
		}
	}
	
}