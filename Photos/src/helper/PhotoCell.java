package helper;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Photo;

/**
 * Displays photo thumbnail in listview
 * @author Wesley Cheung
 * @author Dennis Yu
 */
public class PhotoCell extends ListCell<Photo> {

	Label caption = new Label(), captionValue = new Label(), name = new Label(), nameValue = new Label();
	ImageView imageView = new ImageView();
	AnchorPane anchorPane = new AnchorPane();
	StackPane stackPane = new StackPane();
	
	

	/**
	 * Constructor
	 */
	public PhotoCell() {
		super();

		name.setFont(Font.font("System", FontWeight.BOLD, 12));
		nameValue.setFont(Font.font(12));
		caption.setFont(Font.font("System", FontWeight.BOLD, 12));
		captionValue.setFont(Font.font(12));

		imageView.setFitWidth(60.0);
		imageView.setFitHeight(60.0);
		imageView.setPreserveRatio(false);

		StackPane.setAlignment(imageView, Pos.CENTER);

		stackPane.getChildren().add(imageView);

		stackPane.setPrefHeight(80.0);
		stackPane.setPrefWidth(65.0);

		AnchorPane.setLeftAnchor(stackPane, 0.0);

		AnchorPane.setLeftAnchor(name, 55.0);
		AnchorPane.setTopAnchor(name, 0.0);
		AnchorPane.setLeftAnchor(nameValue, 95.0);
		AnchorPane.setTopAnchor(nameValue, 0.0);

		AnchorPane.setLeftAnchor(caption, 55.0);
		AnchorPane.setTopAnchor(caption, 24.0);
		AnchorPane.setLeftAnchor(captionValue, 105.0);
		AnchorPane.setTopAnchor(captionValue, 24.0);

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
			imageView.setImage(null);
			name.setText(" ");
			nameValue.setText(" ");
			caption.setText(" ");
			captionValue.setText(" ");
		}
		if (photo != null) {
			imageView.setImage(photo.getImage());
			name.setText("Name: ");
			nameValue.setText(photo.getName());
			caption.setText("Caption: ");
			captionValue.setText(photo.getCaption());
		}
	}

}