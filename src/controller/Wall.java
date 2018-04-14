package controller;

import javafx.scene.layout.AnchorPane;
import model.Box;

public class Wall extends Box{
    public Wall(AnchorPane layer, double xPos, double yPos, double setHeight, double setWidth, String imageURL) {
        super(layer, xPos, yPos, setHeight, setWidth);

        this.setImage(imageURL);
    }

    public void addToLayer() {
        this.getLayer().getChildren().add(this.getImageView());
    }

    public void updateUI() {
        getImageView().relocate(this.getXPos(),this.getYPos());
    }
}
