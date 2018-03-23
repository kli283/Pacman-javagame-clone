package model;


/*Parent class for all characters - AI or player controlled
 *
 */

public class Character {
	private int xPos;
	private int yPos;
	
	public Character(int xStart, int yStart) {
		xPos = xStart;
		yPos = yStart;
	}
	
	public void moveLeft() {
		xPos -= 1;
	}
	
	public void moveUp() {
		yPos += 1;
	}
	
	public void moveRight() {
		//TODO will update position of char if not at some boundary TBC
		//if(!collisionDetect()){
			xPos += 1;
			//}
	}
	
	public void moveDown() {
		yPos -= 1;
	}
}