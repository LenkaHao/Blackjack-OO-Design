/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.List;
import java.util.Scanner;

public class BlackjackPlayer extends Player implements PlayerAction{
    // Since there can be multiple players, each player needs an id to identify himself
    private int id;
    private int balance;
    private int bet;

    public BlackjackPlayer(int id, int balance) {
        super();
        this.id = id;
        this.balance = balance;
    }

    /**
     * Ask player to make a bet
     */
    @Override
    public void bet() {
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

    /**
     * The player could split into two hands, if the initial two cards are the same rank
     * @param hand - the hand that wants to split
     * @return true if successfully split hands, false otherwise
     */
    @Override
    public boolean split(BlackjackHand hand) {
        if (hand.isSplittable()) {
            Card card = hand.getCardAt(0);
            BlackjackHand newHand = new BlackjackHand(card);
            hand.removeCard(card);
            addHand(newHand);
            return true;
        }
        return false;
    }

    /**
     * The player double their bet on this hand
     * has error - bet needs to link to hand
     */
    @Override
    public void doubleUp() {
        bet *= 2;
    }

    /**
     * The player takes one additional card
     * @param deck - deck
     * @param hand - what we have right now
     * @param pos - which hand takes action
     */
    @Override
    public void hit(Deck deck, List<BlackjackHand> hand, int pos) {
        Card newCard = deck.dealCard();
//        Card newCard = new Card("Club", 5);
        hand.get(pos).addCard(newCard);
    }

    @Override
    public void stand() {

    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int currency) {
        balance += currency;
    }

    public int getBet() {
        return bet;
    }

    public int getHandCount() {
        return getHands().size();
    }

    public BlackjackHand getHandAt(int idx) {
        return (BlackjackHand) getHands().get(idx);
    }
}
