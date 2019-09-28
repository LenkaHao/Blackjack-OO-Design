public class BlackjackJudge extends Judge{

    public BlackjackJudge() {
        super();
    }

    public boolean isActionValid(String str, BlackjackHand hand) {
        return !str.equals("split") || isSplittable(hand);
    }

    public boolean isSplittable(BlackjackHand hand) {
        // only first two cards with same game value can be split
        if (hand.getCardCount() != 2) {
            return false;
        }
        int cardValue1 = hand.getCardAt(0).getHardValue();
        int cardValue2 = hand.getCardAt(1).getHardValue();
        return cardValue1 == cardValue2;
    }

    public boolean isBusted(BlackjackHand hand) {
        return hand.getTotalValue() > 21;
    }

    private boolean isBlackjack(BlackjackHand hand) {
        return hand.getTotalValue() == 21;
    }

    private boolean isNaturalBlackjack(BlackjackHand hand) {
        if (!isBlackjack(hand)) {
            return false;
        }
        boolean hasAce = false;
        boolean hasFaceCard = false;
        for (int i = 0; i < hand.getCardCount(); i++) {
            Card card = hand.getCardAt(i);
            if (card.getValue() > 10) {
                hasFaceCard = true;
            }
            if (card.getValue() == 1) {
                hasAce = true;
            }
        }
        return hasAce && hasFaceCard;
    }
}
