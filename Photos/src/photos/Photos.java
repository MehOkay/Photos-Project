package photos;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Photos extends Application {

	/**
	 * starts application
	 * 
	 * @param mainStage
	 *            the main stage
	 */
	public void start(Stage mainStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/login.fxml"));
			Pane root = (Pane) loader.load();
			Scene scene = new Scene(root);

			mainStage.setScene(scene);
			mainStage.setResizable(false);
			mainStage.setTitle("Photo Album");
			mainStage.show();

			LoginController controller = loader.getController();
			controller.start(mainStage);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * main method
	 * 
	 * @param args
	 *            command-line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}