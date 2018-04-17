package model;

import javafx.scene.layout.AnchorPane;

public class Flashbang extends Item{

	public Flashbang(AnchorPane layer, double xPos, double yPos, int score) {
		super(layer, xPos, yPos, score);
		
		this.setImage("/view/Resources/flashbang.png");
		this.setHeight(35);
		this.setWidth(35);
		
	}

}
