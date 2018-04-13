package view;

import java.util.ArrayList;

import javax.xml.stream.events.Characters;

public class GameUI {
	
	public GameUI() {
		
	}
	
	public void draw(ArrayList<Character> actors) {
		for(Character x:actors) {
			x.getImageView().relocate(x.getXPos(), x.getYPos());
		}
	}
	
	public void update() {

	}
	
}
