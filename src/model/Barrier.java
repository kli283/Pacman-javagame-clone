package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Barrier {
	AnchorPane layer;
	private double height;
	private double width;
	private double xPos;
	private double yPos;
	private Image image;
	private ImageView imageView;
	
	public Barrier(double height, double width, double xPos, double yPos, AnchorPane layer){
		this.height = height;
		this.width = width;
		this.xPos = xPos;
		this.yPos = yPos;
		this.layer = layer;
		setImage("/model/testBarrier.png");
	}
	
	public void addToLayer() {
        this.layer.getChildren().add(this.getImageView());
    }
	
	public void updateUI() {
		getImageView().relocate(this.xPos,this.yPos);
	}
	
	//Returns a rectangle for use with CollisionDetection
	public Rectangle2D getBoundary() {
		return new Rectangle2D(xPos, yPos, width, height);
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
