package model;

import javafx.scene.layout.AnchorPane;

public class TestMan extends Character {

	public TestMan(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth, double playerSpeed) {
		super(layer, xStart, yStart, isPlayer, setHeight, setWidth, playerSpeed);
		
		this.setImage("/view/Resources/GoldGirl.png");
		this.isPlayer1 = true;
	}
	
//	public void addToLayer() {
//        this.layer.getChildren().add(this.getImageView());
//    }
	
	public void updateUI() {
		getImageView().relocate(this.getXPos(),this.getYPos());
	}
}