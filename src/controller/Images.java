package controller;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {

        private Group root;

        // Load the background image
        private Image tempImage = new Image ("view/Resources/gameBackground.png");
        private ImageView background = new ImageView(tempImage);



    public ImageView getBackground() {
        return this.background;
    }
}
