/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
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

    public boolean isBusted() {
        return getTotalValue() > 21;
    }

    public boolean isBlackjack() {
        return getTotalValue() == 21;
    }

    public boolean isNaturalBlackjack() {
        if (!isBlackjack()) {
            return false;
        }
        boolean hasAce = false;
        boolean hasFaceCard = false;
        for (int i = 0; i < getCardCount(); i++) {
            Card card = getCardAt(i);
            if (card.getValue() > 10) {
                hasFaceCard = true;
            }
            if (card.getValue() == 1) {
                hasAce = true;
            }
        }
        return hasAce && hasFaceCard;
    }

    public boolean isSplittable() {
        // only first two cards with same game value can be split
        if (getCardCount() != 2) {
            return false;
        }
        int cardValue1 = ((BlackjackCard)getCardAt(0)).getHardValue();
        int cardValue2 = ((BlackjackCard)getCardAt(1)).getHardValue();
        if (cardValue1 == cardValue2) {
            return true;
        }
        return false;
    }
}
