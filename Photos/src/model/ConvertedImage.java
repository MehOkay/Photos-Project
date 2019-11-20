package model;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;


/**
 * A custom image class that implements the Serializable interface
 * 
 * @author Wesley Cheung
 * @author Dennis Yu
 *
 */
public class ConvertedImage implements Serializable {
	
	private static final long serialVersionUID = -226314774912753616L;
	
	private int width, height;
	
	private int[][] pixels;
	
	/**
	 * Constructor for creating a Serializable Image object 
	 * @param image the image to be converted
	 */
	public ConvertedImage(Image image) {
		width = (int)image.getWidth();
		height = (int)image.getHeight();
		
		
		
		pixels = new int[width][height];
		
		PixelReader pr = image.getPixelReader();
		
		for (int w = 0; w < width; w++) {
			
			for (int h = 0; h < height; h++) {
				
				pixels[w][h] = pr.getArgb(w, h);
			}
		}
	}
	
	/**
	 * Converts the serialized representation back to an Image
	 * @return Image object
	 */
	public Image getImage() {
		
		WritableImage i = new WritableImage(width, height);
		
		PixelWriter w = i.getPixelWriter();
		
		for (int j = 0; j < width; j++)
			
			for (int k = 0; k < height; k++)
				
				w.setArgb(j, k, pixels[j][k]);
		
		return i;
	}
	
	/**
	 * returns the image width
	 * @return the width of this image
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * returns image height
	 * @return the height of this image
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * check if two images are equal
	 * 
	 * @param image	The serializable image to be compared to
	 * @return true if they're equal, else false
	 */
	public boolean equals(ConvertedImage image) {
		if (width != image.getWidth() || height != image.getHeight()) {
			return false;
		}
		
		else {
			for (int r = 0; r < width; r++) {
				
				for (int c = 0; c < height; c++) {
					if (pixels[r][c] != image.getPixels()[r][c]) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	
	/**
	 * returns 2d array of pixels
	 * 
	 * @return a 2D int array representing the color values
	 */
	public int[][] getPixels() {
		
		return pixels;
		
		
	}
	
	

	
}
