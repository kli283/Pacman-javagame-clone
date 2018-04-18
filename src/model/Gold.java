package model;

import javafx.scene.layout.AnchorPane;

public class Gold extends Item {

    public Gold(AnchorPane layer, double xPos, double yPos, int score) {
        super(layer, xPos, yPos, score);

        this.setImage("/view/Resources/gold.png");
    }

}