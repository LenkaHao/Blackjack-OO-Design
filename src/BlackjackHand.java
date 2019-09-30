/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
 *
 * A class to represent a hand of Blackjack cards in a Blackjack game
 */

public class BlackjackHand extends Hand<BlackjackCard> {

    private int bet;

    public BlackjackHand() {
        super();
    }

    public BlackjackHand(BlackjackCard card) {
        super(card);
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int newBet) {
        bet = newBet;
    }

    /**
     * Compute the total value of cards in a Blackjack game.
     * @return total value of cards within the current hand.
     */
    public int getTotalValue() {
        int value = 0;
        int aceCount = 0;
        int cardCount = getCardCount();

        for (int i = 0; i < cardCount; i++) {
            // Add the value of each card in the hand
            BlackjackCard card = (BlackjackCard) getCardAt(i);
            int cardSoftValue = card.getSoftValue();
            value += cardSoftValue;

            if (card.getHardValue() == 11) {
                aceCount += 1;
            }
        }

        // If a hand has Ace cards, use its hard value as long as the hand is not busted
        while (aceCount > 0 && value + 10 <= 21) {
            value += 10;
            aceCount -= 1;
        }

        return value;
    }
}
