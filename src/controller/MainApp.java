package controller;

/*	This launches the app
 * 
 * Authors: James Flood
 * 
 * 2018
 * 
 */

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {
	public static Stage gameStage;
	private static MediaPlayer gameMusic;

	public static void main(String[] args) {
		launch(args);
	}

	private void initMusic(){
		Media theme = new Media((MainApp.class.getResource("/view/Resources/Cool Vibes.mp3").toString()));
		gameMusic = new MediaPlayer(theme);
		gameMusic.setCycleCount(MediaPlayer.INDEFINITE);;
	}

	public static void playMusic(){
		gameMusic.play();
	}

	public static void stopMusic() {
		gameMusic.pause();
	}



	public void start(Stage gameStage) {
		MainApp.gameStage = gameStage;
		MainApp.gameStage.setTitle("Gold Girl");
		MainApp.gameStage.setResizable(false);
		MainApp.gameStage.sizeToScene();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("MainMenu.fxml"));
			AnchorPane baseLayout = loader.load();

			Scene scene = new Scene(baseLayout);
			gameStage.setScene(scene);
			gameStage.show();
			initMusic();
			playMusic();


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