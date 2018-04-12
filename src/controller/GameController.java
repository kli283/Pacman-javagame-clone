package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Barrier;
import model.Item;
import model.TestCoin;
import model.TestMan;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.fxml.FXML;
import model.TestRobber;
import java.util.Random;

public class GameController { // Class to contain main game loop
	
	private AnchorPane rootLayout;
	TestMan testman;
	TestCoin testCoin;
	TestWall testWall;
	TestRobber testRobber;
	private double playerSpeed = 3;
	private double robberSpeed = 1;
	private ArrayList<Rectangle> mapPath = new ArrayList<Rectangle>();
	@SuppressWarnings("rawtypes")
	private ArrayList charList = new ArrayList<Character>();
	private ArrayList<TestCoin> coinList = new ArrayList<TestCoin>();
	private int pixelScale = 48;
	double coinPosX;
	double coinPosY;
	private int levelWidth;

//	Rectangle rect1;
//	Rectangle rect2;
//	Rectangle rect3;
//	Rectangle rect4;


    CollisionDetection detector = new CollisionDetection();
    Rectangle wall1;
    Rectangle wall2;
	

	public GameController(Stage mainStage) {

		initGameController(mainStage);
		initMap();
		initPlayer();
		initRobber();
		robberMovement();
	}

	private void initMap(){
		Rectangle levelBackground = new Rectangle(768, 768);

		levelWidth = LevelData.LEVEL1[0].length() * pixelScale;

		for (int i = 0; i < LevelData.LEVEL1.length; i++){
			String line = LevelData.LEVEL1[i];
			for (int j = 0; j < line.length(); j++){
				switch (line.charAt(j)){
					case '0':
						testWall = new TestWall(rootLayout, j*pixelScale, i*pixelScale);
						testWall.addToLayer();
						testWall.updateUI();
						break;
					case '1':
						coinList.add(testCoin = new TestCoin(rootLayout, j*pixelScale + 15, i*pixelScale + 15));
						testCoin.addToLayer();
						testCoin.updateUI();
						break;
					case '2':
						break;
				}
			}
		}
		//rootLayout.getChildren().add(levelBackground);
	}
	private void initPlayer(){
		testman = new TestMan(rootLayout, 7 * pixelScale, 7 * pixelScale + 10, true, 50, 50, playerSpeed);
		charList.add(testman);
		testman.addToLayer();
		testman.updateUI();
	}
	private void initRobber(){
		testRobber = new TestRobber(rootLayout, 14 * pixelScale, 14 * pixelScale, false, 35, 35, robberSpeed);
		charList.add(testRobber);
		testRobber.addToLayer();
		testRobber.updateUI();
	}

	private void robberMovement(){
		Random rand = new Random();
		int countDirection = rand.nextInt(4) + 0;
		int countTimer = rand.nextInt(17) + 0;
		if (countTimer == 16 && countDirection == 0){
			testRobber.setDx(0);
			testRobber.setDy(-playerSpeed);
		}else if (countTimer == 12 && countDirection == 0){
			testRobber.setDx(playerSpeed);
			testRobber.setDy(0);
		}else if (countTimer == 8 && countDirection == 0){
			testRobber.setDx(0);
			testRobber.setDy(playerSpeed);
		}else if (countTimer == 4 && countDirection == 0){
			testRobber.setDx(-playerSpeed);
			testRobber.setDy(0);
		}
		testRobber.updateUI();
		System.out.println("Direction: " + countDirection);
		System.out.println("Timer: " + countTimer);
	}
	
