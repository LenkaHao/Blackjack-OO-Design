public class BlackjackJudge extends Judge{

    public BlackjackJudge() {
        super();
    }

    public boolean isActionValid(String str, BlackjackHand hand) {
        if (str.equals("hit")) {
            return isBusted(hand);
        } else if (str.equals("split")) {
            return isSplittable(hand);
        } else if(str.equals("doubleUp")) {
            return isBusted(hand);
        } else if (str.equals("stand")) {
            return true;
        } else {
            System.out.println("Not valid action");
            return false;
        }
    }

    public boolean isSplittable(BlackjackHand hand) {
        // only first two cards with same game value can be split
        if (hand.getCardCount() != 2) {
            return false;
        }
        int cardValue1 = hand.getCardAt(0).getHardValue();
        int cardValue2 = hand.getCardAt(1).getHardValue();
        if (cardValue1 == cardValue2) {
            return true;
        }
        return false;
    }

    private boolean isBusted(BlackjackHand hand) {
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
