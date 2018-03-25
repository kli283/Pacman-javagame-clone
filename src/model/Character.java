package model;


/*Parent class for all characters - AI or player controlled
 *
 */

public class Character {
	private int xPos;
	private int yPos;
	boolean isPlayer; // determines if AI or human. change to int for MP?
	
	public Character(int xStart, int yStart, boolean isPlayer) {
		xPos = xStart;
		yPos = yStart;
		this.isPlayer = isPlayer;
	}
	
	public void moveLeft() {
		if(!collisionDetect()){
			xPos -= 1;
		}
	}
	
	public void moveUp() {
		if(!collisionDetect()){
			yPos += 1;
		}
	}
	
	public void moveRight() {
		//TODO will update position of char if not at some boundary TBC
		if(!collisionDetect()){
			xPos += 1;
			}
	}
	
	public void moveDown() {
		if(!collisionDetect()){
			yPos -= 1;
		}
	}
}