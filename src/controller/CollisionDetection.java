package controller;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import model.*;
import model.Character;
import view.GameUI;

//this class will check if a move is valid
// i.e there is no obstacle or boundary in the way of the intended move
//Authors: Kenny Li, James Flood
//
//2018

public class CollisionDetection {

	public static Score scoreUpdate;

	//This method is called by the game controller and checks if characters can make moves without going through walls, 
	//and removes items if collected
	public void scanCollisions(ArrayList<Character> movers, ArrayList<Rectangle> listOfWalls, ArrayList<Item> coinList, ArrayList<Item> smallCashList, ArrayList<Item> bigCashList,  ArrayList<Item> carList, ArrayList<Item> cryptoList, ArrayList<Item> flashbangList, ArrayList<Item> goldList) {
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
			if(playerHit(movers)) {//Logic for if a 'baddie' attacks the player
				scoreUpdate.hitRobberScore((int)scoreUpdate.getScoreCount());
				System.out.println("OUCH!");
			}
			if(robberHit(movers)) {//Logic for if the player attacks a baddie while using a powerUp
				System.out.println("RUN OVER!");
			}
		}
		ArrayList<Item> tempCoin = new ArrayList<>();//Checks if a coin is collected
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:coinList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						GameUI.deSpawn(y);
						tempCoin.add(y);

					}
				}
			}
		}
		for (Item coin : tempCoin){//Removes a coin form the map that is collected
			coinList.remove(coin);
			this.scoreUpdate.updateScoreCount(coin.getScore());
			GameController.soundEffects.playCoin();
		}
		ArrayList<Item> tempSmallCash = new ArrayList<>();//Removes the smallCash item if collided with
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:smallCashList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						GameUI.deSpawn(y);
						tempSmallCash.add(y);

					}
				}
			}
		}
		for (Item smallCash : tempSmallCash){
			smallCashList.remove(smallCash);
			GameController.soundEffects.playCash();
			this.scoreUpdate.updateScoreCount(smallCash.getScore());
		}
		ArrayList<Item> tempBigCash = new ArrayList<>();//Removes the bigCash item if collided with
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:bigCashList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						GameUI.deSpawn(y);
						tempBigCash.add(y);

					}
				}
			}
		}
		for (Item bigCash : tempBigCash){
			bigCashList.remove(bigCash);
			GameController.soundEffects.playCash();
			scoreUpdate.updateScoreCount(bigCash.getScore());
		}
		ArrayList<Item> tempGold = new ArrayList<>();//Removes the gold item if collided with
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:goldList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						GameUI.deSpawn(y);
						tempGold.add(y);

					}
				}
			}
		}
		for (Item gold : tempGold){
			goldList.remove(gold);
			GameController.soundEffects.playGold();
			scoreUpdate.updateScoreCount(gold.getScore());
		}
		ArrayList<Item> tempCrypto = new ArrayList<>();//Removes the cryptoCoin item if collided with
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:cryptoList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						GameUI.deSpawn(y);
						tempCrypto.add(y);

					}
				}
			}
		}
		for (Item crypto : tempCrypto){
			cryptoList.remove(crypto);
			GameController.soundEffects.playCash();
			scoreUpdate.updateScoreCount(crypto.getScore());
		}
		ArrayList<Item> tempCar = new ArrayList<>();//Logic for picking up the car pickUp
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:carList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						GameUI.deSpawn(y);
						tempCar.add(y);
						x.pickupCar();
					}
				}
			}
		}
		for (Item car : tempCar){
			carList.remove(car);
			GameController.soundEffects.playCar();
		}
		ArrayList<Item> tempGrenade = new ArrayList<>();//Logic for collecting the flashBang pickUp
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:flashbangList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						GameUI.deSpawn(y);
						tempGrenade.add(y);
						this.triggerFlash(movers);
					}
				}
			}
		}
		for (Item Flashbang : tempGrenade){
			flashbangList.remove(Flashbang);
			GameController.soundEffects.playBang();
		}
	}
	
	public boolean checkUp(Character mover, ArrayList<Rectangle> rectangle) {//Checks if the intended move will collide
		for(Rectangle x:rectangle) {										// with a wall
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
	private boolean playerHit(ArrayList<Character> actors) {
		for(Character x:actors) {
			if(x.isGG()) {
				for(Character y:actors) {
					if((y != x)&&(y.isRobber())&&(y.getBoundary().intersects(x.getBoundary().getBoundsInParent()))&&(y.canAttack())&&(!x.canAttackR())) {
						y.attackScore();
						y.setPlayerSpeed(1);
						GameController.soundEffects.playHit();
						return true;
					}
					else if((y != x)&&(y.isAgent())&&(y.getBoundary().intersects(x.getBoundary().getBoundsInParent()))&&(y.canAttack())&&(!x.canAttackR())) {
						MenuControl.gControl.endGame(true);
						System.out.println("Agent got ya!");
						return false;
					}
				}
			}
		}
		return false;
	}
	 //This method is called to find if a robber can be attacked
	private boolean robberHit(ArrayList<Character> actors) {
		for(Character x:actors) {
			if(x.isGG() && x.canAttackR()) {
				for(Character y:actors) {
					if((y != x)&&(y.getBoundary().intersects(x.getBoundary().getBoundsInParent()))) {
						y.getRunOver();
						GameController.soundEffects.playManHit();
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//This sets off the flashbang item if collected
	private void triggerFlash(ArrayList<Character> actors){
		for(Character x:actors) {
			if(!x.isGG()) {
				x.getStunned();
			}
		}
	}
	
	//Old logic for collisions, checks all 4 directions so has issues with moving parallel along walls. Obsolete.
	public boolean willCollide(Character mover, ArrayList<Rectangle> rectangle) {
		for(Rectangle x:rectangle) {
			if(mover.getBoundary().intersects(x.getBoundsInParent())) {
				return true;
			}
		}
		return false;
	}
}