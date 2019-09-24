/**
 * Created by Jiatong Hao, Xiankang Wu and Liqun Chen on 9/23/2019.
 */

import java.util.*;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }

    public Hand(Card card) {
        this();
        cards.add(card);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        String result = "";
        for (Card card : cards) {
            result += card;
            result += " ";
        }
        return result;
    }
}
