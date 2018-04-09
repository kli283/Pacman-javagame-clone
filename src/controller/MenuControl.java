package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuControl {
    //private static GameModes GameMode;
    private AnchorPane test;
    private Scene theScene;

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
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == playButton) {
            MainApp.gameStage = (Stage) playButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("PlayMenu.fxml"));
            theScene = new Scene(test);
            MainApp.gameStage.setScene(theScene);
            System.out.print("PlaySelected" + "\n");
        }
        else if (event.getSource() == statsButton) {
            MainApp.gameStage = (Stage) statsButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("StatsMenu.fxml"));
            theScene = new Scene(test);
            MainApp.gameStage.setScene(theScene);
            System.out.print("StatsSelected" + "\n");
        }
        else if (event.getSource() == settingsButton) {
            MainApp.gameStage = (Stage) settingsButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("SettingsMenu.fxml"));
            theScene = new Scene(test);
            MainApp.gameStage.setScene(theScene);
            System.out.print("SettingsSelected" + "\n");
        }
        else if (event.getSource() == SPButton) {
            MainApp.gameStage = (Stage) SPButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("SinglePlayerMenu.fxml"));
            theScene = new Scene(test);
            MainApp.gameStage.setScene(theScene);
            System.out.print("SPSelected" + "\n");
        }
        else if (event.getSource() == backPlayButton) {
            MainApp.gameStage = (Stage) backPlayButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("PlayMenu.fxml"));
            theScene = new Scene(test);
            MainApp.gameStage.setScene(theScene);
            System.out.print("BackPlayButtonSelected" + "\n");
        }
        else if (event.getSource() == MPButton) {
            MainApp.gameStage = (Stage) MPButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("MultiPlayerMenu.fxml"));
            theScene = new Scene(test);
            MainApp.gameStage.setScene(theScene);
            System.out.print("MPSelected" + "\n");
        }
        else if (event.getSource() == backButton) {
            MainApp.gameStage = (Stage) backButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            theScene = new Scene(test);
            MainApp.gameStage.setScene(theScene);
            System.out.print("BackSelected" + "\n");
        }
        else if (event.getSource() == quitButton) {
            MainApp.gameStage.close();
            System.out.print("Exited" + "\n");
        }
    }

}
