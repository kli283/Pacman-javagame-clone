package controller;

import model.Barrier;
import model.TestMan;
import model.Character;

//this class will check if a move is valid
// i.e there is no obstacle or boundary in the way of the intended move

public class CollisionDetection {
	
	public boolean willCollide(Character mover, Barrier blocker) {
		if(mover.getBoundary().intersects(blocker.getBoundary())) {
			return true;
		}
		
		return false;
	}

}
