package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Box {
    private double xPos;
    private double yPos;
    private double height;
    private double width;
    private Image image;
    private ImageView imageView;
    AnchorPane layer;


    Box(AnchorPane layer, double xPos, double yPos, double setHeight, double setWidth) {
        this.layer = layer;
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = setHeight;
        this.width = setWidth;
    }

    public double getXPos() {
        return this.xPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public void setLayer(AnchorPane layer) {
    	this.layer = layer;
    }
    
    public AnchorPane getLayer() {
    	return this.layer;
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

    public void addToLayer() {
        this.layer.getChildren().add(this.getImageView());
    }

    public void updateUI() {
        getImageView().relocate(this.getXPos(),this.getYPos());
    }
}
