package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundEffects {
    Media coin = new Media((SoundEffects.class.getResource("/view/Resources/Coin.wav").toString()));
    MediaPlayer coinSound = new MediaPlayer(coin);

    public SoundEffects(){

    }

    public void playCoin() {
            soundHandler(coinSound);
        }

    public void soundHandler(MediaPlayer sound) {
        try {
            new Thread() {
                public void run() {
                    sound.stop();
                    sound.play();
                }
            }
                    .start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
