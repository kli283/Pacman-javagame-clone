package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.TestCoin;
import model.TestMan;
import model.Car;
import javafx.scene.shape.Rectangle;
import model.TestRobber;
import view.GameModes;
import javafx.animation.Timeline;

import java.util.Random;

public class GameController { // Class to contain main game loop

	private AnchorPane rootLayout;
	private TestMan testman;
	private TestCoin testCoin;
	private BrickWall brickWall;
	private GreyWall greyWall;
	private DirtWall dirtWall;
	private Car car;
	private TestRobber testRobber;
	private AIController AI;
	private double playerSpeed = 3;
	private double robberSpeed = 2;
	private ArrayList<Rectangle> mapPath = new ArrayList<>();
	@SuppressWarnings("rawtypes")
	private ArrayList charList = new ArrayList<Character>();
	private ArrayList<TestCoin> coinList = new ArrayList<>();
	private ArrayList<BrickWall> brickWallList = new ArrayList<>();
	private ArrayList<GreyWall> greyWallList = new ArrayList<>();
	private ArrayList<DirtWall> dirtWallList = new ArrayList<>();

	//private ArrayList<Rectangle> wallList = new ArrayList<>();
	private ArrayList<Car> carList = new ArrayList<>();
	private int pixelScale = 48;
	double coinPosX;
	double coinPosY;
	private int levelWidth;
	private GameModes gameModes;
	private Label scoreLabel;
	private static final Integer STARTTIME = 120;
	private Timeline timeline;
	private Label timerLabel = new Label();
	private Integer timeSeconds = STARTTIME;
//	Rectangle rect1;
//	Rectangle rect2;
//	Rectangle rect3;
//	Rectangle rect4;


    private CollisionDetection detector = new CollisionDetection();
    Rectangle wall1;
    Rectangle wall2;


	public GameController(Stage mainStage) {

		initGameController(mainStage);
		//initMap1();
		//initPlayer();
		initRobber();
		//robberMovement();
		initMoney();
		//initTimer();
	}
	public void initTimer() {

	}

