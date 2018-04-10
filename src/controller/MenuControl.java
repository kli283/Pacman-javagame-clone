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


import java.io.IOException;

public class MenuControl {
    private AnchorPane test;
    private Scene testScene;
    private static GameModes mode;
    private Group gameRoot;
    private Images gameImages;
    public static GameController gControl;


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
            MainApp.gameStage = (Stage) SPButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("SinglePlayerMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("SPSelected" + "\n");
        }
        else if (event.getSource() == storyButton) {
            gameRoot = new Group();
            testScene = new Scene(gameRoot, MainApp.gameScreenWidth, MainApp.screenHeight);
            MainApp.gameStage.setScene(testScene);
            MainApp.gameStage.setResizable(false);
            MainApp.gameStage.sizeToScene();
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
        }
        else if (event.getSource() == mapSelectButton) {
            gControl = new GameController(MainApp.gameStage);
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
        else if (event.getSource() == backButton) {
            MainApp.gameStage = (Stage) backButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("BackSelected" + "\n");
        }
        else if (event.getSource() == quitButton) {
            MainApp.gameStage.close();
            System.out.print("Exited" + "\n");
        }
    }

}
