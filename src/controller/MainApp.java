package controller;

import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {
	
//	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		
		@SuppressWarnings("unused")
		GameController gControl = new GameController(primaryStage);
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}