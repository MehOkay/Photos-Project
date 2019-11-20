package model;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 * Displays photo thumbnail in listview. This class extends the ListCell class and is used to override the default ListCell for ListView
 * 
 * 
 * @author Wesley Cheung
 * @author Dennis Yu
 */
public class ImageCell extends ListCell<Photo> {

	Label caption = new Label(), captionValue = new Label(), name = new Label(), nameValue = new Label();
	ImageView imageView = new ImageView();
	AnchorPane anchorPane = new AnchorPane();
	StackPane stackPane = new StackPane();
	
	

	/**
	 * Creates a new PhotoCell object. 
	 */
	public ImageCell() {
		super();

		name.setFont(Font.font("System", FontWeight.BOLD, 12));
		
		nameValue.setFont(Font.font(12));
		
		caption.setFont(Font.font("System", FontWeight.BOLD, 12));
		
		captionValue.setFont(Font.font(12));

		imageView.setFitWidth(45.0);
		imageView.setFitHeight(45.0);
		imageView.setPreserveRatio(false);

		AnchorPane.setTopAnchor(caption, 40.0);
		
		AnchorPane.setLeftAnchor(caption, 70.0);
		
		AnchorPane.setLeftAnchor(captionValue, 120.0);
		
		AnchorPane.setTopAnchor(captionValue, 40.0);
		
		
		
		StackPane.setAlignment(imageView, Pos.CENTER);

		stackPane.getChildren().add(imageView);

		stackPane.setPrefHeight(80.0);
		stackPane.setPrefWidth(65.0);

		AnchorPane.setLeftAnchor(stackPane, 0.0);

		AnchorPane.setLeftAnchor(name, 70.0);
		
		AnchorPane.setTopAnchor(name, 15.0);
		
		AnchorPane.setLeftAnchor(nameValue, 120.0);
		
		AnchorPane.setTopAnchor(nameValue, 15.0);

		

		anchorPane.getChildren().addAll(stackPane, name, nameValue, caption, captionValue);

		anchorPane.setPrefHeight(55.0);

		caption.setMaxWidth(300.0);

		setGraphic(anchorPane);
	}

	@Override
	/**
	 * Updates item
	 */
	public void updateItem(Photo photo, boolean empty) {
		super.updateItem(photo, empty);
		setText(null);
		
		if (photo == null) {
			name.setText(" ");
			
			imageView.setImage(null);
			caption.setText(" ");
			nameValue.setText(" ");
			
			captionValue.setText(" ");
		}
		
		else if (photo != null) {
			name.setText("Name: ");
			imageView.setImage(photo.getImage());
			
			nameValue.setText(photo.getName());
			caption.setText("Caption: ");
			
			captionValue.setText(photo.getCaption());
		}
	}

}