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
import model.TestMan;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.fxml.FXML;

public class GameController { // Class to contain main game loop
	
	private AnchorPane rootLayout;
	TestMan testman;
	private double charSpeed = 5;
	private ArrayList<Rectangle> mapPath = new ArrayList<>();
	private ArrayList<Rectangle2D> wallsList = new ArrayList<>();
	double pixelScale = 48;

//	Rectangle rect1;
//	Rectangle rect2;
//	Rectangle rect3;
//	Rectangle rect4;


    CollisionDetection detector = new CollisionDetection();
    Rectangle2D wall1;
    Rectangle2D wall2;
	

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

//			rect1 = new Rectangle(0, 0, 32, 768);
//			rect1.setStroke(Color.BLACK);
//			rect2 = new Rectangle(0, 0, 768, 32);
//			rect2.setStroke(Color.BLACK);
//			rect3 = new Rectangle(0, 736, 768, 32);
//			rect3.setStroke(Color.BLACK);
//			rect4 = new Rectangle(736, 0, 32, 768);
//			rect4.setStroke(Color.BLACK);


			//border
			mapPath.add(new Rectangle(0, 0, pixelScale, 16 * pixelScale));
			mapPath.add(new Rectangle(0, 0, 16 * pixelScale, pixelScale));
			mapPath.add(new Rectangle(0, 15 * pixelScale, 16 * pixelScale, pixelScale));
			mapPath.add(new Rectangle(15 * pixelScale, 0, pixelScale, 16 * pixelScale));
			//top row
			mapPath.add(new Rectangle(2 * pixelScale, 2 * pixelScale, 3 * pixelScale, pixelScale));
			mapPath.add(new Rectangle(6 * pixelScale, 2 * pixelScale, 4 * pixelScale, pixelScale));
			mapPath.add(new Rectangle(11 * pixelScale, 2 * pixelScale, 3 * pixelScale, pixelScale));
			//rest of map
			mapPath.add(new Rectangle(2 * pixelScale, 4 * pixelScale, 4 * pixelScale, 3 * pixelScale));
			mapPath.add(new Rectangle(7 * pixelScale, 4 * pixelScale, 2 * pixelScale, 3 * pixelScale));
			mapPath.add(new Rectangle(10 * pixelScale, 4 * pixelScale, 4 * pixelScale, 3 * pixelScale));

			mapPath.add(new Rectangle(2 * pixelScale, 8 * pixelScale, 3 * pixelScale, pixelScale));
			mapPath.add(new Rectangle(6 * pixelScale, 8 * pixelScale, pixelScale, 3 * pixelScale));
			mapPath.add(new Rectangle(9 * pixelScale, 8 * pixelScale, pixelScale, 3 * pixelScale));
			mapPath.add(new Rectangle(11 * pixelScale, 8 * pixelScale, 3 * pixelScale, pixelScale));

			mapPath.add(new Rectangle(4 * pixelScale, 9 * pixelScale, pixelScale, 2 * pixelScale));
			mapPath.add(new Rectangle(7 * pixelScale, 10 * pixelScale, 2 * pixelScale, pixelScale));
			mapPath.add(new Rectangle(11 * pixelScale, 9 * pixelScale, pixelScale, 2 * pixelScale));

			mapPath.add(new Rectangle(2 * pixelScale, 10 * pixelScale, pixelScale, 4 * pixelScale));
			mapPath.add(new Rectangle(4 * pixelScale, 12 * pixelScale, 8 * pixelScale, 2 * pixelScale));
			mapPath.add(new Rectangle(13 * pixelScale, 10 * pixelScale, pixelScale, 4 * pixelScale));


//			mapPath.add(new Rectangle(288, 384, 48, 144));
//			mapPath.add(new Rectangle(432, 384, 48, 144));
//			mapPath.add(new Rectangle(528, 384, 144, 48));

			//rootLayout.getChildren().addAll(rect1, rect2, rect3, rect4);
			rootLayout.getChildren().addAll(mapPath);

            //mapPath.add(new Rectangle(0, 0, 32, 768));
            testman = new TestMan(rootLayout, 300, 300, true, 46, 46);
            testman.addToLayer();
            testman.updateUI();
			//rootLayout.getChildren().addAll(rect1, rect2, rect3, rect4);
//			wall1 = new Rectangle2D(200, 200, 50, 10);
//			wall2 = new Rectangle2D(400, 500, 20, 20);
			wallsList.add(new Rectangle2D(0, 0, 48, 768));
			wallsList.add(new Rectangle2D(0, 0, 768, 48));
			wallsList.add(new Rectangle2D(0, 720, 768, 48));
			wallsList.add(new Rectangle2D(720, 0, 48, 768));
          //Initialise ArrayList to store currently pressed keys
            ArrayList<String> input = new ArrayList<String>();

          //Initialise EventHandler to listen for key presses, add them to input ArrayList, and when they are released remove them
    		scene.setOnKeyPressed(
    				new EventHandler<KeyEvent>()
    				{	
    					public void handle(KeyEvent e) {
    						//if (!Collision){
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
    						//}

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
					testman.updateUI();
				}
    			
    		}.start();
    		
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	public void tickChange(){
		//testman.setXPos(testman.getXPos() + 1);
		if(!detector.scanCollisions(testman, wallsList)) {
			testman.changeMove();
		}
		else if(detector.scanCollisions(testman, wallsList)) {
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