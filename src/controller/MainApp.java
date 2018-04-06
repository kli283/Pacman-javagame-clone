package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import view.UserInput;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Test Drive");
		
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TestMap.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
          //Initialise user input to listen for key presses
            ArrayList<String> input = new ArrayList<String>();
            
    		scene.setOnKeyPressed(
    				new EventHandler<KeyEvent>()
    				{	
    					public void handle(KeyEvent e)
    					{
    						String code = e.getCode().toString();
    						if(!input.contains(code)) {
    							input.add(code);
    							System.out.println("Key Pressed");
    							if(input.contains("UP")) {
    								System.out.println("Up Pressed");
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
        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
