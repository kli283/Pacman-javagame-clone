package controller;

import javafx.scene.layout.AnchorPane;

public class DirtWall extends Box{
    public DirtWall(AnchorPane layer, double xPos, double yPos, double setHeight, double setWidth) {
        super(layer, xPos, yPos, setHeight, setWidth);

        this.setImage("/model/Dirt.png");
    }

    public void addToLayer() {
        this.layer.getChildren().add(this.getImageView());
    }

    public void updateUI() {
        getImageView().relocate(this.getXPos(),this.getYPos());
    }
}
