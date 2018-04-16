package model;

import javafx.scene.layout.AnchorPane;

public class Car extends Item {

    public Car(AnchorPane layer, double xPos, double yPos, int score) {
        super(layer, xPos, yPos, score);

        this.setImage("/view/Resources/car.png");
    }

    public void addToLayer() {
        this.getLayer().getChildren().add(this.getImageView());
    }

    public void removeFromLayer() {
        this.getLayer().getChildren().remove(this.getImageView());
    }

    public void updateUI() {
        getImageView().relocate(this.getXPos(),this.getYPos());
    }
}