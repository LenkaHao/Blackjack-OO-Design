/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.*;

public class Hand<T extends Card> {
    private List<T> cards;

    public Hand() {
        cards = new ArrayList<T>();
    }

    public Hand(T card) {
        cards = new ArrayList<T>();
        cards.add(card);
    }

    public List<T> getHand() {
        return cards;
    }

    public void setHand(Hand<T> hand) {
        this.cards = new ArrayList<>();
        this.cards.add(hand.getCardAt(0));
    }

    public void clearHand() {
        cards.clear();
    }

    public void addCard(T card) {
        cards.add(card);
    }

    public void removeCard(T card) {
        cards.remove(card);
    }

    public int getCardCount() {
        return cards.size();
    }

    public T getCardAt(int idx) {
        return cards.get(idx);
    }

    @Override
    public String toString() {
        String result = "";
        for (T card : cards) {
            result = result + card + "\n";
        }
        return result;
    }
}
