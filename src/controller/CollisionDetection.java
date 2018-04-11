package controller;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
import model.Barrier;
import model.TestMan;
import model.Character;

//this class will check if a move is valid
// i.e there is no obstacle or boundary in the way of the intended move

public class CollisionDetection {
	
	public boolean scanCollisions(Character mover, ArrayList<Rectangle> rectangle) {
		Rectangle intendedMove = mover.getBoundary();
		int i = 0;
		
		for(Rectangle x:rectangle) {
			if(mover.getDx() > 0) { //If moving right
				//intendedMove = mover.getBoundary();
				intendedMove.setX(mover.getXPos()+1);
			}
			else if(mover.getDx() < 0) { // If moving left
				//intendedMove = mover.getBoundary();
				intendedMove.setX(mover.getXPos()-1);
			}
			else if(mover.getDy() > 0) { // If moving down
				//intendedMove = mover.getBoundary();
				intendedMove.setY(mover.getYPos()+1);
			}
			else if(mover.getDy() < 0) { // If moving up
				//Rectangle intendedMove = mover.getBoundary();
				intendedMove.setY(mover.getYPos()-1);
			}
			if(intendedMove.getBoundsInParent().intersects(x.getBoundsInParent())) {
				System.out.println(i);
				return true;
			}
			i++;
		}
		return false;
	}
	
	public boolean willCollide(Character mover, ArrayList<Rectangle> rectangle) {
		for(Rectangle x:rectangle) {
			if(mover.getBoundary().intersects(x.getBoundsInParent())) {
				return true;
			}
		}
		return false;
	}
}