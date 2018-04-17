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
    private static AnchorPane test;
    private static Scene testScene;
    public static GameModes mode;
    private Group gameRoot;
    // private Images gameImages;
    public static GameController gControl;
    private int positionScale = 48;
    private static boolean storyMode = false;
    private static int levelCounter = 0;

    @FXML
    private Button playButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button creditsButton;
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
    private Button mOnButton;
    @FXML
    private Button mOffButton;;
    @FXML
    private Button sOnButton;
    @FXML
    private Button sOffButton;;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == playButton) {
            MainApp.gameStage = (Stage) playButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("PlayMenu.fxml"));
            testScene = new Scene(test);
            CollisionDetection.scoreUpdate = new Score(0);
            MainApp.gameStage.setScene(testScene);
            System.out.print("PlaySelected" + "\n");
        } else if (event.getSource() == creditsButton) {
            MainApp.gameStage = (Stage) creditsButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("CreditsMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("StatsSelected" + "\n");
        } else if (event.getSource() == settingsButton) {
            MainApp.gameStage = (Stage) settingsButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("SettingsMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("SettingsSelected" + "\n");
        } else if (event.getSource() == SPButton) {
            MenuControl.mode = GameModes.SinglePlayer;
            MainApp.gameStage = (Stage) SPButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("SinglePlayerMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("SPSelected" + "\n");
        } else if (event.getSource() == mapSelectButton) {
            MainApp.gameStage = (Stage) mapSelectButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("MapSelect.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("MapSelectSelected" + "\n");
        } else if (event.getSource() == map1Button) {
            gControl = new GameController(MainApp.gameStage, this.mode, this);
            gControl.initMap(LevelData.LEVEL1, "Brick", this.mode);
            gControl.initLabels();
            //gControl.initPlayer(7 * positionScale, 7 * positionScale + 8);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
        } else if (event.getSource() == map2Button) {
            gControl = new GameController(MainApp.gameStage, this.mode, this);
            gControl.initMap(LevelData.LEVEL2, "BrickGrey", this.mode);
            gControl.initLabels();
            // gControl.initPlayer(7*positionScale + 10, 6*positionScale);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
        } else if (event.getSource() == map3Button) {
            gControl = new GameController(MainApp.gameStage, this.mode, this);
            gControl.initMap(LevelData.LEVEL3, "Dirt", this.mode);
            gControl.initLabels();
            //gControl.initPlayer(7*positionScale, 7*positionScale + 10);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
            System.out.print("Gold Mine Selected" + "\n");
        } else if (event.getSource() == map4Button) {
            gControl = new GameController(MainApp.gameStage, this.mode, this);
            gControl.initMap(LevelData.LEVEL4, "bitcoin", this.mode);
            gControl.initLabels();
            //gControl.initPlayer(7*positionScale, 7*positionScale + 10);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
            System.out.print("Gold Mine Selected" + "\n");
        } else if (event.getSource() == storyButton) {
            this.storyMode = true;
            this.levelCounter = 1;
            gControl = new GameController(MainApp.gameStage, this.mode, this);
            gControl.initMap(LevelData.LEVEL1, "Brick", this.mode);
            gControl.initLabels();
            //MainApp.muteMusic();
            //gControl.initPlayer(7*positionScale, 7*positionScale + 10);
            //gameImages = new Images(gameRoot);
            //gameRoot.getChildren().add(gameImages.getBackground());
        } else if (event.getSource() == backPlayButton) {
            MainApp.gameStage = (Stage) backPlayButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("PlayMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("BackPlayButtonSelected" + "\n");
        } else if (event.getSource() == MPButton) {
            MainApp.gameStage = (Stage) MPButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("MultiPlayerMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("MPSelected" + "\n");
        } else if (event.getSource() == oneButton) {
            MenuControl.mode = GameModes.MultiPlayer1;
            gControl = new GameController(MainApp.gameStage, this.mode, this);
            gControl.initMap(LevelData.LEVEL4, "bitcoin", this.mode);
            gControl.initLabels();
        } else if (event.getSource() == twoButton) {
            MenuControl.mode = GameModes.MultiPlayer2;
            gControl = new GameController(MainApp.gameStage, this.mode, this);
            gControl.initMap(LevelData.LEVEL4, "bitcoin", this.mode);
            gControl.initLabels();
        } else if (event.getSource() == backButton) {
            MainApp.gameStage = (Stage) backButton.getScene().getWindow();
            test = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            testScene = new Scene(test);
            MainApp.gameStage.setScene(testScene);
            System.out.print("BackSelected" + "\n");
        } else if (event.getSource() == quitButton) {
            MainApp.gameStage.close();
            System.out.print("Exited" + "\n");
        } else if (event.getSource() == mOnButton) {
            MainApp.playMusic();
        } else if (event.getSource() == mOffButton) {
            MainApp.stopMusic();
        } else if (event.getSource() == sOnButton) {
            SoundEffects.enableSounds = true;
        } else if (event.getSource() == sOffButton) {
            SoundEffects.enableSounds = false;
        }

    }

    public static void resetStoryMode() {
        MenuControl.storyMode = false;
    }

    public static int getLevelCount() {
        return MenuControl.levelCounter;
    }

    public static void setLevelCount() {
        if (MenuControl.levelCounter != 0) {
            levelCounter++;
            if (MenuControl.levelCounter > 3) {
                levelCounter = 0;
            }
        }
    }

    public static void launchLevel2() {
        try {
            gControl = new GameController(MainApp.gameStage, MenuControl.mode, MenuControl.gControl.getMenu());
        } catch (IOException e) {
            e.printStackTrace();
        }
        gControl.initMap(LevelData.LEVEL2, "BrickGrey", MenuControl.mode);
        gControl.initLabels();
    }

    public static void launchLevel3() {
        try {
            gControl = new GameController(MainApp.gameStage, MenuControl.mode, MenuControl.gControl.getMenu());
        } catch (IOException e) {
            e.printStackTrace();
        }
        gControl.initMap(LevelData.LEVEL3, "Dirt", MenuControl.mode);
        gControl.initLabels();
    }

    public static void launchLevel4() {
        try {
            gControl = new GameController(MainApp.gameStage, MenuControl.mode, MenuControl.gControl.getMenu());
        } catch (IOException e) {
            e.printStackTrace();
        }
        gControl.initMap(LevelData.LEVEL4, "bitcoin", MenuControl.mode);
        gControl.initLabels();
    }


//    public static void returnToMenu(MenuControl menuHub){
//    	try {
//            FXMLLoader reload = new FXMLLoader();
//            reload.setLocation(MainApp.class.getResource("MainMenu.fxml"));
//            MenuControl.test = (AnchorPane) reload.load();
//            Scene menuScene = new Scene(MenuControl.test);
//            MainApp.gameStage.setScene(menuScene);
//            //mainStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            e.getCause();
//        }
//    	
//    	
//    }
}
