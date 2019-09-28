public class BlackjackJudge extends Judge{

    private int dealerThreshold;

    public BlackjackJudge(int threshold) {
        dealerThreshold = threshold;
    }

    public boolean isActionValid(BlackjackPlayer player, BlackjackHand hand, String str) {
        return !str.equals("split") || isSplittable(player, hand);
    }

    private boolean isSplittable(BlackjackPlayer player, BlackjackHand hand) {
        // check if there is only two cards in this hand &  check if balance can afford two bets
        if (hand.getCardCount() != 2) return false;
        if (player.getBalance() < hand.getBet() * 2) return false;
        int cardValue1 = hand.getCardAt(0).getHardValue();
        int cardValue2 = hand.getCardAt(1).getHardValue();
        return cardValue1 == cardValue2;
    }

    public boolean isBusted(BlackjackHand hand) {
        return hand.getTotalValue() > 21;
    }

    public boolean isBlackjack(BlackjackHand hand) {
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

//    public void whoWins(List<BlackjackPlayer> players, BlackjackDealer dealer) {
//        int maxHand = 0;
//        for (BlackjackPlayer player : players) {
//            for (BlackjackHand hand : player.)
//        }
//    }

    public int getDealerThreshold() {
        return dealerThreshold;
    }

    public void setDealerThreshold(int dealerThreshold) {
        this.dealerThreshold = dealerThreshold;
    }
}
