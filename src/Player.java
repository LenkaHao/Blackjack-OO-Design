/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */
import java.util.*;

public class Player {
    private List<BlackjackHand> hands;

    public Player() {
        hands = new ArrayList<BlackjackHand>();
        BlackjackHand hand = new BlackjackHand();
        hands.add(hand);
    }

    public List<BlackjackHand> getHands() {
        return hands;
    }
}
