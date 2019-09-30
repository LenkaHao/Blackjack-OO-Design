public abstract class Game {
    private int round;

    Game(int round) {
        this.round = 1;
    }
    abstract void start();

    int getRound() {
        return round;
    }

    void setRound(int round) {
        this.round = round;
    }

    abstract void playARound();
}
