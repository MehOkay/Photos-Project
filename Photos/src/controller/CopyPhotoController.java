package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


public class CopyPhotoController {
	@FXML ListView<String> AlbumList;
	@FXML Button CopyP;
	User user;
	ListView<Album> albums;
	Photo photo;
	
	public void start(Photo photo, User user, ListView<Album> albums) {
		this.user = user;
		this.photo = photo;
		this.albums = albums;
		AlbumList.setItems(FXCollections.observableArrayList(albums.getSelectionModel().getSelectedItem().getName()));
		AlbumList.getSelectionModel().select(0);
		AlbumList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		CopyP.setOnAction(event -> {
			ObservableList<String> selected = FXCollections
					.observableArrayList(AlbumList.getSelectionModel().getSelectedIndices().toString());
			for (int i = 0; i < selected.size(); i++) {
				for (int j = 0; j < albums.getItems().size(); j++) {
					if (selected.get(i).toString().equals(albums.getItems().get(j).getName())) {
						if (!albums.getItems().contains(photo))
							albums.getItems().get(j).getPhotos().add(photo);
						else {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Copy Alert");
							alert.setHeaderText("Copy photo error");
							alert.setContentText("Photo exists in " + albums.getItems().get(j));
							alert.showAndWait();
							return;
						}
					}
				}
			}
		});

	}
}
