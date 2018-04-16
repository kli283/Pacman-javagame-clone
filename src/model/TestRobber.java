package model;

import javafx.scene.layout.AnchorPane;

public class TestRobber extends Character {

	public TestRobber(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth, double playerSpeed) {
		super(layer, xStart, yStart, isPlayer, setHeight, setWidth, playerSpeed);
		
		this.setImage("/view/Resources/TestRobber.png");
		
		this.canAttackPlayer = true;
	}
}