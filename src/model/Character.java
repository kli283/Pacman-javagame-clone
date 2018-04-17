package model;

import controller.MenuControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import view.Menu;

/*Parent class for all characters - AI or player controlled
 *
 */

public class Character {
	private AnchorPane layer;//Where to draw the character
	private Image image;//How the character
	private ImageView imageView;// should look
	private double xPos;
	private double yPos;
	private double dx;
	private double dy;
	private double height;
	private double width;



	boolean isPlayer = false; // determines if GoldGirl is AI or human.
	boolean isRobber = false;
	boolean isAgent = false;
	boolean isGG = false;
	boolean isAI = false;
	boolean dumbAI = false;
	boolean canPickupItems = false;
	boolean canAttackPlayer = false;
	private int lastAttackTime = 120;
	boolean UP;
	boolean DOWN;
	boolean LEFT;
	boolean RIGHT;
	double playerSpeed;
	double defaultPLayerSpeed;
	
	public Character(AnchorPane layer, double xStart, double yStart, boolean isPlayer, double setHeight, double setWidth, double playerSpeed) {
		this.layer = layer;
		setXPos(xStart);
		setYPos(yStart);
		this.canPickupItems = isPlayer;
		this.height = setHeight;
		this.width = setWidth;
		this.playerSpeed = playerSpeed;
		this.defaultPLayerSpeed = playerSpeed;
		if(!this.isPlayer) {
			this.isAI = true;
			this.dumbAI = true;
		}
	}

	public void move(double xMove, double yMove) {
		setXPos(getXPos() + xMove);
		setYPos(getYPos() + yMove);
	}
	
	public void changeMove() {
		setXPos(getXPos() + dx);
		setYPos(getYPos() + dy);
		if ((MenuControl.gControl.timeAmount() <= lastAttackTime - 5) && !this.isPlayer) {
		    playerSpeed = defaultPLayerSpeed;
        }
	}
	
	public boolean canPickupItems() {
		if(this.canPickupItems) {
			return true;
		}
		
		return false;
	}
	
	public boolean isDumbAI() {
		return dumbAI;
	}
	
	public boolean isHuman() {
		return isPlayer;
	}
	
	public AnchorPane getLayer() {
		return this.layer;
	}
	
	public void setLayer(AnchorPane layer) {
		this.layer = layer;
	}
	
	public double getPlayerSpeed() {
		return this.playerSpeed;
	}

	public void setPlayerSpeed(double speed) {
		this.playerSpeed = speed;
	}
	
	public void setUP(boolean directionFlag) {
		this.UP = directionFlag;
	}
	
	public boolean getUP() {
		return this.UP;
	}
	
	public void setDOWN(boolean directionFlag) {
		this.DOWN = directionFlag;
	}
	
	public boolean getDOWN() {
		return this.DOWN;
	}
	
	public void setLEFT(boolean directionFlag) {
		this.LEFT = directionFlag;
	}
	
	public boolean getLEFT() {
		return this.LEFT;
	}
	
	public void setRIGHT(boolean directionFlag) {
		this.RIGHT = directionFlag;
	}
	
	public boolean getRIGHT() {
		return this.RIGHT;
	}

	public void rotateUP() {
		this.imageView.setRotate(180);
	}
	public void rotateDOWN() {
		this.imageView.setRotate(0);
	}
	public void rotateRIGHT() {
		this.imageView.setRotate(270);
	}
	public void rotateLEFT() {
		this.imageView.setRotate(90);
	}

	public double getXPos() {
		return xPos;
	}

	public void setXPos(double xPos) {
		this.xPos = xPos;
	}
	
	public double getYPos() {
		return yPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	
	public double getDx() {
		return dx;
	}

	public void setDx(double xVelocity) {
		this.dx = xVelocity;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double yVelocity) {
		this.dy = yVelocity;
	}
	
	public boolean canAttack() {
		if(MenuControl.gControl.timeAmount() <= lastAttackTime - 5) {
			if(this.canAttackPlayer) {
			    playerSpeed = 2;
				lastAttackTime = MenuControl.gControl.timeAmount();
				return true;
			}
		}
		return false;
	}
	
	public boolean attackScore(){
		if(this.canAttack()) {
			return true;
		}
		return false;
	}

	//Returns a rectangle for use with CollisionDetection
	public Rectangle getBoundary() {
		return new Rectangle(getXPos(), getYPos(), width, height);
	}
	
	public void setImage(String string) {
		this.image = new Image(string);
		this.imageView = new ImageView(this.image);
	}
	
	public Image getImage() {
		return image;
	}
	
	public ImageView getImageView() {
		return imageView;
	}

}