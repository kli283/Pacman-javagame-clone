package controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {
	public static Stage gameStage;
	private AnchorPane baseLayout;
	public static final double screenWidth = 1024;
	public static final double screenHeight = 768;
	public static final double gameScreenWidth = 1024;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage gameStage) throws IOException {
		//GameController gControl = new GameController(primaryStage);
		MainApp.gameStage = gameStage;
		MainApp.gameStage.setTitle("Gold Girl");
		MainApp.gameStage.setResizable(false);
		MainApp.gameStage.sizeToScene();

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("MainMenu.fxml"));
			baseLayout = (AnchorPane) loader.load();

			Scene scene = new Scene(baseLayout);
			gameStage.setScene(scene);
			gameStage.show();


		} catch (IOException e) {
			e.printStackTrace();
			e.getCause();
		}
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				if (MenuControl.gControl != null) {
					MenuControl.gControl.tickChange();
				}
			}
		};
		timer.start();
	}


}