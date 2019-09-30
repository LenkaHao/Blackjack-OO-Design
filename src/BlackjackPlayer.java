/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 *
 * A class that represents a  player in a Blackjack game
 */

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

    public int getHandCount() {
        return getHands().size();
    }

    /**
     * Get a hand using its index from players' all hands.
     * @param idx index of the hand.
     * @return an instance of BlackjackHand.
     */
    public BlackjackHand getHandAt(int idx) {
        return (BlackjackHand) getHands().get(idx);
    }
}
