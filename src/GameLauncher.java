/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
 *
 * A generic entrance class that initiates a card game and starts it
 */

public class GameLauncher {
    public static void main(String args[]) {
        BlackjackGame game = new BlackjackGame();
        game.start();
    }
}
