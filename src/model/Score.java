package model;

/* Score.java
 * 
 * A class for keeping track and updating the game score
 * 
 * Author: Kenny Li, James Flood
 * 
 * 2018
 * 
 */

public class Score {
    private double scoreCount;

    public Score(int scoreCount){
        setScoreCount(scoreCount);
    }

    public double getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(double scoreCount) {
        this.scoreCount = scoreCount;
    }

    public void updateScoreCount(double scoreCount){
        this.scoreCount += scoreCount;
    }

    public void hitRobberScore(double scoreCount){
        this.scoreCount *= 0.7;
    }
}
