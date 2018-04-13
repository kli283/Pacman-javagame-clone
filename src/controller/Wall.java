package controller;

import javafx.scene.layout.AnchorPane;

public class Wall extends Box{
    public Wall(AnchorPane layer, double xPos, double yPos, double setHeight, double setWidth, String imageURL) {
        super(layer, xPos, yPos, setHeight, setWidth);

        this.setImage(imageURL);
    }

    public void addToLayer() {
        this.layer.getChildren().add(this.getImageView());
    }

    public void updateUI() {
        getImageView().relocate(this.getXPos(),this.getYPos());
    }
}
