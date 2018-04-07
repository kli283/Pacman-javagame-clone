package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/*Parent class for all characters - AI or player controlled
 *
 */

public class Character {
	private Image image;
	private double xPos;
	private double yPos;
	private double dx;
	private double dy;
	private double height;
	private double width;
	boolean isPlayer1; // determines if AI or human. change to int for MP?
	boolean isPlayer2;
	
	public Character(double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth) {
		xPos = xStart;
		yPos = yStart;
		this.isPlayer1 = isPlayer;
		this.height = height;
		this.width = width;
	}
	
	public void move(double time) {
		xPos += dx*time;
		yPos += dy*time;
	}
	
	//Returns a rectangle for use with CollisionDetection
	public Rectangle2D getBoundary() {
		return new Rectangle2D(xPos, yPos, height, width);
	}
	
	public void setImage(String string) {
		this.image = new Image(string);
	}
	
	public Image getImage() {
		return image;
	}
}