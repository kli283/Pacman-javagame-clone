package controller;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import model.*;
import model.Character;
import view.GameUI;
import controller.Score;

//this class will check if a move is valid
// i.e there is no obstacle or boundary in the way of the intended move

public class CollisionDetection {

	public static Score scoreUpdate;
	
	//This method is called by the game controller and checks if characters can make moves without going through walls, 
	//and removes items if collected
	public void scanCollisions(ArrayList<Character> movers, ArrayList<Rectangle> listOfWalls, ArrayList<Item> coinList,ArrayList<Item> smallCashList, ArrayList<Item> bigCashList,  ArrayList<Item> carList, ArrayList<Item> cryptoList) {

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
				this.scoreUpdate.hitRobberScore((int)scoreUpdate.getScoreCount());
				System.out.println("OUCH!");
			}
			if(robberHit(movers)) {
				System.out.println("ROBBER HIT!");
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
			this.scoreUpdate.updateScoreCount(coin.getScore());
			GameController.soundEffects.playCoin();

			System.out.println(this.scoreUpdate.getScoreCount());
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
			this.scoreUpdate.updateScoreCount(smallCash.getScore());
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
			this.scoreUpdate.updateScoreCount(bigCash.getScore());
			System.out.println(scoreUpdate);
		}
		ArrayList<Item> tempCrypto = new ArrayList<>();
		for(Character x:movers) {
			if(x.canPickupItems()) {
				for(Item y:cryptoList) {
					if(x.getBoundary().intersects(y.getBoundary().getBoundsInParent())) {
						//y.removeFromLayer();
						GameUI.deSpawn(y);
						tempCrypto.add(y);

					}
				}
			}
		}
		for (Item crypto : tempCrypto){
			cryptoList.remove(crypto);
			GameController.soundEffects.playCash();
			this.scoreUpdate.updateScoreCount(crypto.getScore());
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
						x.canAttackR();

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
						y.setPlayerSpeed(1);
						GameController.soundEffects.playHit();
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean robberHit(ArrayList<Character> actors) {
		for(Character x:actors) {
			if(x.isDumbAI()) {
				for(Character y:actors) {
					if((y != x)&&(y.getBoundary().intersects(x.getBoundary().getBoundsInParent()))&&(y.canAttackR())){
						y.attackScore();
						y.setPlayerSpeed(0);
						GameController.soundEffects.playHit();
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
