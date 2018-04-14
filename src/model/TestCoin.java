package model;

import javafx.scene.layout.AnchorPane;

public class TestCoin extends Item {

    public TestCoin(AnchorPane layer, double xPos, double yPos, int score) {
        super(layer, xPos, yPos, score);

        this.setImage("/model/coin.png");
    }

    public void addToLayer() {
        this.layer.getChildren().add(this.getImageView());
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.getImageView());
    }

    public void updateUI() {
        getImageView().relocate(this.getXPos(),this.getYPos());
    }
}