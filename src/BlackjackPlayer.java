/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */
import java.util.Scanner;

public class BlackjackPlayer extends Player {
    // Since there can be multiple players, each player needs an id to identify himself
    private int id;
    private int balance;
    private int bet;

    public BlackjackPlayer(int id, int balance) {
        super();
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBet() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int playerBet = 0;
        while (!isValid) {
            System.out.println("Current balance of player " + getId() + " is: " + getBalance());
            System.out.println("Please enter an integer between 1 and your balance as bet: ");
            playerBet = scanner.nextInt();
            if (playerBet >= 1 && playerBet <= balance) {
                isValid = true;
            } else {
                System.out.println("Please enter a valid bet.");
            }
        }
        this.bet = playerBet;
    }

    public int getBet() {
        return bet;
    }

    public int getHandCount() {
        return getHands().size();
    }

    public BlackjackHand getHandAt(int idx) {
        return getHands().get(idx);
    }
}
