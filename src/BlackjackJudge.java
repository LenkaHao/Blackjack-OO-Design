import java.util.List;

public class BlackjackJudge extends Judge{

    private int dealerValue;

    private int winValue;

    // constructor

    public BlackjackJudge(int dealerValue, int winValue) {
        this.dealerValue = dealerValue;
        this.winValue = winValue;
    }

    // getter & setter

    public int getDealerValue() {
        return dealerValue;
    }

    public void setDealerValue(int dealerValue) {
        this.dealerValue = dealerValue;
    }

    public int getWinValue() {
        return winValue;
    }

    public void setWinValue(int winValue) {
        this.winValue = winValue;
    }

    // judge methods

    public boolean isActionValid(BlackjackPlayer player, BlackjackHand hand, String action) {
        return !action.equals("split") || isSplittable(player, hand);
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
        if (hand.getTotalValue() > 21) {
            System.out.println("Your current hand value is : " + hand.getTotalValue() + "\nYour hand is busted!");
            return true;
        }
        return false;
    }

    public boolean canDealerHit(BlackjackDealer dealer) {
        return dealer.getHand().getTotalValue() < dealerValue;
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

    public void whoWins(List<BlackjackPlayer> players, BlackjackDealer dealer) {
        int maxHand = 0;
        for (BlackjackPlayer player : players) {
            for (int i = 0; i < player.getHandCount(); i++) {
                int value = player.getHandAt(i).getTotalValue();
                if (value > maxHand) maxHand = value;
            }
        }
    }
}
