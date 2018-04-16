package controller;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import model.*;
import model.Character;
import view.GameUI;

//this class will check if a move is valid
// i.e there is no obstacle or boundary in the way of the intended move

public class CollisionDetection {

	//public static SoundEffects soundEffects;
	public static int scoreUpdate = 0;
	
	//This method is called by the game controller and checks if characters can make moves without going through walls, 
	//and removes items if collected
	public void scanCollisions(ArrayList<Character> movers, ArrayList<Rectangle> listOfWalls, ArrayList<Item> coinList,ArrayList<Item> smallCashList, ArrayList<Item> bigCashList,  ArrayList<Item> carList) {
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
			else if((x.getDx() < 0) && (this.checkLeft(x, listOfWalls))) {
				x.setDx(0);
			}
			else if((x.getDy() > 0) && (this.checkDown(x, listOfWalls))) {
				x.setDy(0);
			}
			else if((x.getDy() < 0) && (this.checkUp(x, listOfWalls))) {
				x.setDy(0);
			}
			if(playerHit(movers)) {
				scoreUpdate *= 0.7;
				System.out.println("OUCH!");
			}
		}
		ArrayList<Item> tempCoin = new ArrayList<>();
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:coinList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						//y.removeFromLayer();
						GameUI.deSpawn(y);
						tempCoin.add(y);

					}
				}
			}
		}
		for (Item coin : tempCoin){
			coinList.remove(coin);
			scoreUpdate += coin.getScore();
			GameController.soundEffects.playCoin();

			System.out.println(scoreUpdate);
		}
		ArrayList<Item> tempSmallCash = new ArrayList<>();
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:smallCashList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						//y.removeFromLayer();
						GameUI.deSpawn(y);
						tempSmallCash.add(y);

					}
				}
			}
		}
		for (Item smallCash : tempSmallCash){
			smallCashList.remove(smallCash);
			GameController.soundEffects.playCash();
			scoreUpdate += smallCash.getScore();
			System.out.println(scoreUpdate);
		}
		ArrayList<Item> tempBigCash = new ArrayList<>();
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:bigCashList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						//y.removeFromLayer();
						GameUI.deSpawn(y);
						tempBigCash.add(y);

					}
				}
			}
		}
		for (Item bigCash : tempBigCash){
			bigCashList.remove(bigCash);
			GameController.soundEffects.playCash();
			scoreUpdate += bigCash.getScore();
			System.out.println(scoreUpdate);
		}
		ArrayList<Item> tempCar = new ArrayList<>();
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:carList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						//y.removeFromLayer();
						GameUI.deSpawn(y);
						x.setPlayerSpeed(5);
						tempCar.add(y);

					}
				}
			}
		}
		for (Item car : tempCar){
			carList.remove(car);
			GameController.soundEffects.playCar();
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
	
	//This method is called to see if a player gets touched by an enemy who can attack
	public boolean playerHit(ArrayList<Character> actors) {
		for(Character x:actors) {
			if(x.isHuman()) {
				for(Character y:actors) {
					if((y != x)&&(y.getBoundary().intersects(x.getBoundary().getBoundsInParent()))&&(y.canAttack())) {
						y.attackScore();
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//Old logic for collisions, checks all 4 directions so has issues with moving parallel along walls
	public boolean willCollide(Character mover, ArrayList<Rectangle> rectangle) {
		for(Rectangle x:rectangle) {
			if(mover.getBoundary().intersects(x.getBoundsInParent())) {
				return true;
			}
		}
		return false;
	}

}
