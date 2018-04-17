package controller;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;
import model.Character;

public class AIController {
	
	public AIController() {}
	
	public void navigate(ArrayList<Character> AI, Character player, CollisionDetection detector, ArrayList<Rectangle> wallList, boolean isPlayer) {
		if (isPlayer == false) {
			for (Character x : AI) {
				if (!x.isHuman()) {
					if (x.isDumbAI()) {
						if (this.isAbove(x, player) && !detector.checkUp(x, wallList)) {
							x.setUP(true);
							x.rotateUP();
							//System.out.println("UP");
						} else {
							x.setUP(false);
						}
						if (this.isBelow(x, player) && !detector.checkDown(x, wallList)) {
							x.setDOWN(true);
							x.rotateDOWN();
							//System.out.println("DOWN");
						} else {
							x.setDOWN(false);
						}
						if (this.isLeft(x, player) && !detector.checkLeft(x, wallList)) {
							x.setLEFT(true);
							x.rotateLEFT();
							//System.out.println("LEFT");
						} else {
							x.setLEFT(false);
						}
						if (this.isRight(x, player) && !detector.checkRight(x, wallList)) {
							x.rotateRIGHT();
							x.setRIGHT(true);
							//System.out.println("RIGHT");
						} else {
							x.setRIGHT(false);
						}
					}
				}
			}
		}
	}
	
	public boolean isAbove(Character AI, Character player) {
		if(player.getYPos()<AI.getYPos() && (Math.abs(player.getYPos() - AI.getYPos())>24)) {
			return true;
		}
		return false;
	}
	
	public boolean isBelow(Character AI, Character player) {
		if(player.getYPos()>AI.getYPos() && (Math.abs(player.getYPos() - AI.getYPos())>24)) {
			return true;
		}
		return false;
	}
	
	public boolean isRight(Character AI, Character player) {
		if(player.getXPos()>AI.getXPos() && (Math.abs(player.getXPos() - AI.getXPos())>24)) {
			return true;
		}
		return false;
	}
	
	public boolean isLeft(Character AI, Character player) {
		if(player.getXPos()<AI.getXPos() && (Math.abs(player.getXPos() - AI.getXPos())>24)) {
			return true;
		}
		return false;
	}
}
