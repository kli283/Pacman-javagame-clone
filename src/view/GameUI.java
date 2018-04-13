package view;

import java.util.ArrayList;

import model.Character;

public class GameUI {
	
	public GameUI() {
		
	}
	
	public static void update(ArrayList<Character> actors) {
		for(Character x:actors) {
			x.getImageView().relocate(x.getXPos(), x.getYPos());
		}
	}	
}
