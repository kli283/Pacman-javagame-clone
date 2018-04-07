package model;

import java.io.FileInputStream;

import javafx.scene.image.Image;

public class TestMan extends Character {

	public TestMan(double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth) {
		super(xStart, yStart, isPlayer, setHeight, setWidth);
		
		this.setImage("testMan.png");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
