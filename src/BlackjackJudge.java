import java.util.HashMap;
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

        switch (action) {
            case "hit":
                return !isBusted(hand);
            case "split":
                return isSplittable(player, hand);
            case "doubleUp":
                return isEnoughBalance(player, hand.getBet());
        }
        return true;
    }

    private boolean isSplittable(BlackjackPlayer player, BlackjackHand hand) {
        // check if there is only two cards in this hand &  check if balance can afford two bets
        if (hand.getCardCount() != 2) return false;
        if (!isEnoughBalance(player, hand.getBet())) return false;
        int cardValue1 = hand.getCardAt(0).getHardValue();
        int cardValue2 = hand.getCardAt(1).getHardValue();
        return cardValue1 == cardValue2;
    }

    private boolean isEnoughBalance(BlackjackPlayer player, int bet) {
        return player.getBalance() - bet >= 0;
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

        BlackjackHand dealerHand = dealer.getHand();
        int dealerValue = dealerHand.getTotalValue();

        if (isBusted(dealerHand)) {  // if dealer bust
            for (BlackjackPlayer player : players) {
                for (int i = 0; i < player.getHandCount(); i++) {
                    BlackjackHand playerHand = player.getHandAt(i);
                    if (!isBusted(playerHand)) {  // if this player hand not bust, this hand wins
                        System.out.println("Player " + player.getId() + " wins " + player.getBalance() + "!");
                        player.setBalance(player.getBalance() + playerHand.getBet() * 2);
                    }  else {  // if this player hand bust, both player and dealer lose, balance remains the same
                        System.out.println("Both player's and dealer's hands are busted, nobody wins!");
                        player.setBalance(player.getBalance() + playerHand.getBet());
                    }
                }
            }
        } else {  // if dealer not bust
            for (BlackjackPlayer player : players) {
                for (int i = 0; i < player.getHandCount(); i++) {
                    BlackjackHand playerHand = player.getHandAt(i);
                    int value = playerHand.getTotalValue();
                    if (isBusted(playerHand)) {  // if this player hand bust, this hand loses

                    } else {  // if player hand not bust
                        if (value < dealerValue) {  // if player hand value < dealer hand value, dealer hand wins

                        } else if (value == dealerValue) {  // if they are the same value
                            if (value < winValue) {  // if neigher blackjack, nor natural blackjack, tie

                            } else if (isNaturalBlackjack(dealerHand) && isNaturalBlackjack(playerHand))
                        } else {  // if player hand value > dealer hand value, player hand wins

                        }

                    }
                        if (value > dealerValue) {
                            System.out.println("Player " + player.getId() + " wins!");
                            player.setBalance(player.getBalance() + playerHand.getBet());
                        } else if (value == dealerValue) {
                            if ()
                        }
                }
            }
        }
    }
}
