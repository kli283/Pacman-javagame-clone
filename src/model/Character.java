package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/*Parent class for all characters - AI or player controlled
 *
 */

public class Character {
	AnchorPane layer;
	private Image image;
	ImageView imageView;
	 double xPos;
	 double yPos;
	private double dx;
	private double dy;
	private double height;
	private double width;
	boolean isPlayer1; // determines if AI or human. change to int for MP?
	boolean isPlayer2;
	
	public Character(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth) {
		this.layer = layer;
		xPos = xStart;
		yPos = yStart;
		this.isPlayer1 = isPlayer;
		this.height = height;
		this.width = width;
	}
	
	public void move(double xMove, double yMove) {
		xPos += xMove;
		yPos += yMove;
	}
	
	//Returns a rectangle for use with CollisionDetection
	public Rectangle2D getBoundary() {
		return new Rectangle2D(xPos, yPos, height, width);
	}
	
	public void setImage(String string) {
		this.image = new Image(string);
		this.imageView = new ImageView(this.image);
	}
	
	public Image getImage() {
		return image;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
}