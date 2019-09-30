/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
 */

public class GameVisualizer {

    public void msg(String str) {
        System.out.println(str);
    }

    public void welcomeMsg() {
        System.out.println("Welcome to our game!");
        System.out.println("Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.\n");
    }

    public void displayInfo(int playerId, int playerBalance, int handIdx) {
        System.out.println("Player: " + playerId + ", Balance = " + playerBalance + ", Hand: " + handIdx);
    }

    public void displayPlayerHand(int handIdx, BlackjackHand hand) {
        System.out.println("Your current hand " + handIdx + " is:\n" + hand);
    }

    public void displayDealerHand(BlackjackHand hand) {
        System.out.println("Dealer's current hand is:\n" + hand);
    }

    public void invalidAction(String action) {
        System.out.println("You cannot " + action + ". Please select another action.");
    }

    public void printPlayerBalance(int playerId, int roundBalance, int playerBalance, int roundNum) {
        System.out.println("\nPlayer " + playerId + " current balance is " + playerBalance);
        if (roundBalance > 0)
            System.out.println("At round " + roundNum + ", Player " + playerId + " wins " + roundBalance + ".");
        else if (roundBalance == 0)
            System.out.println("At round " + roundNum + ", Player " + playerId + " is tie.");
        else
            System.out.println("At round " + roundNum + ", Player " + playerId + " loses " + -roundBalance + ".");
        System.out.println("Player " + playerId + " current balance is " + playerBalance);
    }
}