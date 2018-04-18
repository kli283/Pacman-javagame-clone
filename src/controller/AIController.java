package controller;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.shape.Rectangle;
import model.Character;

/*This class contains the logic for AI navigation
 * 
 * It interacts with the GameController class and the relevant Character subclasses.
 * 
 * It also interacts with the collision detection to prevent the AI from trying to go through walls
 * 
 */

public class AIController {
	
	public AIController() {}
	
	//Receives ArrayLists of characters to navigate, the player to target and collisin detection information
	public void navigate(ArrayList<Character> AI, Character player, CollisionDetection detector, ArrayList<Rectangle> wallList) {
		for (Character x : AI) {
			if (!x.isHuman() && !x.isStunned()) {
				if (x.isDumbAI()||((x.isDumberAI() && (distanceToPlayer(player, x) < 48*7)))) {
					if (this.isAbove(x, player) && !detector.checkUp(x, wallList)) {
						x.setUP(true);
						x.rotateUP();
					} else {
						x.setUP(false);
					}
					if (this.isBelow(x, player) && !detector.checkDown(x, wallList)) {
						x.setDOWN(true);
						x.rotateDOWN();
					} else {
						x.setDOWN(false);
					}
					if (this.isLeft(x, player) && !detector.checkLeft(x, wallList)) {
						x.setLEFT(true);
						x.rotateLEFT();
					} else {
						x.setLEFT(false);
					}
					if (this.isRight(x, player) && !detector.checkRight(x, wallList)) {
						x.rotateRIGHT();
						x.setRIGHT(true);
					} else {
						x.setRIGHT(false);
					}
				}
				if((x.getDx()==0)&&(x.getDy()==0)) {
					Random rand = new Random();
					int countDirection = rand.nextInt(4);
					int countTimer = rand.nextInt(33);
					if (countTimer == 32 && countDirection == 0){
						x.setDx(0);
						x.setDy(-x.getPlayerSpeed());
					}else if (countTimer == 24 && countDirection == 0){
						x.setDx(x.getPlayerSpeed());
						x.setDy(0);
					}else if (countTimer == 16 && countDirection == 0){
						x.setDx(0);
						x.setDy(x.getPlayerSpeed());
					}else if (countTimer == 8 && countDirection == 0){
						x.setDx(-x.getPlayerSpeed());
						x.setDy(0);
					}
				}
			}
		}
	}
	
	public boolean isAbove(Character AI, Character player) {
		if(player.getYPos()<AI.getYPos() && (Math.abs(player.getYPos() - AI.getYPos())>24)) {
			return true;
		}
		return false;
	}
	
	public boolean isBelow(Character AI, Character player) {
		if(player.getYPos()>AI.getYPos() && (Math.abs(player.getYPos() - AI.getYPos())>24)) {
			return true;
		}
		return false;
	}
	
	public boolean isRight(Character AI, Character player) {
		if(player.getXPos()>AI.getXPos() && (Math.abs(player.getXPos() - AI.getXPos())>24)) {
			return true;
		}
		return false;
	}
	
	public boolean isLeft(Character AI, Character player) {
		if(player.getXPos()<AI.getXPos() && (Math.abs(player.getXPos() - AI.getXPos())>24)) {
			return true;
		}
		return false;
	}
	
	public double distanceToPlayer(Character player, Character chaser) {
		return Math.sqrt(Math.pow((player.getXPos()-chaser.getXPos()), 2) + Math.pow((player.getYPos()-chaser.getYPos()), 2));
	}
}
