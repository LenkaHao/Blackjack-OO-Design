/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
 */

import java.util.List;

public interface PlayerAction {
    /**
     * player make bet
     */
    void bet();

    /**
     * player could split into two hands, if the initial two cards are the same rank
     */
    boolean split(BlackjackHand hand);

    /**
     * player could double up their bet (they could only take one more card after)
     */
    void doubleUp();

    /**
     * the player takes one additional card
     */
    void hit(Deck deck, List<BlackjackHand> hand, int pos);

    /**
     * the player ends and maintains the value of current hand
     */
    void stand();
}
