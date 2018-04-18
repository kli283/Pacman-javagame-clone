package model;

import javafx.scene.layout.AnchorPane;

public class GoldGirl extends Character {

	public GoldGirl(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth, double playerSpeed, boolean isGG) {
		super(layer, xStart, yStart, isPlayer, setHeight, setWidth, playerSpeed, isGG);
		
		this.setImage("/view/Resources/GoldGirl.png");
		this.isPlayer = true;
		this.isGG = true;
		this.canAttackRobber = false;
	}
}