package controller;

import model.Character;

public class Pathfinding {
	
	private Character subject;
	private Character target;
	private String levelData;
	private int subjectXPos;
	private int subjectYPos;
	private int targetXPos;
	private int targetYPos;
	private enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT
	};
	
	public static Direction getSmartMove() {
		return Direction.UP;
	}
	
	public void findSubject() {
		subjectXPos = (int) (subject.getXPos()/MenuControl.gControl.getPixelScale());
		subjectYPos = (int) (subject.getYPos()/MenuControl.gControl.getPixelScale());
	}
	
	public void findTarget() {
		targetXPos = (int) (target.getXPos()/MenuControl.gControl.getPixelScale());
		targetYPos = (int) (target.getYPos()/MenuControl.gControl.getPixelScale());
	}
	
	

}
