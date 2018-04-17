package model;

import javafx.scene.layout.AnchorPane;

public class TestRobber extends Character {

	public TestRobber(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth, double playerSpeed, boolean isGG) {
		super(layer, xStart, yStart, isPlayer, setHeight, setWidth, playerSpeed, isGG);
		
		this.setImage("/view/Resources/TestRobber.png");
		
		this.canAttackPlayer = true;
	}
}