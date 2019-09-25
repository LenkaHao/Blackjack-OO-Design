/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.*;

public class Hand {
    private List<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getCardCount() {
        return hand.size();
    }

    public Card getCardAt(int idx) {
        return hand.get(idx);
    }

    @Override
    public String toString() {
        String result = "";
        for (Card card : hand) {
            result = result + card + "\n";
        }
        return result;
    }
}
