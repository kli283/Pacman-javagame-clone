package controller;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
import model.*;
import model.Character;

//this class will check if a move is valid
// i.e there is no obstacle or boundary in the way of the intended move

public class CollisionDetection {

	public static int scoreUpdate = 0;
	//This method is called by the game controller and checks if characters can make moves without going through walls, 
	//and removes items if collected
	public void scanCollisions(ArrayList<Character> movers, ArrayList<Rectangle> listOfWalls, ArrayList<TestCoin> coinList,ArrayList<SmallCash> smallCashList, ArrayList<BigCash> bigCashList,  ArrayList<Car> carList) {
		for(Character x:movers) {
			if(x.getUP() && !this.checkUp(x, listOfWalls)) {
				x.setDy(-x.getPlayerSpeed());
				x.setDx(0);
			}
			if(x.getDOWN() && !this.checkDown(x, listOfWalls)) {
				x.setDy(x.getPlayerSpeed());
				x.setDx(0);
			}
			if(x.getLEFT() && !this.checkLeft(x, listOfWalls)) {
				x.setDx(-x.getPlayerSpeed());
				x.setDy(0);
			}
			if(x.getRIGHT() && !this.checkRight(x, listOfWalls)) {
				x.setDx(x.getPlayerSpeed());
				x.setDy(0);
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
		ArrayList<TestCoin> tempCoin = new ArrayList<>();
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
		ArrayList<SmallCash> tempSmallCash = new ArrayList<>();
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(SmallCash y:smallCashList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						y.removeFromLayer();
						tempSmallCash.add(y);

					}
				}
			}
		}
		for (SmallCash smallCash : tempSmallCash){
			smallCashList.remove(smallCash);
			scoreUpdate += smallCash.getScore();
			System.out.println(scoreUpdate);
		}
		ArrayList<BigCash> tempBigCash = new ArrayList<>();
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(BigCash y:bigCashList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						y.removeFromLayer();
						tempBigCash.add(y);

					}
				}
			}
		}
		for (BigCash bigCash : tempBigCash){
			bigCashList.remove(bigCash);
			scoreUpdate += bigCash.getScore();
			System.out.println(scoreUpdate);
		}
		ArrayList<Car> tempCar = new ArrayList<>();
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Car y:carList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						y.removeFromLayer();
						x.setPlayerSpeed(6);
						tempCar.add(y);

					}
				}
			}
		}
		for (Car car : tempCar){
			coinList.remove(car);
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
