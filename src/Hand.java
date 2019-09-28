/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.*;

public class Hand<T extends Card> {
    private List<T> hand;

    public Hand() {
        hand = new ArrayList<T>();
    }

    public Hand(T card) {
        hand = new ArrayList<T>();
        hand.add(card);
    }

    public List<T> getHand() {
        return hand;
    }

    public void setHand(Hand<T> hand) {
        this.hand = new ArrayList<>();
        this.hand.add(hand.getCardAt(0));
    }

    public void clearHand() {
        hand.clear();
    }

    public void addCard(T card) {
        hand.add(card);
    }

    public void removeCard(T card) {
        hand.remove(card);
    }

    public int getCardCount() {
        return hand.size();
    }

    public T getCardAt(int idx) {
        return hand.get(idx);
    }

    @Override
    public String toString() {
        String result = "";
        for (T card : hand) {
            result = result + card + "\n";
        }
        return result;
    }
}
