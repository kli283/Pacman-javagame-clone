package model;

import javafx.scene.layout.AnchorPane;
import model.Box;

public class BrickWall extends Box{
    public BrickWall(AnchorPane layer, double xPos, double yPos, double setHeight, double setWidth) {
        super(layer, xPos, yPos, setHeight, setWidth);

        this.setImage("/model/Brick.png");
    }

    public void addToLayer() {
        this.getLayer().getChildren().add(this.getImageView());
    }

    public void updateUI() {
        getImageView().relocate(this.getXPos(),this.getYPos());
    }
}
