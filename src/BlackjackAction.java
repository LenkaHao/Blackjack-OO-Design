/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
 */

public interface BlackjackAction {

    /**
     * the player takes one additional card
     */
    void hit(BlackjackDeck deck, BlackjackHand hand);

    /**
     * Ask player to make a bet
     */
    public void bet(BlackjackPlayer player, BlackjackHand hand);

    /**
     * The player could split into two hands, if the initial two cards are the same rank
     * @param hand - the hand that wants to split
     * @return true if successfully split hands, false otherwise
     */
    public void split(BlackjackPlayer player, BlackjackHand hand);

    /**
     * The player double up their bets and immediately followed by a hit and stand
     * @param deck
     * @param hand
     */
    public void doubleUp(BlackjackDeck deck, BlackjackHand hand);

    /**
     * the player ends and maintains the value of current hand
     */
    void stand();
}