package controller;

import model.Character;

public class AIController {
	
	public AIController(Character AI, Character player) {
		
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
	
}
