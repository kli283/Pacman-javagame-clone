package model;

import javafx.scene.layout.AnchorPane;
import model.Box;

public class GreyWall extends Box{
    public GreyWall(AnchorPane layer, double xPos, double yPos, double setHeight, double setWidth) {
        super(layer, xPos, yPos, setHeight, setWidth);

        this.setImage("/view/Resources/BrickGrey.png");
    }

    public void addToLayer() {
        this.getLayer().getChildren().add(this.getImageView());
    }

    public void updateUI() {
        getImageView().relocate(this.getXPos(),this.getYPos());
    }
}
