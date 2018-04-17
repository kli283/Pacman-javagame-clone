package model;

import javafx.scene.layout.AnchorPane;

public class Coin extends Item {

    public Coin(AnchorPane layer, double xPos, double yPos, int score) {
        super(layer, xPos, yPos, score);

        this.setImage("/view/Resources/coin.png");
    }
}