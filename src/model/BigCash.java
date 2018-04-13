package model;

import javafx.scene.layout.AnchorPane;

public class BigCash extends Item {

    public BigCash(AnchorPane layer, double xPos, double yPos, int score) {
        super(layer, xPos, yPos, score);

        this.setImage("/model/BigCash.png");
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