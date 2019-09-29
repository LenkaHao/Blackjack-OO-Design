/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.ArrayList;

public class BlackjackDealer extends Player {

    BlackjackDealer(BlackjackDeck deck) {
        super();
        addHand(new BlackjackHand());
    }

    /**
     * Get the face-up card in dealer hand. In default it returns the second one.
     * First one is treated as face-down.
     *
     * @return
     */
    public BlackjackCard getVisibleCard() {
        return ((BlackjackHand)getHands().get(0)).getCardAt(1);
    }

    public BlackjackHand getHand() {
        return (BlackjackHand) getHands().get(0);
    }
}
