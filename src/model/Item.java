package model;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Item {
	
	private double xPos;
	private double yPos;
	private double height;
	private double width;
	private Image image;
	
	public Item(double xPos, double yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
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
}