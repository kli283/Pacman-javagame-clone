package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.GameModes;
import controller.SoundEffects;


import java.io.IOException;

public class MenuControl {
    private AnchorPane test;
    private Scene testScene;
    public static GameModes mode;
    private Group gameRoot;
    // private Images gameImages;
    public static GameController gControl;
    private int positionScale = 48;


    @FXML
    private Button playButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button statsButton;
    @FXML
    private Button quitButton;
    @FXML
    private Button backButton;
    @FXML
    private Button SPButton;
    @FXML
    private Button MPButton;
    @FXML
    private Button backPlayButton;
    @FXML
    private Button storyButton;
    @FXML
    private Button mapSelectButton;
    @FXML
    private Button oneButton;
    @FXML
    private Button twoButton;
    @FXML
    private Button map1Button;
    @FXML
    private Button map2Button;
    @FXML
    private Button map3Button;
    @FXML
    private Button map4Button;


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == playButton) {
            MainApp.gameStage = (Stage) playButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("PlayMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("PlaySelected" + "\n");
        }
        else if (event.getSource() == statsButton) {
            MainApp.gameStage = (Stage) statsButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("StatsMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("StatsSelected" + "\n");
        }
        else if (event.getSource() == settingsButton) {
            MainApp.gameStage = (Stage) settingsButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("SettingsMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("SettingsSelected" + "\n");
        }
        else if (event.getSource() == SPButton) {
            MenuControl.mode = GameModes.SinglePlayer;
            MainApp.gameStage = (Stage) SPButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("SinglePlayerMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("SPSelected" + "\n");
        }
        else if (event.getSource() == mapSelectButton) {
            MainApp.gameStage = (Stage) mapSelectButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("MapSelect.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("MapSelectSelected" + "\n");
        }
        else if (event.getSource() == map1Button) {
            gControl = new GameController(MainApp.gameStage);
            gControl.initMap(LevelData.LEVEL1, "Brick");
            gControl.initLabels();
            //gControl.initPlayer(7 * positionScale, 7 * positionScale + 8);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
        }
        else if (event.getSource() == map2Button) {
            gControl = new GameController(MainApp.gameStage);
            gControl.initMap(LevelData.LEVEL2, "BrickGrey");
            gControl.initLabels();
           // gControl.initPlayer(7*positionScale + 10, 6*positionScale);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
        }
        else if (event.getSource() == map3Button) {
            gControl = new GameController(MainApp.gameStage);
            gControl.initMap(LevelData.LEVEL3, "Dirt");
            gControl.initLabels();
            //gControl.initPlayer(7*positionScale, 7*positionScale + 10);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
            System.out.print("Gold Mine Selected" + "\n");
        }

        else if (event.getSource() == storyButton) {
            gControl = new GameController(MainApp.gameStage);
            gControl.initMap(LevelData.LEVEL1, "Brick");
            gControl.initLabels();
            //MainApp.muteMusic();
            //gControl.initPlayer(7*positionScale, 7*positionScale + 10);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
        }
        else if (event.getSource() == backPlayButton) {
            MainApp.gameStage = (Stage) backPlayButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("PlayMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("BackPlayButtonSelected" + "\n");
        }
        else if (event.getSource() == MPButton) {
            MainApp.gameStage = (Stage) MPButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("MultiPlayerMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("MPSelected" + "\n");
        }
        else if (event.getSource() == oneButton) {
            MenuControl.mode = GameModes.MultiPlayer1;
            gControl = new GameController(MainApp.gameStage);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
        }
        else if (event.getSource() == twoButton) {
            gControl = new GameController(MainApp.gameStage);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
        }
        else if (event.getSource() == backButton) {
            MainApp.gameStage = (Stage) backButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("BackSelected" + "\n");
        }
        else if (event.getSource() == storyButton) {
            gControl = new GameController(MainApp.gameStage);
            //test = FXMLLoader.load(getClass().getResource("TestMap.fxml"));
            //theScene = new Scene(test);
            //MainApp.gameStage.setScene(theScene);
            System.out.println("Start test level");
        }
        else if (event.getSource() == quitButton) {
            MainApp.gameStage.close();
            System.out.print("Exited" + "\n");
        }
    }

}
