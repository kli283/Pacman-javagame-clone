package model;

import javafx.scene.layout.AnchorPane;

public class Crypto extends Item {

    public Crypto(AnchorPane layer, double xPos, double yPos, int score) {
        super(layer, xPos, yPos, score);

        this.setImage("/view/Resources/Crypto.png");
    }

}