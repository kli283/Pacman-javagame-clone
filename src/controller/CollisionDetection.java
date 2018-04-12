package controller;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
import model.Barrier;
import model.TestMan;
import model.Character;
import model.TestCoin;

//this class will check if a move is valid
// i.e there is no obstacle or boundary in the way of the intended move

public class CollisionDetection {

	public static int scoreUpdate = 0;
	//This method is called by the game controller and checks if characters can make moves without going through walls, 
	//and removes items if collected
	public void scanCollisions(ArrayList<Character> movers, ArrayList<Rectangle> listOfWalls, ArrayList<TestCoin> coinList) {
		for(Character x:movers) {
			if(x.getUP() && !this.checkUp(x, listOfWalls)) {
				x.setDy(-x.getPlayerSpeed());
			}
			if(x.getDOWN() && !this.checkDown(x, listOfWalls)) {
				x.setDy(x.getPlayerSpeed());
			}
			if(x.getLEFT() && !this.checkLeft(x, listOfWalls)) {
				x.setDx(-x.getPlayerSpeed());
			}
			if(x.getRIGHT() && !this.checkRight(x, listOfWalls)) {
				x.setDx(x.getPlayerSpeed());
			}
			if((x.getDx() > 0) && (this.checkRight(x, listOfWalls))) {
				x.setDx(0);
			}
			else if((x.getDx() < 0)  && (this.checkLeft(x, listOfWalls))) {
				x.setDx(0);
			}
			else if((x.getDy() > 0) && (this.checkDown(x, listOfWalls))) {
				x.setDy(0);
			}
			else if((x.getDy() < 0) && (this.checkUp(x, listOfWalls))) {
				x.setDy(0);
			}
		}
		ArrayList<TestCoin> tempCoin = new ArrayList<TestCoin>();
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(TestCoin y:coinList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						y.removeFromLayer();
						tempCoin.add(y);

					}
				}
			}
		}
		for (TestCoin coin : tempCoin){
			coinList.remove(coin);
			scoreUpdate += coin.getScore();
			System.out.println(scoreUpdate);
		}
	}
	
	public boolean checkUp(Character mover, ArrayList<Rectangle> rectangle) {
		for(Rectangle x:rectangle) {
			Rectangle intendedMove = mover.getBoundary();
			intendedMove.setY(intendedMove.getY() - mover.getPlayerSpeed());
			if(intendedMove.getBoundsInParent().intersects(x.getBoundsInParent())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkDown(Character mover, ArrayList<Rectangle> rectangle) {
		for(Rectangle x:rectangle) {
			Rectangle intendedMove = mover.getBoundary();
			intendedMove.setY(intendedMove.getY() + mover.getPlayerSpeed());
			if(intendedMove.getBoundsInParent().intersects(x.getBoundsInParent())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkLeft(Character mover, ArrayList<Rectangle> rectangle) {
		for(Rectangle x:rectangle) {
			Rectangle intendedMove = mover.getBoundary();
			intendedMove.setX(intendedMove.getX() - mover.getPlayerSpeed());
			if(intendedMove.getBoundsInParent().intersects(x.getBoundsInParent())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkRight(Character mover, ArrayList<Rectangle> rectangle) {
		for(Rectangle x:rectangle) {
			Rectangle intendedMove = mover.getBoundary();
			intendedMove.setX(intendedMove.getX() + mover.getPlayerSpeed());
			if(intendedMove.getBoundsInParent().intersects(x.getBoundsInParent())) {
				return true;
			}
		}
		return false;
	}
	
	//Old logic for collisions, checks all 4 directions so has issues with moving parallel to walls
	public boolean willCollide(Character mover, ArrayList<Rectangle> rectangle) {
		for(Rectangle x:rectangle) {
			if(mover.getBoundary().intersects(x.getBoundsInParent())) {
				return true;
			}
		}
		return false;
	}

}
