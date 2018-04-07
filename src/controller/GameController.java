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
import model.TestMan;

public class GameController { // Class to contain main game loop
	
	private AnchorPane rootLayout;

	
	public GameController(Stage mainStage) {
		
		initGameController(mainStage);
		
	}
	
	// Get the controller up and running
	public void initGameController(Stage mainStage) {
		mainStage.setTitle("Test Drive");
		
		try {
            // Load root layout from FXML file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TestMap.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainStage.setScene(scene);
            mainStage.show();
            
            TestMan testman = new TestMan(rootLayout, 300, 300, true, 50, 50);
            testman.addToLayer();
            testman.updateUI();
            
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
    							if(input.contains("UP")) {
    								System.out.println("Move Up");
    								testman.move(0, -10);
    								input.remove(code);
    							}
    							while(input.contains("RIGHT")) {
    								System.out.println("Move Right");
    								testman.move(10, 0);
    								input.remove(code);
    							}
    							if(input.contains("DOWN")) {
    								System.out.println("Move Down");
    								testman.move(0, 10);
    								input.remove(code);
    							}
    							if(input.contains("LEFT")) {
    								System.out.println("Move Left");
    								testman.move(-10, 0);
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
					// TODO Put graphics drawing classes, methods what-have-you in here
					testman.updateUI();
				}
    			
    		}.start();
    		
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}
