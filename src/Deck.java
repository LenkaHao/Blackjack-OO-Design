/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.*;

public abstract class Deck<E extends Card> {

    private List<E> cards;

    private int cardCount;

    public Deck() {
        cardCount = 52;
        createDeck();
        shuffle();
    }

    public List<E> getCards() {
        return cards;
    }

    public void setCards(List<E> cards) {
        this.cards = cards;
    }

    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int num) {
        cardCount += num;
    }

    abstract void createDeck();

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public E dealCard() {
        if (getCardCount() == 0) {
            createDeck();
            shuffle();
            setCardCount(52);
        }
        setCardCount(-1);
        return cards.remove(0);
    }
}
