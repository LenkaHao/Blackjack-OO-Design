/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.Scanner;

public class BlackjackPlayer extends Player {

    private int balance;

    // Constructor
    public BlackjackPlayer(int id, int balance) {
        super(id);
        this.balance = balance;
        addHand(new BlackjackHand());
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int currency) {
        balance += currency;
    }

    /**
     * Ask player to make initial bet
     */
    public void makeBet() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int playerBet = 0;
        while (!isValid) {
            System.out.println("Current balance of player " + getId() + " is: " + getBalance());
            System.out.println("Please enter an integer between 1 and your balance as bet: ");
            playerBet = scanner.nextInt();
            if (playerBet >= 1 && playerBet <= getBalance()) {
                isValid = true;
            } else {
                System.out.println("Please enter a valid bet.");
            }
        }
        getHandAt(0).setBet(playerBet);
        setBalance(-playerBet);
    }

    public int getHandCount() {
        return getHands().size();
    }

    public BlackjackHand getHandAt(int idx) {
        return (BlackjackHand) getHands().get(idx);
    }
}
