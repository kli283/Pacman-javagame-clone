package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundEffects {
    static boolean enableSounds = true;

    private Media coin = new Media((SoundEffects.class.getResource("/view/Resources/New Coin.wav").toString()));
    private MediaPlayer coinSound = new MediaPlayer(coin);
    private Media cash = new Media((SoundEffects.class.getResource("/view/Resources/Cash.wav").toString()));
    private MediaPlayer cashSound = new MediaPlayer(cash);
    private Media car = new Media((SoundEffects.class.getResource("/view/Resources/Car.wav").toString()));
    private MediaPlayer carSound = new MediaPlayer(car);
    private Media hit = new Media((SoundEffects.class.getResource("/view/Resources/GirlHit.wav").toString()));
    private MediaPlayer hitSound = new MediaPlayer(hit);
    private Media manHit = new Media((SoundEffects.class.getResource("/view/Resources/ManHit.wav").toString()));
    private MediaPlayer manHitSound = new MediaPlayer(manHit);
    private Media bang = new Media((SoundEffects.class.getResource("/view/Resources/Bang.wav").toString()));
    private MediaPlayer bangSound = new MediaPlayer(bang);
    private Media gold = new Media((SoundEffects.class.getResource("/view/Resources/Gold.wav").toString()));
    private MediaPlayer goldSound = new MediaPlayer(gold);

    public SoundEffects() {

    }

    public void playCoin() {
        if(enableSounds){
            soundHandler(coinSound);
        }
    }

    public void playCar() {
        if(enableSounds) {
            soundHandler(carSound);
        }
    }

    public void playCash() {
        if (enableSounds) {
            soundHandler(cashSound);
        }
    }

    public void playHit() {
        if (enableSounds) {
            soundHandler(hitSound);
        }
    }
    public void playManHit() {
        if (enableSounds) {
            soundHandler((manHitSound));
        }
    }
    public void playBang() {
        if (enableSounds) {
            soundHandler(bangSound);
        }
    }

    public void playGold() {
        if (enableSounds) {
            soundHandler(goldSound);
        }
    }
    private void soundHandler(MediaPlayer sound) {
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
