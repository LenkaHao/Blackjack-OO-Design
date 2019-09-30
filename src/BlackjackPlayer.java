/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.Scanner;

public class BlackjackPlayer extends Player {

    private int balance;

    /**
     * Constructor.
     * @param id player id.
     * @param balance Initial balance for the player.
     */
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
        int input;
        boolean isValid = false;
        System.out.println("Current balance of player " + getId() + " is: " + getBalance());
        System.out.println("Player " + getId() + ", please enter an integer between 1 and " + getBalance() + " as bet: ");

        while (!isValid) {
            input = getInteger(sc.nextLine());
            if (input >= 1 && input <= getBalance()) {
                isValid = true;
                getHandAt(0).setBet(input);
                setBalance(-input);
            } else {
                System.out.println("Invalid input. Please enter an integer between 1 and " + getBalance() + " as bet: ");
            }
        }
    }

    /**
     * Ask the player if he/she would like to cash out.
     *
     * @return True if cash out, false otherwise
     */
    public boolean cashOut() {

        Scanner scanner = new Scanner(System.in);
        boolean isCashOut = false;
        System.out.println("Player " + getId() + ", do you want to cash out? Please enter Y/y for yes. All other input means no.");
        String choice = scanner.nextLine();
        if (choice.equals("y") || choice.equals("Y")) {
            isCashOut = true;
        }
        return isCashOut;
    }

    /**
     * Getter for hands.
     *
     * @return the number of hands.
     */
    public int getHandCount() {
        return getHands().size();
    }

    /**
     * Get the hand.
     *
     * @param idx index of the hand.
     * @return an instance of BlackjackHand.
     */
    public BlackjackHand getHandAt(int idx) {
        return (BlackjackHand) getHands().get(idx);
    }

    private int getInteger(String str) {
        try {
            int res = Integer.parseInt(str);
            return res;
        } catch (Exception e) {
            return -1;
        }
    }
}
