/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.*;

public class Deck {
    private List<Card> cards;
    private int cardCount;

    public Deck() {
        createDeck();
        shuffle();
    }

    private void createDeck() {
        cards = new ArrayList<Card>();
        String[] suits = new String[]{"Spade", "Heart", "Club", "Diamond"};
        for (String suit : suits) {
            cards.add(new AceCard(suit));
            for (int i = 2; i <= 10; i++) {
                cards.add(new Card(suit, i));
            }
            for (int i = 11; i <= 13; i++) {
                cards.add(new FaceCard(suit, i));
            }
        }
        cardCount = 52;
    }

    private void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cardCount; i++) {
            int randIdx = i + random.nextInt(cardCount - i);
            Card temp = cards.get(randIdx);
            cards.set(randIdx, cards.get(i));
            cards.set(i, temp);
        }
    }

    public Card dealCard() {
        if (cardCount == 0) {
            createDeck();
            shuffle();
        }
        cardCount -= 1;
        return cards.remove(0);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
