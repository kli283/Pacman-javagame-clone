package controller;

import java.util.ArrayList;
import model.Character;

public class AIController {
	
	public AIController() {}
	
	public void navigate(ArrayList<Character> AI, Character player) {
		for(Character x:AI) {
			if(!x.isHuman()) {
				if(x.isDumbAI()) {
					if(this.isAbove(x, player)) {
						x.setUP(true);
						x.rotateUP();
						System.out.println("UP");
					}
					else {
						x.setUP(false);
					}
					if(this.isBelow(x, player)) {
						x.setDOWN(true);
						x.rotateDOWN();
						System.out.println("DOWN");
					}
					else {
						x.setDOWN(false);
					}
					if(this.isLeft(x, player)) {
						x.setLEFT(true);
						x.rotateLEFT();
						System.out.println("LEFT");
					}
					else {
						x.setLEFT(false);
					}
					if(this.isRight(x, player)) {
						x.rotateRIGHT();
						x.setRIGHT(true);
						System.out.println("RIGHT");
					}
					else {
						x.setRIGHT(false);
					}
				}
			}
		}
	}
	
	public boolean isAbove(Character AI, Character player) {
		if(player.getYPos()<AI.getYPos()) {
			return true;
		}
		return false;
	}
	
	public boolean isBelow(Character AI, Character player) {
		if(player.getYPos()>AI.getYPos()) {
			return true;
		}
		return false;
	}
	
	public boolean isRight(Character AI, Character player) {
		if(player.getXPos()>AI.getXPos()) {
			return true;
		}
		return false;
	}
	
	public boolean isLeft(Character AI, Character player) {
		if(player.getXPos()<AI.getXPos()) {
			return true;
		}
		return false;
	}
	
}
