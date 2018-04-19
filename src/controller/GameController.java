package controller;

/* GameController.java
 * 
 * This class is the backbone of the actual game when it is running
 * 
 * This is the main controller that initialises everything and runs
 * everything during gameplay
 * 
 * Author: Kenny Li, James Flood
 * 
 * 2018
 * 
 */

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.*;

import java.util.Random;

import javafx.scene.shape.Rectangle;
import view.*;
import model.Character;

public class GameController { // Class to contain main game loop

    private AnchorPane rootLayout;
    private GoldGirl GoldGirl;
    private Robber1 robber2;
    private Robber1 robber3;
    private AIController AI;
    private GameUI UI;
    private ArrayList<Rectangle> mapPath = new ArrayList<>();
    private ArrayList<Character> charList = new ArrayList<Character>();
    private ArrayList<Item> coinList = new ArrayList<Item>();
    private ArrayList<Item> smallCashList = new ArrayList<>();
    private ArrayList<Item> bigCashList = new ArrayList<>();
    private ArrayList<Item> goldList = new ArrayList<>();
    private ArrayList<Item> cryptoList = new ArrayList<>();
    private ArrayList<Box> wallList = new ArrayList<>();
    private ArrayList<Item> carList = new ArrayList<>();
    private ArrayList<Item> flashbangList = new ArrayList<>();
    private GameModes gameModes;
    public static SoundEffects soundEffects;
    private Label scoreLabel;
    private Label timeLabel;
    private Label pregameLabel;
    private Label escLabel;
    private Label pauseLabel;
    private Label winLabel;
    private Label carTimer;
    private Label stunTimer;
    private boolean foundStunned = false;
    private Rectangle winBox;
    private static final int STARTTIME = 120;
    private Label timerLabel = new Label();
    private int timeSeconds = STARTTIME;
    private int timeRemaining;
    private int preTimeRemaining = 3;
    private int numOfTimesTicked;
    private int preGameTimer;
    private boolean escPressed;
    private boolean pausePressed;
    private boolean endGamePressed;
    private boolean gameOver;
    private boolean readyToStart;
    private boolean winGame;
    private CollisionDetection detector = new CollisionDetection();

    //This object is the main controller where the gameloop is contained
    public GameController(Stage mainStage, GameModes gameModes) throws IOException {
        timeRemaining = 0;
        numOfTimesTicked = 0;
        escPressed = false;
        pausePressed = false;
        endGamePressed = false;
        winGame = false;

        resetTimer();
        initGameController(mainStage, gameModes);
        AI = new AIController();
        soundEffects = new SoundEffects();
    }

    private boolean startGame() {
        return this.readyToStart;
    }
    
    //A method to end the game and display a message depending on the win/lose condition and the player's score
    public void endGame(boolean agentEndGame) {
        this.timeRemaining = 0;
        this.gameOver = true;

        winBox.setFill(Color.BEIGE);
        if (winGame) {
            winLabel.setText("GAME OVER \nScore: $" + (String.format("%.2f", CollisionDetection.scoreUpdate.getScoreCount())) + "\npress enter to exit");
            winLabel.toFront();
        } 
        else if(agentEndGame) {
            scoreLabel.setText("");
        	winLabel.setText("GAME OVER \n(The Agent got ya...) \nScore: $" + (String.format("%.2f", CollisionDetection.scoreUpdate.getScoreCount())) + "\npress enter to exit");
            winLabel.toFront();
        }
        else {
            winLabel.setText("GAME OVER \nScore: $" + (String.format("%.2f", CollisionDetection.scoreUpdate.getScoreCount())) + "\npress enter to exit");
            winLabel.toFront();
        }
    }

    private boolean gameIsPaused() {
        return (this.escPressed) || (this.pausePressed) || (this.gameOver) || (this.checkWin());
    }
    
    //Timer logic
    private void decreaseTime() {

        this.timeRemaining--;
    }

    private void decreasePreTime() {
        this.preTimeRemaining--;
    }

    private void resetTimer() {
        this.timeRemaining = 120;
    }

    private void zeroTimer() {
        this.timeRemaining = 1;
    }

    //timeRemaining returns the amount of time left in the game
    public int timeAmount() {
        return this.timeRemaining;
    }

