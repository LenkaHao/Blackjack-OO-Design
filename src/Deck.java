/**
 * Created by Jiatong Hao, Xiankang Wu and Liqun Chen on 9/23/2019.
 */

import java.util.*;

public class Deck {
    private List<BlackjackCard> cards;
    private int cardCount;

    public Deck() {
        createDeck();
        shuffle();
    }

    private void createDeck() {
        cards = new ArrayList<BlackjackCard>();
        String[] suits = new String[]{"Spade", "Heart", "Club", "Diamond"};
        for (int i = 1; i <= 13; i++) {
            for (String suit : suits) {
                cards.add(new BlackjackCard(suit, i));
            }
        }
        cardCount = 52;
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cardCount; i++) {
            int randIdx = i + random.nextInt(cardCount - i);
            BlackjackCard temp = cards.get(randIdx);
            cards.set(randIdx, cards.get(i));
            cards.set(i, temp);
        }
    }

    public BlackjackCard dealCard() {
        if (cardCount == 0) {
            createDeck();
            shuffle();
        }
        cardCount -= 1;
        return cards.remove(0);
    }
}