	public void initMap1() {
		Rectangle levelBackground = new Rectangle(768, 768);

		levelWidth = LevelData.LEVEL1[0].length() * pixelScale;

		for (int i = 0; i < LevelData.LEVEL1.length; i++) {
			String line = LevelData.LEVEL1[i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
					case 'p':
						initPlayer(j * pixelScale, i * pixelScale);
						break;
					case '0':
						brickWallList.add(brickWall = new BrickWall(rootLayout, j * pixelScale, i * pixelScale, pixelScale, pixelScale));
						mapPath.add(brickWall.getBoundary());
						brickWall.addToLayer();
						brickWall.updateUI();
						break;
					case '1':
						coinList.add(testCoin = new TestCoin(rootLayout, j * pixelScale + 15, i * pixelScale + 15, 2));
						testCoin.addToLayer();
						testCoin.updateUI();
						break;
					case '2':
						break;
					case '3':
						carList.add(car = new Car(rootLayout, j * pixelScale + 3, i * pixelScale + 5, 0));
						car.addToLayer();
						car.updateUI();

				}
			}
		}
	}
	public void initMap2() {

		levelWidth = LevelData.LEVEL1[0].length() * pixelScale;

		for (int i = 0; i < LevelData.LEVEL2.length; i++) {
			String line = LevelData.LEVEL2[i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
					case '0':
						greyWallList.add(greyWall = new GreyWall(rootLayout, j * pixelScale, i * pixelScale, pixelScale, pixelScale));
						mapPath.add(greyWall.getBoundary());
						greyWall.addToLayer();
						greyWall.updateUI();
						break;
					case '1':
						coinList.add(testCoin = new TestCoin(rootLayout, j * pixelScale + 15, i * pixelScale + 15, 2));
						testCoin.addToLayer();
						testCoin.updateUI();
						break;
					case '2':
						break;
					case '3':
						carList.add(car = new Car(rootLayout, j * pixelScale + 3, i * pixelScale + 5, 0));
						car.addToLayer();
						car.updateUI();
					case 'p':
						initPlayer(j * pixelScale, i * pixelScale);
						break;
				}
			}
		}
	}
	public void initMap3() {

		levelWidth = LevelData.LEVEL1[0].length() * pixelScale;

		for (int i = 0; i < LevelData.LEVEL3.length; i++) {
			String line = LevelData.LEVEL3[i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
					case '0':
						dirtWallList.add(dirtWall = new DirtWall(rootLayout, j * pixelScale, i * pixelScale, pixelScale, pixelScale));
						mapPath.add(greyWall.getBoundary());
						greyWall.addToLayer();
						greyWall.updateUI();
						break;
					case '1':
						coinList.add(testCoin = new TestCoin(rootLayout, j * pixelScale + 15, i * pixelScale + 15, 2));
						testCoin.addToLayer();
						testCoin.updateUI();
						break;
					case '2':
						break;
					case '3':
						carList.add(car = new Car(rootLayout, j * pixelScale + 3, i * pixelScale + 5, 0));
						car.addToLayer();
						car.updateUI();
				}
			}
		}
	}
		//rootLayout.getChildren().add(levelBackground);
	public void initPlayer(double xPosition, double yPosition){
		testman = new TestMan(rootLayout, xPosition, yPosition, true, 38, 38, playerSpeed);
		//testman = new TestMan(rootLayout, 7 * pixelScale, 7 * pixelScale + 10, true, 38, 38, playerSpeed);
		charList.add(testman);
		testman.addToLayer();
		testman.updateUI();
	}
	private void initRobber(){
		testRobber = new TestRobber(rootLayout, 14 * pixelScale, 14 * pixelScale, false, 35, 35, robberSpeed);
		charList.add(testRobber);
		testRobber.addToLayer();
		testRobber.updateUI();
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
		testRobber.updateUI();
		//System.out.println("Direction: " + countDirection);
		//System.out.println("Timer: " + countTimer);
	}
	
	// Get the controller up and running
	public void initGameController(Stage mainStage) {
		mainStage.setTitle("Test Character Movement");
		try {
            // Load root layout from FXML file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TestMap.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainStage.setScene(scene);
            mainStage.show();
          

//TESTING GAME MODE


				//Initialise ArrayList to store currently pressed keys
				ArrayList<String> input = new ArrayList<String>();

				//Initialise EventHandler to listen for key presses, add them to input ArrayList, and when they are released remove them
				scene.setOnKeyPressed(
						new EventHandler<KeyEvent>() {
							public void handle(KeyEvent e) {
								String code = e.getCode().toString();
								if (!input.contains(code)) {
									input.add(code);
									if (input.contains("UP")) {
										System.out.println("Move Up");
										testman.setUP(true);
									} else {
										testman.setUP(false);
									}
									if (input.contains("RIGHT")) {
										System.out.println("Move Right");
										testman.setRIGHT(true);

									} else {
										testman.setRIGHT(false);
									}
									if (input.contains("DOWN")) {
										testman.setDOWN(true);
										System.out.println("Move Down");

									} else {
										testman.setDOWN(false);
									}
									if (input.contains("LEFT")) {
										System.out.println("Move Left");
										testman.setLEFT(true);
									}
								}
							}
						});
				scene.setOnKeyReleased(
						new EventHandler<KeyEvent>() {
							public void handle(KeyEvent e) {
								String code = e.getCode().toString();
								if (code == "LEFT") {
									testman.setLEFT(false);
								} else if (code == "RIGHT") {
									testman.setRIGHT(false);
								} else if (code == "UP") {
									testman.setUP(false);
								} else if (code == "DOWN") {
									testman.setDOWN(false);
								}
								input.remove(code);
								System.out.println("Key Released");
							}
						});

				mainStage.show();

				new AnimationTimer() {

					public void handle(long currentNanoTime) {
						// TODO Put graphics drawing classes, methods what-have-you in here.
						testman.updateUI();
					}

				}.start();
			if(MenuControl.mode == GameModes.MultiPlayer1) {
				ArrayList<String> inputMulti1 = new ArrayList<String>();

				//Initialise EventHandler to listen for key presses, add them to input ArrayList, and when they are released remove them
				scene.setOnKeyPressed(
						new EventHandler<KeyEvent>() {
							public void handle(KeyEvent e) {
								String code = e.getCode().toString();
								if (!inputMulti1.contains(code)) {
									inputMulti1.add(code);
									if (inputMulti1.contains("W")) {
										System.out.println("Move Up");
										testRobber.setUP(true);
									} else {
										testRobber.setUP(false);
									}
									if (inputMulti1.contains("D")) {
										System.out.println("Move Right");
										testRobber.setRIGHT(true);

									} else {
										testRobber.setRIGHT(false);
									}
									if (inputMulti1.contains("S")) {
										testRobber.setDOWN(true);
										System.out.println("Move Down");

									} else {
										testRobber.setDOWN(false);
									}
									if (inputMulti1.contains("A")) {
										System.out.println("Move Left");
										testRobber.setLEFT(true);
									}
								}
							}
						});
				scene.setOnKeyReleased(
						new EventHandler<KeyEvent>() {
							public void handle(KeyEvent e) {
								String code = e.getCode().toString();
								if (code == "A") {
									testRobber.setLEFT(false);
								} else if (code == "D") {
									testRobber.setRIGHT(false);
								} else if (code == "W") {
									testRobber.setUP(false);
								} else if (code == "S") {
									testRobber.setDOWN(false);
								}
								input.remove(code);
							}
						});
				mainStage.show();
				new AnimationTimer() {

					public void handle(long currentNanoTime) {
						// TODO Put graphics drawing classes, methods what-have-you in here.
						testRobber.updateUI();
					}

				}.start();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	private void initMoney(){
		scoreLabel = new Label((Integer.toString(CollisionDetection.scoreUpdate)));
		scoreLabel.setFont(new Font("Calibri", 32));
		scoreLabel.setLayoutX(845);
		scoreLabel.setLayoutY(96);
		rootLayout.getChildren().add(scoreLabel);
	}

	public void tickChange(){
		detector.scanCollisions(charList, mapPath, coinList, carList);
		testman.changeMove();
		testRobber.changeMove();
		robberMovement();
		scoreLabel.setText(("$" + Integer.toString(CollisionDetection.scoreUpdate)));
		//System.out.println(CollisionDetection.scoreUpdate);
	}
}