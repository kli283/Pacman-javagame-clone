package model;

import javafx.scene.layout.AnchorPane;

public class TestRobber extends Character {

	public TestRobber(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth) {
		super(layer, xStart, yStart, isPlayer, setHeight, setWidth);
		
		this.setImage("/model/TestRobber.png");
	}
	
	public void addToLayer() {
        this.layer.getChildren().add(this.getImageView());
    }
	
	public void updateUI() {
		getImageView().relocate(this.getXPos(),this.getYPos());
	}
}