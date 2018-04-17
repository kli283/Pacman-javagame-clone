package model;

import javafx.scene.layout.AnchorPane;

public class TestMan extends Character {

	public TestMan(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth, double playerSpeed, boolean isGG) {
		super(layer, xStart, yStart, isPlayer, setHeight, setWidth, playerSpeed, isGG);
		
		this.setImage("/view/Resources/GoldGirl.png");
		this.isPlayer = true;
		this.canAttackRobber = false;
	}
	
//	public void addToLayer() {
//        this.layer.getChildren().add(this.getImageView());
//    }
	
	public void updateUI() {
		getImageView().relocate(this.getXPos(),this.getYPos());
	}
}