package model;

import javafx.scene.layout.AnchorPane;

public class Robber1 extends Character {

	public Robber1(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth, double playerSpeed, boolean isGG) {
		super(layer, xStart, yStart, isPlayer, setHeight, setWidth, playerSpeed, isGG);
		
		this.setImage("/view/Resources/Robber1.png");

		this.canAttackPlayer = true;
		this.dumbAI = false;
		this.dumberAI = true;
		this.isRobber = true;
		
	}


}