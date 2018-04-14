package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import javafx.scene.shape.Rectangle;
import view.*;
import model.Character;

public class GameController { // Class to contain main game loop

	private AnchorPane rootLayout;
	private TestMan testman;
	private TestCoin testCoin;
	private SmallCash smallCash;
	private BigCash bigCash;
	private BrickWall brickWall;
	private GreyWall greyWall;
	private DirtWall dirtWall;
	private Car car;
	private TestRobber testRobber;
	private AIController AI;
	private GameUI UI;
	private double playerSpeed = 3;
	private double robberSpeed = 2;
	private ArrayList<Rectangle> mapPath = new ArrayList<>();
	private ArrayList<Character> charList = new ArrayList<Character>();
	private ArrayList<Item> coinList = new ArrayList<Item>();
	private ArrayList<Item> smallCashList = new ArrayList<>();
	private ArrayList<Item> bigCashList = new ArrayList<>();
	private ArrayList<Box> wallList = new ArrayList<>();
	private ArrayList<Item> carList = new ArrayList<>();
	private int pixelScale = 48;
	double coinPosX;
	double coinPosY;
	private int levelWidth;
	private GameModes gameModes;
	private Label scoreLabel;
	private Label timeLabel;
	private Label pregameLabel;
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
//	Rectangle rect1;
//	Rectangle rect2;
//	Rectangle rect3;
//	Rectangle rect4;


    private CollisionDetection detector = new CollisionDetection();
    Rectangle wall1;
    Rectangle wall2;


	public GameController(Stage mainStage) throws IOException {
		timeRemaining = 0;
		numOfTimesTicked = 0;
		escPressed = false;
		pausePressed = false;
		endGamePressed = false;
		winGame = false;
		resetTimer();
		initGameController(mainStage);

		//initMap1();
		//initPlayer();
		initRobber();
		//robberMovement();
		//initLabels();
		//initTimer();
	}
	public boolean startGame() {
		return this.readyToStart;
	}

	public void endGame() {
		this.gameOver = true;
		if (winGame == true) {
			//label displays win
		} else {
			//label displays lose
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
		}else {
			return false;
		}
	}
	public void decreaseTime() {

		this.timeRemaining--;
	}
	public void decreasePreTime() {
		this.preTimeRemaining--;
	}

	/**
	 * resetTimer resets the game's timer.
	 */
	public void resetTimer() {
		this.timeRemaining = 120;
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
	public void initMap(String[] Level, String wallType){
		Rectangle levelBackground = new Rectangle(768, 768);

		levelWidth = Level[0].length() * pixelScale;
		String imageURL = "/model/" + wallType + ".png";

		for (int i = 0; i < Level.length; i++) {
			String line = Level[i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
					case 'p':
						initPlayer(j * pixelScale, i * pixelScale);
						break;
					case '0':
						Wall wall = new Wall(rootLayout, j * pixelScale, i * pixelScale, pixelScale, pixelScale, imageURL);
						wallList.add(wall);
						mapPath.add(wall.getBoundary());
						GameUI.spawn(wall);
						//wall.addToLayer();
						//wall.updateUI();
						break;
					case '1':
						coinList.add(testCoin = new TestCoin(rootLayout, j * pixelScale + 15, i * pixelScale + 15, 2));
						GameUI.spawn(testCoin);
						//testCoin.updateUI();
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
						smallCashList.add(smallCash = new SmallCash(rootLayout, j * pixelScale + 4, i * pixelScale + 8, 10));
						GameUI.spawn(smallCash);
						//smallCash.addToLayer();
						//smallCash.updateUI();
						break;
					case '5':
						bigCashList.add(bigCash = new BigCash(rootLayout, j * pixelScale + 4, i * pixelScale + 8, 25));
						GameUI.spawn(bigCash);
						//bigCash.addToLayer();
						//bigCash.updateUI();
						break;

				}
			}
		}
	}
		//rootLayout.getChildren().add(levelBackground);
	public void initPlayer(double xPosition, double yPosition){
		testman = new TestMan(rootLayout, xPosition, yPosition, true, 42, 42, playerSpeed);
		//testman = new TestMan(rootLayout, 7 * pixelScale, 7 * pixelScale + 10, true, 38, 38, playerSpeed);
		charList.add(testman);
		GameUI.spawn(testman);
		//testman.updateUI();
	}
	private void initRobber(){
		testRobber = new TestRobber(rootLayout, 14 * pixelScale, 14 * pixelScale, false, 35, 35, robberSpeed);
		charList.add(testRobber);
		GameUI.spawn(testRobber);
		//testRobber.updateUI();
		AI = new AIController(testRobber, testman);
	}

	private void robberMovement(){
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
		AI.navigate(testRobber, testman);
		//System.out.println("Direction: " + countDirection);
		//System.out.println("Timer: " + countTimer);
	}
	
	// Get the controller up and running
	public void initGameController(Stage mainStage) throws IOException {
		mainStage.setTitle("Test Character Movement");
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
				} else {
					testman.setUP(false);
				}
				if (event.getCode() == KeyCode.RIGHT) {
					System.out.println("Move RIGHT");
					testman.setRIGHT(true);
				} else {
					testman.setRIGHT(false);
				}
				if (event.getCode() == KeyCode.DOWN) {
					System.out.println("Move DOWN");
					testman.setDOWN(true);
				} else {
					testman.setDOWN(false);
				}
				if (event.getCode() == KeyCode.LEFT) {
					System.out.println("Move LEFT");
					testman.setLEFT(true);
				} else {
					testman.setLEFT(false);
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
					});

	}
	public void initLabels(){
		scoreLabel = new Label("$" + (Integer.toString(CollisionDetection.scoreUpdate)));
		scoreLabel.setFont(new Font("Calibri", 32));
		scoreLabel.setLayoutX(845);
		scoreLabel.setLayoutY(96);
		timeLabel = new Label(Integer.toString(timeSeconds) + " seconds");
		timeLabel.setFont(new Font("Calibri", 32));
		timeLabel.setLayoutX(845);
		timeLabel.setLayoutY(150);
		pregameLabel = new Label(Integer.toString(preGameTimer));
		pregameLabel.setFont(new Font("Calibri", 95));
		pregameLabel.setLayoutX(350);
		pregameLabel.setLayoutY(330);
		rootLayout.getChildren().addAll(scoreLabel, timeLabel, pregameLabel);
	}

	public void tickChange(){
		GameUI.updateActors(charList);
		GameUI.updateBoxes(wallList);
		GameUI.updateItems(coinList);
		GameUI.updateItems(smallCashList);
		GameUI.updateItems(bigCashList);
		GameUI.updateItems(carList);
		if (!gameIsPaused()) {
			if (startGame() == true) {
				detector.scanCollisions(charList, mapPath, coinList, smallCashList, bigCashList, carList);
				testman.changeMove();
				testRobber.changeMove();
				robberMovement();
				scoreLabel.setText("$" + (Integer.toString(CollisionDetection.scoreUpdate)));

				numOfTimesTicked++;
				if (numOfTimesTicked == 60) {
					decreaseTime();
					if (timeAmount() == 0) {
						endGame();
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
		}
		//System.out.println(CollisionDetection.scoreUpdate);
	}
}