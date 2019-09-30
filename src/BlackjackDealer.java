/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

public class BlackjackDealer extends Player {

    BlackjackDealer() {
        super();
        addHand(new BlackjackHand());
    }

    /**
     * Get the face-up card in dealer hand. In default it returns the second one.
     * First one is treated as face-down.
     *
     * @return the card that is face-down.
     */
    public BlackjackCard getVisibleCard() {
        return ((BlackjackHand)getHands().get(0)).getCardAt(1);
    }

    /**
     * Get the first hand within hands.
     * @return a hand instance
     */
    public BlackjackHand getHand() {
        return (BlackjackHand) getHands().get(0);
    }
}
