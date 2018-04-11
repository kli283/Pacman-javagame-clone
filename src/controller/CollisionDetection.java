package controller;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import model.Barrier;
import model.TestMan;
import model.Character;

//this class will check if a move is valid
// i.e there is no obstacle or boundary in the way of the intended move

public class CollisionDetection {
	
	public boolean scanCollisions(Character mover, ArrayList<Rectangle2D> rectangle) {
		for(Rectangle2D x:rectangle) {
			if(mover.getBoundary().intersects(x)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean willCollide(Character mover, ArrayList<Rectangle2D> rectangle) {
		for(Rectangle2D x:rectangle) {
			if(mover.getBoundary().intersects(x)) {
				return true;
			}
		}
		return false;
	}

}
