package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.TestMan;
import view.UserInput;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Test Drive");
		
		// Not sure if required
		Canvas canvas = new Canvas();
		
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TestMap.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            TestMan testman = new TestMan(rootLayout, 300, 300, true, 50, 50);
            testman.addToLayer();
            testman.updateUI();
            
          //Initialise ArrayList to store currently pressed keys
            ArrayList<String> input = new ArrayList<String>();
            //GraphicsContext gc = canvas.getGraphicsContext2D();
          //Initialise   EventHandler to listen for key presses, add them to input ArrayList, and when they are released remove them
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
    		
    		//Not sure if required
    		
    		primaryStage.show();
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
	
	public static void main(String[] args) {
		launch(args);
	}
}