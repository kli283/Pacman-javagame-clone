package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundEffects {
    Media coin = new Media((SoundEffects.class.getResource("/view/Resources/New Coin.wav").toString()));
    MediaPlayer coinSound = new MediaPlayer(coin);
    Media cash = new Media((SoundEffects.class.getResource("/view/Resources/Cash.wav").toString()));
    MediaPlayer cashSound = new MediaPlayer(cash);
    Media car = new Media((SoundEffects.class.getResource("/view/Resources/car.wav").toString()));
    MediaPlayer carSound = new MediaPlayer(car);
    Media hit = new Media((SoundEffects.class.getResource("/view/Resources/GirlHit.wav").toString()));
    MediaPlayer hitSound = new MediaPlayer(hit);

    public SoundEffects() {

    }

    public void playCoin() {
        soundHandler(coinSound);
    }

    public void playCar() {
        soundHandler(carSound);
    }

    public void playCash() {
        soundHandler(cashSound);
    }

    public void playHit() {
        soundHandler(hitSound);
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
