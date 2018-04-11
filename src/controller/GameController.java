package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Barrier;
import model.TestMan;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.fxml.FXML;

public class GameController { // Class to contain main game loop
	
	private AnchorPane rootLayout;
	TestMan testman;
	private double charSpeed = 3;
	private ArrayList<Rectangle> mapPath = new ArrayList<>();
    CollisionDetection detector = new CollisionDetection();
    Barrier wall1;
	
	public GameController(Stage mainStage) {
		
		initGameController(mainStage);
	
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
			Rectangle rect1 = new Rectangle(0, 0, 32, 768);
			rect1.setStroke(Color.BLACK);
			Rectangle rect2 = new Rectangle(0, 0, 768, 32);
			rect2.setStroke(Color.BLACK);
			Rectangle rect3 = new Rectangle(0, 736, 768, 32);
			rect3.setStroke(Color.BLACK);
			Rectangle rect4 = new Rectangle(736, 0, 32, 768);
			rect4.setStroke(Color.BLACK);

            //mapPath.add(new Rectangle(0, 0, 32, 768));
            testman = new TestMan(rootLayout, 300, 300, true, 50, 50);
            testman.addToLayer();
            testman.updateUI();
			rootLayout.getChildren().addAll(rect1, rect2, rect3, rect4);
			wall1 = new Barrier(50, 10, 200, 200, rootLayout);
			wall1.addToLayer();
          //Initialise ArrayList to store currently pressed keys
            ArrayList<String> input = new ArrayList<String>();

          //Initialise EventHandler to listen for key presses, add them to input ArrayList, and when they are released remove them
    		scene.setOnKeyPressed(
    				new EventHandler<KeyEvent>()
    				{	
    					public void handle(KeyEvent e)
    					{
    						String code = e.getCode().toString();
    						if(!input.contains(code)) {
    							input.add(code);
    							//if(!detector.willCollide(testman, wall1)){    							
	    							if(input.contains("UP")) {
	    								System.out.println("Move Up");
	    								testman.setDx(0);
	    								testman.setDy(-charSpeed);
	    								input.remove(code);
	    							}
	    							if(input.contains("RIGHT")) {
	    								System.out.println("Move Right");
										testman.setDx(charSpeed);
										testman.setDy(0);
	    								input.remove(code);
	    							}
	    							if(input.contains("DOWN")) {
	    								System.out.println("Move Down");
										testman.setDx(0);
										testman.setDy(charSpeed);
	    								input.remove(code);
	    							}
	    							if(input.contains("LEFT")) {
	    								System.out.println("Move Left");
										testman.setDx(-charSpeed);
										testman.setDy(0);
	    								input.remove(code);
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
    						input.remove(code);
    						System.out.println("Key Released");
    					}
    				});
    		
    		mainStage.show();
    		
    		new AnimationTimer() {

				public void handle(long currentNanoTime) {
					// TODO Put graphics drawing classes, methods what-have-you in here.
					// 		Move this into an appropriate view class.
					testman.updateUI();
					wall1.updateUI();
				}
    			
    		}.start();
    		
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	public void tickChange(){
		//testman.setXPos(testman.getXPos() + 1);
		if(!detector.willCollide(testman, wall1)) {
			testman.changeMove();
		}
		else if(detector.willCollide(testman, wall1)) {
			if(testman.getDx() > 0) {
				testman.setXPos(testman.getXPos() - 5);
				testman.setDx(0);
				testman.setDy(0);
			}
			else if(testman.getDx() < 0) {
				testman.setXPos(testman.getXPos() + 5);
				testman.setDx(0);
				testman.setDy(0);
			}
			else if(testman.getDy() > 0) {
				testman.setYPos(testman.getYPos() - 5);
				testman.setDx(0);
				testman.setDy(0);
			}
			else if(testman.getDy() < 0) {
				testman.setYPos(testman.getYPos() + 5);
				testman.setDx(0);
				testman.setDy(0);
			}
		}
	}
}