package controller;

import javafx.scene.layout.AnchorPane;

public class TestWall extends Box{
    public TestWall(AnchorPane layer, double xPos, double yPos) {
        super(layer, xPos, yPos);

        this.setImage("/model/TestWall.png");
    }

    public void addToLayer() {
        this.layer.getChildren().add(this.getImageView());
    }

    public void updateUI() {
        getImageView().relocate(this.getXPos(),this.getYPos());
    }
}
