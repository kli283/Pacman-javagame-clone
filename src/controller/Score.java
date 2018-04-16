package controller;

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
        this.scoreCount = scoreCount * 0.7;
    }


}
