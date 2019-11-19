package controller;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import model.User;
import javafx.scene.Node;

public class AdminController {
	@FXML
	private ListView<User> users;
	
	@FXML
	private TextField userN;
	
	@FXML
	private Button Logout, createU, deleteU,
			listUsersButton;
	
	/**
	 * On start the users are initialized
	 * 
	 * @param users is the list of users that are in the system
	 */
	public void start(ArrayList<User> users) {
		this.users.setItems(FXCollections.observableArrayList(users));
		this.users.getSelectionModel().select(0);
		this.users.setVisible(false);
	}
	/**
	 * Creates a new user and adds them to the system
	 */
	public void createUser() {
		User newUser = new User(userN.getText());
		ObservableList<User> userList = users.getItems();
	
		for (User currentUser : userList) {
			if (currentUser.getUsername().equals(newUser.getUsername())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Admin Alert");
				alert.setHeaderText("Create New User error.");
				alert.setContentText("This user already exists.");

				alert.showAndWait();
				return;
			}
		}

		users.getItems().add(newUser);
		users.refresh();
		writeData();
		userN.clear();
	}
	
	/**
	 * Deletes selected user from the system and removes their data
	 */
	public void deleteUser() {
		User user = users.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Admin Alert");
		alert.setHeaderText("Confirmation Needed");
		alert.setContentText("Are you sure you want to delete \"" + user.getUsername() + "\"'s account?");

		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get().equals(ButtonType.YES)) {
			users.getItems().remove(user);
			users.refresh();
			writeData();
		}
	}
	
	/**
	 * logs out of admin and returns to login screen
	 */
	public void logOut(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent parent = (Parent) loader.load();
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Makes the users visible on click of list user button
	 */
	public void handleListUserButton() {
		users.setVisible(true);
		users.refresh();
	}
	
	/**
	 * Serializes user data and writes it to data.dat file
	 */
	private void writeData() {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("data/data.dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			objectOutputStream.writeObject(new ArrayList<>(Arrays.asList(users.getItems().toArray())));
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
