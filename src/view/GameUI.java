package view;

/* GameUI.java
 * 
 * This class is the main view class and is used to present the UI to the user
 * 
 * Author: Kenny Li, James Flood
 * 
 * 2018
 * 
 */

import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import model.*;
import model.Character;

public class GameUI {
	
	public GameUI() {
		
	}
	
	public static void spawn(Character actor) {
		actor.getLayer().getChildren().add(actor.getImageView());
	}
	
	public static void spawn(Item item) {
		item.getLayer().getChildren().add(item.getImageView());
	}
	
	public static void spawn(Box box) {
		box.getLayer().getChildren().add(box.getImageView());
	}
	
	public static void deSpawn(Character actor) {
		actor.getLayer().getChildren().remove(actor.getImageView());
	}
	
	public static void deSpawn(Item item) {
		item.getLayer().getChildren().remove(item.getImageView());
	}
	
	public static void deSpawn(Box box) {
		box.getLayer().getChildren().remove(box.getImageView());
	}
	
	public static void updateActors(ArrayList<Character> actors) {
		for(Character x:actors) {
			x.getImageView().relocate(x.getXPos(), x.getYPos());
		}
	}	
	
	public static void updateBoxes(ArrayList<Box> walls) {
		for(Box x:walls) {
			x.getImageView().relocate(x.getXPos(), x.getYPos());
		}
	}
	
	public static void updateItems(ArrayList<Item> items) {
		for(Item x:items) {
			x.getImageView().relocate(x.getXPos(), x.getYPos());
		}
	}
}
