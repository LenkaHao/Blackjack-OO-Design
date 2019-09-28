import java.util.*;

public class BlackjackDeck extends Deck {
    private List<BlackjackCard> cards;

    public void createDeck() {
        cards = new ArrayList<BlackjackCard>();
        String[] suits = new String[]{"Spade", "Heart", "Club", "Diamond"};
        for (String suit : suits) {
            cards.add(new BlackjackAceCard(suit));
            for (int i = 2; i <= 10; i++) {
                cards.add(new BlackjackCard(suit, i));
            }
            for (int i = 11; i <= 13; i++) {
                cards.add(new BlackjackFaceCard(suit, i));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public BlackjackCard dealCard() {
        if (getCardCount() == 0) {
            createDeck();
            shuffle();
            setCardCount(52);
        }
        setCardCount(-1);
        return cards.remove(0);
    }
}
