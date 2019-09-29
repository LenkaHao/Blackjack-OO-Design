/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
 */

import java.util.List;

public class GameVisualizer {

    public void msg(String str) {
        System.out.println(str);
    }

    public void welcomeMsg() {
        System.out.println("Welcome to our game!");
        System.out.println("Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.\n");
    }

    public void invalidAction(String action) {
        System.out.println("You cannot " + action + ". Please select another action.");
    }

    public void displayInfo(int playerId, int playerBalance, int handIdx) {
        System.out.println("Player: " + playerId + ", Balance = " + playerBalance + ", Hand: " + handIdx);
    }

    public void displayPlayerHand(int handIdx, BlackjackHand hand) {
        System.out.println("Your hand " + handIdx + " is:\n" + hand);
    }

    public void printPlayerWinningState() {

    }

    /**
     * visualize the game, print game data at the end of each round
     * @param players
     */
    public void printPlayerBalance(List<BlackjackPlayer> players, int roundNum) {
        System.out.println("\nRound " + roundNum + " result:");

        for (BlackjackPlayer player : players) {
            System.out.println("Player " + player.getId() + " current balance is " + player.getBalance());
        }
    }
}