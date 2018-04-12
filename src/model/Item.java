package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Item {
	
	private double xPos;
	private double yPos;
	private double height;
	private double width;
	private int score;
	private Image image;
	private ImageView imageView;
	AnchorPane layer;

	public Item(AnchorPane layer, double xPos, double yPos, int score) {
		this.layer = layer;
		this.xPos = xPos;
		this.yPos = yPos;
		this.score = score;
	}
	
	public double getXPos() {
		return this.xPos;
	}
	
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	public double getYPos() {
		return this.yPos;
	}
	
	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Rectangle getBoundary() {
		return new Rectangle(this.xPos, this.yPos, this.width, this.height);
	}
	public void setImage(String string) {
		this.image = new Image(string);
		this.imageView = new ImageView(this.image);
	}

	public ImageView getImageView() {
		return imageView;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
