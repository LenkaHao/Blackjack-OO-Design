/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
 */

import java.util.List;

public interface PlayerAction {

    /**
     * the player takes one additional card
     */
    void hit(BlackjackDeck deck, BlackjackHand hand);

    /**
     * the player ends and maintains the value of current hand
     */
    void stand();
}
