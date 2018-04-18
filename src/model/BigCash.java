package model;

import javafx.scene.layout.AnchorPane;

public class BigCash extends Item {

    public BigCash(AnchorPane layer, double xPos, double yPos, int score) {
        super(layer, xPos, yPos, score);

        this.setImage("/view/Resources/BigCash.png");
    }

}