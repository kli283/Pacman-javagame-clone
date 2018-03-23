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
}