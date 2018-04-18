package model;

import javafx.scene.layout.AnchorPane;

public class SmallCash extends Item {

    public SmallCash(AnchorPane layer, double xPos, double yPos, int score) {
        super(layer, xPos, yPos, score);

        this.setImage("/view/Resources/SmallCash.png");
    }

}