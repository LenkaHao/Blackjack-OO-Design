/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.Scanner;

public class BlackjackPlayer extends Player implements PlayerAction {

    private int balance;

    // Constructor
    public BlackjackPlayer(int id, int balance) {
        super(id);
        this.balance = balance;
        addHand(new BlackjackHand());
    }

    // implement PlayerAction

    /**
     * The player takes one additional card
     * @param deck - deck
     * @param hand - what we have right now
     */
    @Override
    public void hit(BlackjackDeck deck, BlackjackHand hand) {
        BlackjackCard newCard = (BlackjackCard) deck.dealCard();
        hand.addCard(newCard);
    }

    @Override
    public void stand() {
        return;
    }

    // implement specific player action

    /**
     * Ask player to make a bet
     */
    public void bet(BlackjackHand hand) {
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
        hand.setBet(playerBet);
    }

    /**
     * The player could split into two hands, if the initial two cards are the same rank
     * @param hand - the hand that wants to split
     * @return true if successfully split hands, false otherwise
     */
    public void split(BlackjackHand hand) {
        BlackjackCard card = (BlackjackCard) hand.getCardAt(0);
        BlackjackHand newHand = new BlackjackHand(card);
        hand.removeCard(card);
        addHand(newHand);
    }

    /**
     * The player double up their bets and immediately followed by a hit and stand
     * @param deck
     * @param hand
     */
    public void doubleUp(BlackjackDeck deck, BlackjackHand hand) {
        hand.setBet(hand.getBet() * 2);
        hit(deck, hand);
        stand();
    }

    // getter & setter

    public int getBalance() {
        return balance;
    }

    public void setBalance(int currency) {
        balance += currency;
    }

    public int getHandCount() {
        return getHands().size();
    }

    public BlackjackHand getHandAt(int idx) {
        return (BlackjackHand) getHands().get(idx);
    }
}
