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
        Scanner sc = new Scanner(System.in);
        int playerBet;
        System.out.println("Current balance of player " + getId() + " is: " + getBalance());
        System.out.println("Please enter an integer between 1 and your balance as bet: ");
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer between 1 and your balance as bet.\n");
                sc.next();
            }
            playerBet = sc.nextInt();
            if (playerBet >= 1 && playerBet <= getBalance()) {
                break;
            } else {
                System.out.println("Invalid input. Please enter an integer between 1 and your balance as bet.\n");
            }
        } while (true);
        getHandAt(0).setBet(playerBet);
        setBalance(-playerBet);
    }

    public boolean cashOut() {
        Scanner scanner = new Scanner(System.in);
        boolean isCashOut = false;
        System.out.println("Do you want to cash out? Please enter Y/y for yes. All other input means no.");
        String choice = scanner.nextLine();
        if (choice.equals("y") || choice.equals("Y")) {
            isCashOut = true;
        }
        return isCashOut;
    }

    public int getHandCount() {
        return getHands().size();
    }

    public BlackjackHand getHandAt(int idx) {
        return (BlackjackHand) getHands().get(idx);
    }
}