    private int preTimeAmount() {
        return this.preTimeRemaining;
    }

    //This method generates the gameMap from map data contained in LevelData
    public void initMap(String[] Level, String wallType, GameModes gameModes) {
        Rectangle levelBackground = new Rectangle(768, 768);

        int pixelScale = 48;
        int levelWidth = Level[0].length() * pixelScale;
        String imageURL = "/view/Resources/" + wallType + ".png";

        for (int i = 0; i < Level.length; i++) {
            String line = Level[i];
            for (int j = 0; j < line.length(); j++) {
                Random randScore = new Random();
                switch (line.charAt(j)) {
                    case '0':
                        Wall wall = new Wall(rootLayout, j * pixelScale, i * pixelScale, pixelScale, pixelScale, imageURL);
                        wallList.add(wall);
                        mapPath.add(wall.getBoundary());
                        GameUI.spawn(wall);
                        break;
                    case 'p':
                        initPlayer(j * pixelScale, i * pixelScale);
                        break;
                    case 'r':
                        Robber1 robber1;
                        if (gameModes == GameModes.SinglePlayer) {
                            robber1 = initRobber(j * pixelScale, i * pixelScale, false);
                        } else if (gameModes == GameModes.MultiPlayer1) {
                            robber1 = initRobber(j * pixelScale, i * pixelScale, false);
                        } else if (gameModes == GameModes.MultiPlayer2) {
                            robber1 = initRobber(j * pixelScale, i * pixelScale, false);
                        }
                        break;
                    case 'm':
                        if (gameModes == GameModes.SinglePlayer) {
                            robber1 = initRobber(j * pixelScale, i * pixelScale, false);
                        } else if (gameModes == GameModes.MultiPlayer1) {
                            robber1 = initRobber(j * pixelScale, i * pixelScale, false);
                        } else if (gameModes == GameModes.MultiPlayer2) {
                            robber3 = initRobber(j * pixelScale, i * pixelScale, true);
                        }
                        break;
                    case 'n':
                        if (gameModes == GameModes.SinglePlayer) {
                            robber1 = initRobber(j * pixelScale, i * pixelScale, false);
                        } else if (gameModes == GameModes.MultiPlayer1) {
                            robber2 = initRobber(j * pixelScale, i * pixelScale, true);
                        } else if (gameModes == GameModes.MultiPlayer2) {
                            robber2 = initRobber(j * pixelScale, i * pixelScale, true);
                        }
                        break;
                    case 'a':
                        initAgent(j * pixelScale, i * pixelScale);
                        break;
                    case 'f':
                    	Flashbang flashbang;
                    	flashbangList.add(flashbang = new Flashbang(rootLayout, j * pixelScale + 4, i * pixelScale, 0));
                    	GameUI.spawn(flashbang);
                    	break;
                    case '1':
                        Coin coin;
                        coinList.add(coin = new Coin(rootLayout, j * pixelScale + 15, i * pixelScale + 15, 2));
                        GameUI.spawn(coin);
                        break;
                    case '2':
                        break;
                    case '3':
                        Car car;
                        carList.add(car = new Car(rootLayout, j * pixelScale + 5, i * pixelScale + 5, 0));
                        GameUI.spawn(car);
                        break;
                    case '4':
                        SmallCash smallCash;
                        smallCashList.add(smallCash = new SmallCash(rootLayout, j * pixelScale + 8, i * pixelScale + 10, 10));
                        GameUI.spawn(smallCash);
                        break;
                    case '5':
                        BigCash bigCash;
                        bigCashList.add(bigCash = new BigCash(rootLayout, j * pixelScale + 8, i * pixelScale + 8, 25));
                        GameUI.spawn(bigCash);
                        break;
                    case 'c':
                        Crypto crypto;
                        cryptoList.add(crypto = new Crypto(rootLayout, j * pixelScale + 8, i * pixelScale + 8, randScore.nextInt(1000 + 1 + 1000) - 1000));
                        GameUI.spawn(crypto);
                        break;
                    case 'g':
                        Gold gold;
                        goldList.add(gold = new Gold(rootLayout, j * pixelScale + 8, i * pixelScale + 8, 100));
                        GameUI.spawn(gold);
                        break;
                }
            }
        }
    }

