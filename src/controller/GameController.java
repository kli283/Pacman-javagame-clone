package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import view.*;
import model.Character;

public class GameController { // Class to contain main game loop

    private AnchorPane rootLayout;
    private TestMan testman;
    private Coin coin;
    private SmallCash smallCash;
    private BigCash bigCash;
    private Crypto crypto;
    private Car car;
    private Robber1 robber1;
    private Robber1 robber2;
    private Robber1 robber3;
    private AIController AI;
    private Agent agent;
    private GameUI UI;
    private double playerSpeed = 3;
    private double robberSpeed = 2;
    private double agentSpeed = 1;
    private ArrayList<Rectangle> mapPath = new ArrayList<>();
    private ArrayList<Character> charList = new ArrayList<Character>();
    private ArrayList<Item> coinList = new ArrayList<Item>();
    private ArrayList<Item> smallCashList = new ArrayList<>();
    private ArrayList<Item> bigCashList = new ArrayList<>();
    private ArrayList<Item> cryptoList = new ArrayList<>();
    private ArrayList<Box> wallList = new ArrayList<>();
    private ArrayList<Item> carList = new ArrayList<>();
    private int pixelScale = 48;
    double coinPosX;
    double coinPosY;
    private int levelWidth;
    private GameModes gameModes;
    public static SoundEffects soundEffects;
    private Label scoreLabel;
    private Label timeLabel;
    private Label pregameLabel;
    private Label escLabel;
    private Label winLabel;
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
    private boolean isPlayer;
    private Stage gameStage;
    private MenuControl menuHub;
//	Rectangle rect1;
//	Rectangle rect2;
//	Rectangle rect3;
//	Rectangle rect4;

    private CollisionDetection detector = new CollisionDetection();
    Rectangle wall1;
    Rectangle wall2;

    public GameController(Stage mainStage, GameModes gameModes, MenuControl menuHub) throws IOException {
    	this.menuHub = menuHub;
        timeRemaining = 0;
        numOfTimesTicked = 0;
        escPressed = false;
        pausePressed = false;
        endGamePressed = false;
        winGame = false;
        
        this.gameStage = mainStage;
        
        resetTimer();
        initGameController(mainStage, gameModes);
        if (gameModes == GameModes.SinglePlayer){
            isPlayer = false;
        } else {
            isPlayer = true;
        }
        AI = new AIController();

        //initMap1();
        //initPlayer();
        //initRobber();
        //robberMovement();
        //initLabels();
        soundEffects = new SoundEffects();
        //initTimer();
    }

    public boolean startGame() {
        return this.readyToStart;
    }

    public void endGame() {
        this.timeRemaining = 0;
        this.gameOver = true;
        if (winGame == true) {
            winLabel.setText("GAME OVER \n YOU WIN");
        } else {
            winLabel.setText("GAME OVER \n YOU LOSE");
        }
    }

