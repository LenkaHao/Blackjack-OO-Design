/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.*;

public class Player {
    private List<Hand> hands;

    public Player() {
        hands = new ArrayList<Hand>();
        Hand hand = new Hand();
        hands.add(hand);
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void addHand(Hand hand) {
        hands.add(hand);
    }

    public void setHands(Hand hand, int position) {
        hands.get(position).setHand(hand);
    }
}
