package model;

import javafx.scene.layout.AnchorPane;

public class Car extends Item {

    public Car(AnchorPane layer, double xPos, double yPos, int score) {
        super(layer, xPos, yPos, score);

        this.setImage("/view/Resources/car.png");
    }

}