    public boolean gameIsPaused() {
        if ((this.escPressed) || (this.pausePressed) || (this.gameOver)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFinished() {
        if (this.timeRemaining <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void decreaseTime() {

        this.timeRemaining--;
    }

    public void decreasePreTime() {
        this.preTimeRemaining--;
    }

    public void resetTimer() {
        this.timeRemaining = 120;
    }

    public void zeroTimer() {
        this.timeRemaining = 1;
    }

    /**
     * timeRemaining returns the amount of time left in the game.
     */
    public int timeAmount() {
        return this.timeRemaining;
    }

    public int preTimeAmount() {
        return this.preTimeRemaining;
    }

    public void initTimer() {

    }

    public void initMap(String[] Level, String wallType, GameModes gameModes) {
        Rectangle levelBackground = new Rectangle(768, 768);

        levelWidth = Level[0].length() * pixelScale;
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
                        //wall.addToLayer();
                        //wall.updateUI();
                        break;
                    case 'p':
                        initPlayer(j * pixelScale, i * pixelScale);
                        break;
                    case 'r':
                        if (gameModes == GameModes.SinglePlayer) {
                            robber1 = initRobber(robber1, j * pixelScale, i * pixelScale, false);
                        } else if (gameModes == GameModes.MultiPlayer1) {
                            robber2 = initRobber(robber2, j * pixelScale, i * pixelScale, isPlayer);
                        }
                        break;
                    case 'm':
                        if (gameModes == GameModes.SinglePlayer) {
                            robber1 = initRobber(robber1, j * pixelScale, i * pixelScale, false);
                        }else if (gameModes == GameModes.MultiPlayer1) {
                            robber1 = initRobber(robber1, j * pixelScale, i * pixelScale, false);
                        } else if (gameModes == GameModes.MultiPlayer2) {
                            robber3 = initRobber(robber3, j * pixelScale, i * pixelScale, isPlayer);
                        }
                        break;
                    case 'a':
                        initAgent(j * pixelScale, i * pixelScale);
                        break;
                    case '1':
                        coinList.add(coin = new Coin(rootLayout, j * pixelScale + 15, i * pixelScale + 15, 2));
                        GameUI.spawn(coin);
                        //coin.updateUI();
                        break;
                    case '2':
                        break;
                    case '3':
                        carList.add(car = new Car(rootLayout, j * pixelScale + 3, i * pixelScale + 5, 0));
                        GameUI.spawn(car);
                        //car.addToLayer();
                        //car.updateUI();
                        break;
                    case '4':
                        smallCashList.add(smallCash = new SmallCash(rootLayout, j * pixelScale + 8, i * pixelScale + 10, 10));
                        GameUI.spawn(smallCash);
                        //smallCash.addToLayer();
                        //smallCash.updateUI();
                        break;
                    case '5':
                        bigCashList.add(bigCash = new BigCash(rootLayout, j * pixelScale + 8, i * pixelScale + 8, 25));
                        GameUI.spawn(bigCash);
                        //bigCash.addToLayer();
                        //bigCash.updateUI();
                        break;
                    case 'c':
                        cryptoList.add(crypto = new Crypto(rootLayout, j * pixelScale + 8, i * pixelScale + 8, randScore.nextInt(1000 + 1 + 1000) - 1000 ));
                        GameUI.spawn(crypto);
                        //bigCash.addToLayer();
                        //bigCash.updateUI();
                        break;

                }
            }
        }
    }

    //rootLayout.getChildren().add(levelBackground);
    public void initPlayer(double xPosition, double yPosition) {
        testman = new TestMan(rootLayout, xPosition + 2, yPosition + 2, true, 42, 42, playerSpeed);
        //testman = new TestMan(rootLayout, 7 * pixelScale, 7 * pixelScale + 10, true, 38, 38, playerSpeed);
        charList.add(testman);
        GameUI.spawn(testman);
        //testman.updateUI();
    }

    private Robber1 initRobber(Robber1 robber, double xPosition, double yPosition, boolean isPlayer) {
        //testRobber = new TestRobber(rootLayout, 14 * pixelScale, 14 * pixelScale, false, 35, 35, robberSpeed);
        robber = new Robber1(rootLayout, xPosition, yPosition, isPlayer, 40, 40, robberSpeed);
        charList.add(robber);
        GameUI.spawn(robber);

        return robber;
        //testRobber.updateUI();
    }
    private void initAgent(double xPosition, double yPosition) {
        //testRobber = new TestRobber(rootLayout, 14 * pixelScale, 14 * pixelScale, false, 35, 35, robberSpeed);
        agent = new Agent(rootLayout, xPosition, yPosition, false, 40, 40, agentSpeed);
        charList.add(agent);
        GameUI.spawn(agent);
        //testRobber.updateUI();
    }

    private void robberMovement() {
//		Random rand = new Random();
//		int countDirection = rand.nextInt(4);
//		int countTimer = rand.nextInt(33);
//		if (countTimer == 32 && countDirection == 0){
//			testRobber.setDx(0);
//			testRobber.setDy(-robberSpeed);
//		}else if (countTimer == 24 && countDirection == 0){
//			testRobber.setDx(robberSpeed);
//			testRobber.setDy(0);
//		}else if (countTimer == 16 && countDirection == 0){
//			testRobber.setDx(0);
//			testRobber.setDy(robberSpeed);
//		}else if (countTimer == 8 && countDirection == 0){
//			testRobber.setDx(-robberSpeed);
//			testRobber.setDy(0);
//		}
        AI.navigate(charList, testman, detector, mapPath, isPlayer);
        //System.out.println("Direction: " + countDirection);
        //System.out.println("Timer: " + countTimer);
    }

    // Get the controller up and running
    public void initGameController(Stage mainStage, GameModes gameModes) throws IOException {
        mainStage.setTitle("Gold Girl");
//		try {
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("TestMap.fxml"));
        rootLayout = (AnchorPane) loader.load();

        // Show the scene containing the root layout.
        preGameTimer = 3;
        Scene scene = new Scene(rootLayout);
        mainStage.setScene(scene);
        mainStage.show();

//TESTING GAME MODE

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                System.out.println("Move Up");
                testman.setUP(true);
                testman.rotateUP();
            } else {
                testman.setUP(false);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                //System.out.println("Move RIGHT");
                testman.setRIGHT(true);
                testman.rotateRIGHT();
            } else {
                testman.setRIGHT(false);
            }
            if (event.getCode() == KeyCode.DOWN) {
                //System.out.println("Move DOWN");
                testman.setDOWN(true);
                testman.rotateDOWN();
            } else {
                testman.setDOWN(false);
            }
            if (event.getCode() == KeyCode.LEFT) {
                //System.out.println("Move LEFT");
                testman.setLEFT(true);
                testman.rotateLEFT();
            }
            if (event.getCode() == KeyCode.P) {
                pressPause();
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                if (escPressed == true) {
                } else if (!this.pausePressed) {
                    escLabel.setText("Press Enter to quit, Backspace to go back");
                    this.escPressed = true;
                }
            }
            // Pressing Enter when the quit game prompt is on the screen will take the game back to the main menu of the game
            if (event.getCode() == KeyCode.ENTER) {
                if (this.escPressed == true || this.gameOver == true) {
                    try {
                        FXMLLoader reload = new FXMLLoader();
                        reload.setLocation(MainApp.class.getResource("MainMenu.fxml"));
                        rootLayout = (AnchorPane) reload.load();
                        Scene menuScene = new Scene(rootLayout);
                        mainStage.setScene(menuScene);
                        //mainStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                }
            }
            if(gameModes == GameModes.MultiPlayer1) {
                if (event.getCode() == KeyCode.W) {
                    //System.out.println("Move Up");
                    robber2.setUP(true);
                    robber2.rotateUP();
                } else {
                    robber2.setUP(false);
                }
                if (event.getCode() == KeyCode.D) {
                    //System.out.println("Move RIGHT");
                    robber2.setRIGHT(true);
                    robber2.rotateRIGHT();
                } else {
                    robber2.setRIGHT(false);
                }
                if (event.getCode() == KeyCode.S) {
                    //System.out.println("Move DOWN");
                    robber2.setDOWN(true);
                    robber2.rotateDOWN();
                } else {
                    robber2.setDOWN(false);
                }
                if (event.getCode() == KeyCode.A) {
                    //System.out.println("Move LEFT");
                    robber2.setLEFT(true);
                    robber2.rotateLEFT();
                }
            }
//            if (gameModes == GameModes.MultiPlayer2) {
//                if (event.getCode() == KeyCode.I) {
//                    //System.out.println("Move Up");
//                    robber3.setUP(true);
//                    robber3.rotateUP();
//                } else {
//                    robber3.setUP(false);
//                }
//                if (event.getCode() == KeyCode.L) {
//                    //System.out.println("Move RIGHT");
//                    robber3.setRIGHT(true);
//                    robber3.rotateRIGHT();
//                } else {
//                    robber3.setRIGHT(false);
//                }
//                if (event.getCode() == KeyCode.K) {
//                    //System.out.println("Move DOWN");
//                    robber3.setDOWN(true);
//                    robber3.rotateDOWN();
//                } else {
//                    robber3.setDOWN(false);
//                }
//                if (event.getCode() == KeyCode.J) {
//                    //System.out.println("Move LEFT");
//                    robber3.setLEFT(true);
//                    robber3.rotateLEFT();
//                }
//            }

            // Pressing backspace when the quit game prompt is on the screen will resume the game

            if (event.getCode() == KeyCode.BACK_SPACE) {
                if (this.escPressed == true) {
                    this.escPressed = false;
                    escLabel.setText("");
                }
            }
            if (event.getCode() == KeyCode.PAGE_DOWN) {
                //this.timeRemaining = 1;
                if (endGamePressed == true) {
                } else {
                    endGamePressed = true;
                    zeroTimer();
                    endGame();
                }
            }

        });
        scene.setOnKeyReleased(event -> {

            if (event.getCode() == KeyCode.LEFT) {
                testman.setLEFT(false);
            } else if (event.getCode() == KeyCode.UP) {
                testman.setUP(false);
            } else if (event.getCode() == KeyCode.DOWN) {
                testman.setDOWN(false);
            } else if (event.getCode() == KeyCode.RIGHT) {
                testman.setRIGHT(false);
            }
            if (gameModes == GameModes.MultiPlayer1) {
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

    public void initLabels() {
        scoreLabel = new Label("$" + String.format("%.2f", CollisionDetection.scoreUpdate.getScoreCount()));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(new Font("Calibri", 32));
        scoreLabel.setLayoutX(820);
        scoreLabel.setLayoutY(96);
        timeLabel = new Label(Integer.toString(timeSeconds) + " seconds");
        timeLabel.setTextFill(Color.WHITE);
        timeLabel.setFont(new Font("Calibri", 32));
        timeLabel.setLayoutX(820);
        timeLabel.setLayoutY(150);
        pregameLabel = new Label(Integer.toString(preGameTimer));
        pregameLabel.setFont(new Font("Calibri", 95));
        pregameLabel.setLayoutX(350);
        pregameLabel.setLayoutY(330);
        escLabel = new Label("");
        escLabel.setFont(new Font("Calibri", 45));
        escLabel.setLayoutX(150);
        escLabel.setLayoutY(330);
        winLabel = new Label("");
        winLabel.setFont(new Font("Calibri", 65));
        winLabel.setLayoutX(220);
        winLabel.setLayoutY(300);
        rootLayout.getChildren().addAll(scoreLabel, timeLabel, pregameLabel, escLabel, winLabel);
    }

    public void pressPause() {
        if (!this.pausePressed) {
            this.pausePressed = true;
        } else {
            this.pausePressed = false;
        }
    }

    public void tickChange() {
        GameUI.updateItems(cryptoList);
        GameUI.updateBoxes(wallList);
        GameUI.updateItems(coinList);
        GameUI.updateItems(smallCashList);
        GameUI.updateItems(bigCashList);
        GameUI.updateItems(carList);
        GameUI.updateActors(charList);
        if (!gameIsPaused()) {
            if (startGame() == true) {
                detector.scanCollisions(charList, mapPath, coinList, smallCashList, bigCashList, carList, cryptoList);
//				testman.changeMove();
//				testRobber.changeMove();
                for (Character x : charList) {
                    x.changeMove();
                }
                robberMovement();
                scoreLabel.setText("$" + (String.format("%.2f", CollisionDetection.scoreUpdate.getScoreCount())));

                numOfTimesTicked++;
                if (numOfTimesTicked == 60) {
                    decreaseTime();
                    if (timeAmount() == 0) {
                        endGame();
                        //timeLabel.setText(timeAmount() + " seconds");
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
        } else if (gameOver == true) {
            timeLabel.setText(timeAmount() + " seconds");
        } 
        if (checkWin()) {
        	winLabel.setText("Level Complete!");
        	if(MenuControl.getLevelCount()==1) {
        		MenuControl.launchLevel2();
        		MenuControl.setLevelCount();
        	}
        	else if(MenuControl.getLevelCount()==2) {
        		MenuControl.launchLevel3();
        		MenuControl.setLevelCount();
        	}
        }
        //System.out.println(CollisionDetection.scoreUpdate);
    }
    
    public double getPixelScale() {
    	return this.pixelScale;
    }
    
    public boolean checkWin() {
    	if(coinList.isEmpty()&&smallCashList.isEmpty()&&bigCashList.isEmpty()&&cryptoList.isEmpty()) {
    		return true;
    	}
    	return false;
    }
    
    public MenuControl getMenu() {
    	return this.menuHub;
    }
}