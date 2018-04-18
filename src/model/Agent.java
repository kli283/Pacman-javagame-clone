package model;

import javafx.scene.layout.AnchorPane;

public class Agent extends Character {

	public Agent(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth, double playerSpeed, boolean isGG) {
		super(layer, xStart, yStart, isPlayer, setHeight, setWidth, playerSpeed, isGG);
		
		this.setImage("/view/Resources/Agent.png");
		
		this.canAttackPlayer = true;
		this.isAgent = true;
	}
}