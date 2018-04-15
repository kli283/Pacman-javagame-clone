package controller;

import model.Character;

public class AIController {
	
	public AIController(Character AI, Character player) {
	}
	
	public void navigate(Character AI, Character player) {
		if(AI.isDumbAI()) {
			if(this.isAbove(AI, player)) {
				AI.setUP(true);
				AI.rotateUP();
				System.out.println("UP");
			}
			else {
				AI.setUP(false);
			}
			if(this.isBelow(AI, player)) {
				AI.setDOWN(true);
				AI.rotateDOWN();
				System.out.println("DOWN");
			}
			else {
				AI.setDOWN(false);
			}
			if(this.isLeft(AI, player)) {
				AI.setLEFT(true);
				AI.rotateLEFT();
				System.out.println("LEFT");
			}
			else {
				AI.setLEFT(false);
			}
			if(this.isRight(AI, player)) {
				AI.rotateRIGHT();
				AI.setRIGHT(true);
				System.out.println("RIGHT");
			}
			else {
				AI.setRIGHT(false);
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
