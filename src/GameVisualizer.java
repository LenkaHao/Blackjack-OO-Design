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

    public void invalidAction(String action) {
        System.out.println("You cannot " + action + ". Please select another action.");
    }

    public void playHandInfo(int playerId, int playerBalance, int handIdx, int bet) {
        System.out.println("Player: " + playerId + ", Hand: " + handIdx + "Bet for this hand: "+ bet + ", Balance = " + playerBalance );
    }

    public void displayDealerCard(BlackjackCard card) {
        System.out.println("Dealer's face-up card: " + card);
    }

    public void displayDealerHand(BlackjackHand hand) {
        System.out.println("Dealer's current hand is: \n" + hand);
        System.out.println("Dealer's current hand has value: " + hand.getTotalValue());
    }

    public void displayPlayerHand(BlackjackHand hand) {
        System.out.println("Your current hand is:\n" + hand);
        System.out.println("Your current hand has value: " + hand.getTotalValue());
    }


    public void displayActionChoices(String[] actions) {
        System.out.println("\nPlease select your next action with its corresponding number (e.g., 0 to hit):");
        int idx = 1;
        for (String action : actions) {
            System.out.print(action + ": " + idx++ + "\t");
        }
        System.out.println();
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

    public void playerLeaves(BlackjackPlayer player) {
        System.out.println("Player " + player.getId() + "leaves the game with $" + player.getBalance());
    }
}