    //Initialise the player object
    public void initPlayer(double xPosition, double yPosition) {
        double playerSpeed = 3;
        GoldGirl = new GoldGirl(rootLayout, xPosition + 2, yPosition + 2, true, 42, 42, playerSpeed, true);
        charList.add(GoldGirl);
        GameUI.spawn(GoldGirl);
    }

    //Initialise the robbers
    private Robber1 initRobber(double xPosition, double yPosition, boolean isPlayer) {
        double robberSpeed = 2;
        Robber1 robber = new Robber1(rootLayout, xPosition, yPosition, isPlayer, 40, 40, robberSpeed, false);
        charList.add(robber);
        GameUI.spawn(robber);
        return robber;
    }

    //Initialise agent
    private void initAgent(double xPosition, double yPosition) {
        double agentSpeed = 1;
        Agent agent = new Agent(rootLayout, xPosition, yPosition, false, 40, 40, agentSpeed, false);
        charList.add(agent);
        GameUI.spawn(agent);
    }

    //A method to navigate the AI
    private void robberMovement() {
        AI.navigate(charList, GoldGirl, detector, mapPath);
    }

    // Get the controller up and running
    private void initGameController(Stage mainStage, GameModes gameModes) throws IOException {
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("Map.fxml"));
        rootLayout = loader.load();

        // Show the scene containing the root layout.
        preGameTimer = 3;
        Scene scene = new Scene(rootLayout);
        mainStage.setScene(scene);
        mainStage.show();

