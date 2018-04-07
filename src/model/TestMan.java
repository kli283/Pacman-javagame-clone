package model;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class TestMan extends Character {

	public TestMan(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth) {
		super(layer, xStart, yStart, isPlayer, setHeight, setWidth);
		
		this.setImage("/model/testMan.png");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void addToLayer() {
        this.layer.getChildren().add(this.getImageView());
    }
	
	public void updateUI() {
		imageView.relocate(this.xPos,this.yPos);
	}
}