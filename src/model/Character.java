package model;

import controller.CollisionDetection;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/*Parent class for all characters - AI or player controlled
 *
 */

public class Character {
	AnchorPane layer;//Where to draw the character
	private Image image;//How the character
	private ImageView imageView;// should look
	private double xPos;
	private double yPos;
	private double dx;
	private double dy;
	private double height;
	private double width;
	boolean isPlayer1; // determines if AI or human. change to int for MP?
	boolean isPlayer2;
	boolean UP;
	boolean DOWN;
	boolean LEFT;
	boolean RIGHT;
	
	public Character(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth) {
		this.layer = layer;
		setXPos(xStart);
		setYPos(yStart);
		this.isPlayer1 = isPlayer;
		this.height = setHeight;
		this.width = setWidth;
	}

	public void move(double xMove, double yMove) {//, CollisionDetection detector) {
	//	if(!detector.willCollide(this)) {
			setXPos(getXPos() + xMove);
			setYPos(getYPos() + yMove);
		//}
	}
	public void changeMove() {
		setXPos(getXPos() + dx);
		setYPos(getYPos() + dy);
	}
	
	public void setUP(boolean directionFlag) {
		this.UP = directionFlag;
	}
	
	public boolean getUP() {
		return this.UP;
	}
	
	public void setDOWN(boolean directionFlag) {
		this.DOWN = directionFlag;
	}
	
	public boolean getDOWN() {
		return this.DOWN;
	}
	
	public void setLEFT(boolean directionFlag) {
		this.LEFT = directionFlag;
	}
	
	public boolean getLEFT() {
		return this.LEFT;
	}
	
	public void setRIGHT(boolean directionFlag) {
		this.RIGHT = directionFlag;
	}
	
	public boolean getRIGHT() {
		return this.RIGHT;
	}
	
	public double getXPos() {
		return xPos;
	}

	public void setXPos(double xPos) {
		this.xPos = xPos;
	}
	
	public double getYPos() {
		return yPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	
	public double getDx() {
		return dx;
	}

	public void setDx(double xVelocity) {
		this.dx = xVelocity;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double yVelocity) {
		this.dy = yVelocity;
	}

	//Returns a rectangle for use with CollisionDetection
	public Rectangle getBoundary() {
		return new Rectangle(getXPos(), getYPos(), width, height);
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