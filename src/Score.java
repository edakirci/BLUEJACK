public class Score {
    private int bjScorePlayer;
    private int bjScoreComputer;
    private int normalScorePlayer;
    private int normalScoreComputer;


    public Score(int bjScorePlayer, int bjScoreComputer, int normalScorePlayer, int normalScoreComputer) {
        this.bjScorePlayer = bjScorePlayer;
        this.bjScoreComputer = bjScoreComputer;
        this.normalScorePlayer = normalScorePlayer;
        this.normalScoreComputer = normalScoreComputer;

    }

    public int getBjScorePlayer() {
        return bjScorePlayer;
    }

    public void setBjScorePlayer(int bjScorePlayer) {
        this.bjScorePlayer = bjScorePlayer;
    }

    public int getBjScoreComputer() {
        return bjScoreComputer;
    }

    public void setBjScoreComputer(int bjScoreComputer) {
        this.bjScoreComputer = bjScoreComputer;
    }

    public int getNormalScorePlayer() {
        return normalScorePlayer;
    }

    public void setNormalScorePlayer(int normalScorePlayer) {
        this.normalScorePlayer = normalScorePlayer;
    }

    public int getNormalScoreComputer() {
        return normalScoreComputer;
    }

    public void setNormalScoreComputer(int normalScoreComputer) {
        this.normalScoreComputer = normalScoreComputer;
    }
}