	// Get the controller up and running
	public void initGameController(Stage mainStage) {
		mainStage.setTitle("Test Character Movement");
		try {
            // Load root layout from FXML file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TestMap.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainStage.setScene(scene);
            mainStage.show();
          
            //Hard coding rectangular map

//			rect1 = new Rectangle(0, 0, 32, 768);
//			rect1.setStroke(Color.BLACK);
//			rect2 = new Rectangle(0, 0, 768, 32);
//			rect2.setStroke(Color.BLACK);
//			rect3 = new Rectangle(0, 736, 768, 32);
//			rect3.setStroke(Color.BLACK);
//			rect4 = new Rectangle(736, 0, 32, 768);
//			rect4.setStroke(Color.BLACK);


			//border
			mapPath.add(new Rectangle(0, 0, pixelScale - 8, 16 * pixelScale));
			mapPath.add(new Rectangle(0, 0, 16 * pixelScale, pixelScale - 8));
			mapPath.add(new Rectangle(0, 15 * pixelScale + 8, 16 * pixelScale, pixelScale - 8));
			mapPath.add(new Rectangle(15 * pixelScale + 8, 0, pixelScale - 8, 16 * pixelScale));
			//top row
			mapPath.add(new Rectangle(2 * pixelScale + 8, 2 * pixelScale + 8, 3 * pixelScale - 8, pixelScale - 8));
			mapPath.add(new Rectangle(6 * pixelScale + 8, 2 * pixelScale + 8, 4 * pixelScale - 8, pixelScale - 8));
			mapPath.add(new Rectangle(11 * pixelScale + 8, 2 * pixelScale + 8, 3 * pixelScale - 8, pixelScale - 8));
			//rest of map
			mapPath.add(new Rectangle(2 * pixelScale + 8, 4 * pixelScale + 8, 4 * pixelScale - 8, 3 * pixelScale - 8));
			mapPath.add(new Rectangle(7 * pixelScale + 8, 4 * pixelScale + 8, 2 * pixelScale - 8, 3 * pixelScale - 8));
			mapPath.add(new Rectangle(10 * pixelScale + 8, 4 * pixelScale + 8, 4 * pixelScale - 8, 3 * pixelScale - 8));

			mapPath.add(new Rectangle(2 * pixelScale + 8, 8 * pixelScale + 8, 3 * pixelScale - 8, pixelScale - 8));
			mapPath.add(new Rectangle(6 * pixelScale + 8, 8 * pixelScale + 8, pixelScale - 8, 2 * pixelScale - 8));
			mapPath.add(new Rectangle(9 * pixelScale + 8, 8 * pixelScale + 8, pixelScale - 8, 2 * pixelScale - 8));
			mapPath.add(new Rectangle(11 * pixelScale + 8, 8 * pixelScale + 8, 3 * pixelScale - 8, pixelScale - 8));

			mapPath.add(new Rectangle(4 * pixelScale + 8, 9 * pixelScale - 8, pixelScale - 8, 2 * pixelScale));
			mapPath.add(new Rectangle(6 * pixelScale + 8, 10 * pixelScale - 8, 4 * pixelScale - 8, pixelScale));
			mapPath.add(new Rectangle(11 * pixelScale + 8, 9 * pixelScale - 8, pixelScale - 8, 2 * pixelScale));

			mapPath.add(new Rectangle(2 * pixelScale + 8, 10 * pixelScale + 8, pixelScale - 8, 4 * pixelScale - 8));
			mapPath.add(new Rectangle(4 * pixelScale + 8, 12 * pixelScale + 8, 8 * pixelScale - 8, 2 * pixelScale - 8));
			mapPath.add(new Rectangle(13 * pixelScale + 8, 10 * pixelScale + 8, pixelScale - 8, 4 * pixelScale - 8));


//			mapPath.add(new Rectangle(288, 384, 48, 144));
//			mapPath.add(new Rectangle(432, 384, 48, 144));
//			mapPath.add(new Rectangle(528, 384, 144, 48));

			//rootLayout.getChildren().addAll(rect1, rect2, rect3, rect4);
			rootLayout.getChildren().addAll(mapPath);


			//setting coins
//			coinPosX = pixelScale + 8;
//			coinPosY = pixelScale + 8;
//			for (int i = 0; i < 16; i++){
//				pixelScale = pixelScale + pixelScale + 8;
//				for (int j; j < 16; j++){
//					testCoin = new TestCoin(rootLayout, pixelScale + 8, pixelScale + 8);
//					testCoin.addToLayer();
//					testCoin.updateUI();
//					coinPosY = coinPosY + pixelScale + 8;
//				}
//			}
			//testCoin = new TestCoin(rootLayout, pixelScale + 8, pixelScale + 8);
			//testCoin.addToLayer();
			//testCoin.updateUI();
            //mapPath.add(new Rectangle(0, 0, 32, 768));
//            testman = new TestMan(rootLayout, 7 * pixelScale, 7 * pixelScale + 10, true, 35, 35);
//            testman.addToLayer();
//            testman.updateUI();
			//rootLayout.getChildren().addAll(rect1, rect2, rect3, rect4);

          //Initialise ArrayList to store currently pressed keys
            ArrayList<String> input = new ArrayList<String>();

          //Initialise EventHandler to listen for key presses, add them to input ArrayList, and when they are released remove them
    		scene.setOnKeyPressed(
    				new EventHandler<KeyEvent>()
    				{	
    					public void handle(KeyEvent e) {
    						String code = e.getCode().toString();
    						if(!input.contains(code)) {
    							input.add(code);    							
	    							if(input.contains("UP")) {
	    								System.out.println("Move Up");
	    								testman.setUP(true);
	    							}
	    							else {
	    								testman.setUP(false);
	    							}
	    							if(input.contains("RIGHT")) {
	    								System.out.println("Move Right");
	    								testman.setRIGHT(true);
										
	    							}
	    							else {
	    								testman.setRIGHT(false);
	    							}
	    							if(input.contains("DOWN")) {
	    								testman.setDOWN(true);
	    								System.out.println("Move Down");
										
	    							}
	    							else {
	    								testman.setDOWN(false);
	    							}
	    							if(input.contains("LEFT")){
	    								System.out.println("Move Left");
	    								testman.setLEFT(true);
	    							}
    							}
    						}
    					});
    		scene.setOnKeyReleased(
    				new EventHandler<KeyEvent>()
    				{
    					public void handle(KeyEvent e)
    					{
    						String code = e.getCode().toString();
    						if(code == "LEFT") {
    							testman.setLEFT(false);
    						}
    						else if(code == "RIGHT") {
    							testman.setRIGHT(false);
    						}
    						else if(code == "UP") {
    							testman.setUP(false);
    						}
							else if(code == "DOWN") {
								testman.setDOWN(false);
							}
    						input.remove(code);
    						System.out.println("Key Released");
    					}
    				});
    		
    		mainStage.show();
    		
    		new AnimationTimer() {

				public void handle(long currentNanoTime) {
					// TODO Put graphics drawing classes, methods what-have-you in here.
					testman.updateUI();
				}
    			
    		}.start();
    		
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	public void tickChange(){
		detector.scanCollisions(charList, mapPath, coinList);
		
		testman.changeMove();
		testRobber.changeMove();
		robberMovement();
	}
}