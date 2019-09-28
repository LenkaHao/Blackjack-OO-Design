/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.*;

public abstract class Deck {
    private int cardCount;

    public Deck() {
        cardCount = 52;
        createDeck();
        shuffle();
    }

    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int num) {
        cardCount += num;
    }

    abstract void createDeck();

    abstract void shuffle();

    abstract <T extends Card> T dealCard();
}