        // Sets up controls for SinglePlayer and MultiPlayer
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP ) {
                GoldGirl.setUP(true);
                GoldGirl.rotateUP();
            } else {
                GoldGirl.setUP(false);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                GoldGirl.setRIGHT(true);
                GoldGirl.rotateRIGHT();
            } else {
                GoldGirl.setRIGHT(false);
            }
            if (event.getCode() == KeyCode.DOWN) {
                GoldGirl.setDOWN(true);
                GoldGirl.rotateDOWN();
            } else {
                GoldGirl.setDOWN(false);
            }
            if (event.getCode() == KeyCode.LEFT) {
                GoldGirl.setLEFT(true);
                GoldGirl.rotateLEFT();
            }
            if (gameModes == GameModes.MultiPlayer1 || gameModes == GameModes.MultiPlayer2) {
                if (event.getCode() == KeyCode.W) {
                    robber2.setUP(true);
                    robber2.rotateUP();
                } else {
                    robber2.setUP(false);
                }
                if (event.getCode() == KeyCode.D) {
                    robber2.setRIGHT(true);
                    robber2.rotateRIGHT();
                } else {
                    robber2.setRIGHT(false);
                }
                if (event.getCode() == KeyCode.S) {
                    robber2.setDOWN(true);
                    robber2.rotateDOWN();
                } else {
                    robber2.setDOWN(false);
                }
                if (event.getCode() == KeyCode.A) {
                    robber2.setLEFT(true);
                    robber2.rotateLEFT();
                }
            }
            if (gameModes == GameModes.MultiPlayer2) {
                if (event.getCode() == KeyCode.I) {
                    robber3.setUP(true);
                    robber3.rotateUP();
                } else {
                    robber3.setUP(false);
                }
                if (event.getCode() == KeyCode.L) {
                    robber3.setRIGHT(true);
                    robber3.rotateRIGHT();
                } else {
                    robber3.setRIGHT(false);
                }
                if (event.getCode() == KeyCode.K) {
                    robber3.setDOWN(true);
                    robber3.rotateDOWN();
                } else {
                    robber3.setDOWN(false);
                }
                if (event.getCode() == KeyCode.J) {
                    //System.out.println("Move LEFT");
                    robber3.setLEFT(true);
                    robber3.rotateLEFT();
                }
            }

            /** Sets up in game button presses **/
            if (event.getCode() == KeyCode.P) {
                pressPause();
                if(!pausePressed){
                    winBox.setFill(Color.TRANSPARENT);
                    pauseLabel.setText("");
                }
            }

            if (event.getCode() == KeyCode.ESCAPE) {
                if (escPressed) {
                } else if (!this.pausePressed) {
                    winBox.setFill(Color.BEIGE);
                    escLabel.toFront();
                    escLabel.setText("Press Enter to quit\nBackspace to go back");
                    this.escPressed = true;
                }
            }
            // Pressing Enter when the quit game prompt is on the screen will take the game back to the main menu of the game
            if (event.getCode() == KeyCode.ENTER) {
                if (this.escPressed || this.gameOver) {
                    try {
                        winBox.setFill(Color.TRANSPARENT);
                        FXMLLoader reload = new FXMLLoader();
                        reload.setLocation(MainApp.class.getResource("MainMenu.fxml"));
                        rootLayout = reload.load();
                        Scene menuScene = new Scene(rootLayout);
                        mainStage.setScene(menuScene);
                    } catch (IOException e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                }
            }


            // Pressing backspace when the quit game prompt is on the screen will resume the game
            if (event.getCode() == KeyCode.BACK_SPACE) {
                if (this.escPressed) {
                    winBox.setFill(Color.TRANSPARENT);
                    this.escPressed = false;
                    escLabel.setText("");
                }
            }
            if (event.getCode() == KeyCode.PAGE_DOWN) {
                if (endGamePressed) {
                } else {
                    endGamePressed = true;
                    zeroTimer();
                    endGame(false);
                }
            }
            if (event.getCode() == KeyCode.PAGE_UP) {
                coinList.clear();
                smallCashList.clear();
                bigCashList.clear();
                cryptoList.clear();
                goldList.clear();
                zeroTimer();
            }


        });
        //This sets up the listener for the key presses, based on if the game is single or multiplayer
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                GoldGirl.setLEFT(false);
            } else if (event.getCode() == KeyCode.UP) {
                GoldGirl.setUP(false);
            } else if (event.getCode() == KeyCode.DOWN) {
                GoldGirl.setDOWN(false);
            } else if (event.getCode() == KeyCode.RIGHT) {
                GoldGirl.setRIGHT(false);
            }
            if (gameModes == GameModes.MultiPlayer1 || gameModes == GameModes.MultiPlayer2) {
                if (event.getCode() == KeyCode.A) {
                    robber2.setLEFT(false);
                } else if (event.getCode() == KeyCode.W) {
                    robber2.setUP(false);
                } else if (event.getCode() == KeyCode.S) {
                    robber2.setDOWN(false);
                } else if (event.getCode() == KeyCode.D) {
                    robber2.setRIGHT(false);
                }
            }
            if (gameModes == GameModes.MultiPlayer2) {
                if (event.getCode() == KeyCode.J) {
                    robber3.setLEFT(false);
                } else if (event.getCode() == KeyCode.I) {
                    robber3.setUP(false);
                } else if (event.getCode() == KeyCode.K) {
                    robber3.setDOWN(false);
                } else if (event.getCode() == KeyCode.L) {
                    robber3.setRIGHT(false);
                }
            }
        });

    }

    //Initialise all of the labels used by the UI
    public void initLabels() {
        scoreLabel = new Label("$" + String.format("%.2f", CollisionDetection.scoreUpdate.getScoreCount()));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(new Font("Calibri", 32));
        scoreLabel.setLayoutX(815);
        scoreLabel.setLayoutY(180);
        timeLabel = new Label(Integer.toString(timeSeconds) + " seconds");
        timeLabel.setTextFill(Color.WHITE);
        timeLabel.setFont(new Font("Calibri", 32));
        timeLabel.setLayoutX(815);
        timeLabel.setLayoutY(280);
        pregameLabel = new Label(Integer.toString(preGameTimer));
        pregameLabel.setFont(new Font("Calibri", 95));
        pregameLabel.setLayoutX(350);
        pregameLabel.setLayoutY(330);
        escLabel = new Label("");
        escLabel.setFont(new Font("Calibri", 45));
        escLabel.setLayoutX(175);
        escLabel.setLayoutY(330);
        escLabel.setTextAlignment(TextAlignment.CENTER);
        pauseLabel = new Label("");
        pauseLabel.setFont(new Font("Calibri", 45));
        pauseLabel.setLayoutX(260);
        pauseLabel.setLayoutY(350);
        pauseLabel.setTextAlignment(TextAlignment.CENTER);
        winLabel = new Label("");
        winLabel.setFont(new Font("Calibri", 65));
        winLabel.setLayoutX(145);
        winLabel.setLayoutY(265);
        winLabel.setTextAlignment(TextAlignment.CENTER);
        winBox = new Rectangle();
        winBox.setX(0);
        winBox.setY(0);
        winBox.setWidth(768);
        winBox.setHeight(768);
        winBox.setFill(Color.TRANSPARENT);
        carTimer = new Label("");
        carTimer.setTextFill(Color.WHITE);
        carTimer.setFont(new Font("Calibri", 32));
        carTimer.setLayoutX(865);
        carTimer.setLayoutY(445);
        stunTimer = new Label("");
        stunTimer.setTextFill(Color.WHITE);
        stunTimer.setFont(new Font("Calibri", 32));
        stunTimer.setLayoutX(865);
        stunTimer.setLayoutY(540);
        rootLayout.getChildren().addAll(scoreLabel, timeLabel, pregameLabel, escLabel, winLabel, pauseLabel, winBox, carTimer, stunTimer);
    }

    private void pressPause() {
        winBox.setFill(Color.BEIGE);
        pauseLabel.toFront();
        pauseLabel.setText("Game Paused");
        this.pausePressed = !this.pausePressed;
    }

    //This method is used to update the game and animate all of the characters and items and calls the collision detection
    public void tickChange() {
        GameUI.updateItems(cryptoList);
        GameUI.updateBoxes(wallList);
        GameUI.updateItems(coinList);
        GameUI.updateItems(smallCashList);
        GameUI.updateItems(bigCashList);
        GameUI.updateItems(goldList);
        GameUI.updateItems(carList);
        GameUI.updateItems(flashbangList);
        GameUI.updateActors(charList);
        if (!gameIsPaused()) {
            if (startGame()) {
                detector.scanCollisions(charList, mapPath, coinList, smallCashList, bigCashList, carList, cryptoList, flashbangList, goldList);
                for (Character x : charList) {
                    x.changeMove();
                    if(x.isDriving()) {
                        double carFormatTimer = (this.timeAmount() + 10) - x.getCarTimer();
                    	this.carTimer.setText(String.format("%.0f", carFormatTimer));
                    }
                    else if(x.isGG()&&!x.isDriving()) {
                    	this.carTimer.setText("");
                    }
                    if(x.isStunned()) {
                    	this.foundStunned = true;
                        double stunFormatTimer = ((this.timeAmount() + 5) - (x.getLastAttackTime()));
                    	this.stunTimer.setText(String.format("%.0f", stunFormatTimer));
                    }
                    if(!this.foundStunned) {
                    	this.stunTimer.setText("");
                    }
                }
                this.foundStunned = false;
                robberMovement();
                scoreLabel.setText("$" + (String.format("%.2f", CollisionDetection.scoreUpdate.getScoreCount())));
                  
                numOfTimesTicked++;
                if (numOfTimesTicked == 60) {
                    decreaseTime();
                    if (timeAmount() == 0) {
                        endGame(false);
                    }
                    timeLabel.setText(timeAmount() + " seconds");
                    numOfTimesTicked = 0;
                }
            } else {
                numOfTimesTicked++;
                if (numOfTimesTicked == 60) {
                    numOfTimesTicked = 0;
                    decreasePreTime();
                    pregameLabel.setText(Integer.toString(preTimeAmount()));
                    this.preGameTimer--;
                }

                if (preGameTimer == 0) {
                    this.readyToStart = true;
                    pregameLabel.setText("");
                }
            }
        } else if (gameOver) {
            this.timeLabel.setText(this.timeAmount() + " seconds");
        }
        if (checkWin()) {
            endGame(false);
            winLabel.setText("Game Over! \nScore: $" + (String.format("%.2f", CollisionDetection.scoreUpdate.getScoreCount())) + "\npress enter to exit");
            if (MenuControl.getLevelCount() == 1) {
                MenuControl.launchLevel2();
                MenuControl.setLevelCount();
                gameIsPaused();
            } else if (MenuControl.getLevelCount() == 2) {
                MenuControl.launchLevel3();
                MenuControl.setLevelCount();
            }
        }
    }
    
    //Evaluate if the game has been won
    private boolean checkWin() {
        return coinList.isEmpty() && smallCashList.isEmpty() && bigCashList.isEmpty() && cryptoList.isEmpty() && goldList.isEmpty();
    }

}