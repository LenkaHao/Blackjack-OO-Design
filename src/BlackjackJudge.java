import java.util.ArrayList;
import java.util.List;

public class BlackjackJudge extends Judge<BlackjackPlayer, BlackjackDealer> {

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

    public void checkDealerStatus(BlackjackDealer dealer) {
        BlackjackHand dealerHand = dealer.getHand();
        int dealerHandVal = dealer.getHand().getTotalValue();
        if (isNaturalBlackjack(dealerHand)) {
            System.out.println("Dealer has a natural BlackJack!");
        } else if (isBlackjack(dealerHand)) {
            System.out.println("Dealer has a BlackJack!");
        } else {
            System.out.println("Dealer has a total card value = " + dealerHandVal);
            if (dealerHandVal >= this.dealerValue) {
                if (!isBust(dealerHand)) {
                    System.out.println("Dealer hand has value larger than " + this.dealerValue);
                }
            }
        }
    }

    public boolean isActionValid(BlackjackPlayer player, BlackjackHand hand, String action) {

        switch (action) {
            case "hit":
                return !isBust(hand);
            case "split":
                return isSplittable(player, hand);
            case "doubleUp":
                return isEnoughBalance(player, hand.getBet());
        }
        return true;
    }

    private boolean isEnoughBalance(BlackjackPlayer player, int bet) {
        return player.getBalance() - bet >= 0;
    }

    public boolean isBust(BlackjackHand hand) {
        if (hand.getTotalValue() > this.winValue) {
            return true;
        }
        return false;
    }

    private boolean isSplittable(BlackjackPlayer player, BlackjackHand hand) {
        // check if there is only two cards in this hand &  check if balance can afford two bets
        if (hand.getCardCount() != 2) return false;
        if (!isEnoughBalance(player, hand.getBet())) return false;
        int cardValue1 = hand.getCardAt(0).getHardValue();
        int cardValue2 = hand.getCardAt(1).getHardValue();
        return cardValue1 == cardValue2;
    }

    public boolean canDealerHit(BlackjackDealer dealer) {
        return dealer.getHand().getTotalValue() < dealerValue;
    }

    /**
     * In default a Blackjack has a total value of 21 (default winValue).
     * We allow our user to change the winValue.
     *
     * @param hand
     * @return if the current hand is Blackjack.
     */
    public boolean isBlackjack(BlackjackHand hand) {
        return hand.getTotalValue() == this.winValue;
    }

    public boolean isNaturalBlackjack(BlackjackHand hand) {
        if (hand.getCardCount() != 2) {
            return false;
        }
        boolean hasAce = false;
        boolean hasFaceCard = false;
        for (int i = 0; i < 2; i++) {
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

    public int checkWinner(BlackjackPlayer player, BlackjackDealer dealer) {
        BlackjackHand dealerHand = dealer.getHand();
        int dealerValue = dealerHand.getTotalValue();

        int roundBalance = 0;

        if (isBust(dealerHand)) {
            // if dealer is bust
            for (int i = 0; i < player.getHandCount(); i++) {
                BlackjackHand playerHand = player.getHandAt(i);
                int bet = playerHand.getBet();

                if (!isBust(playerHand)) {
                    // if not bust, player hand wins
                    player.setBalance(bet * 2);
                    roundBalance += playerHand.getBet();
                } else {
                    // if this player hand bust, both player and dealer lose, tie
                    player.setBalance(bet);
                }
            }
        } else {
            // if dealer does not bust
            for (int i = 0; i < player.getHandCount(); i++) {
                BlackjackHand playerHand = player.getHandAt(i);
                int value = playerHand.getTotalValue();
                int bet = playerHand.getBet();

                if (isBust(playerHand)) {
                    // if player hand bust, player hand loses
                    roundBalance -= bet;
                } else {
                    // if player hand not bust
                    if (value < dealerValue) {
                        // if player hand value < dealer hand value, player hand loses
                        roundBalance -= bet;
                    } else if (value == dealerValue) {
                        if (isNaturalBlackjack(dealerHand) && isNaturalBlackjack(playerHand)) {
                            // both dealer hand & player hand is natural blackjack, tie
                            player.setBalance(bet);
                        } else if (isNaturalBlackjack(dealerHand) && !isBlackjack(playerHand)) {
                            // dealer hand == natural blackjack && player hand == blackjack, player hand loses
                            roundBalance -= bet;
                        } else if (isBlackjack(dealerHand) && isNaturalBlackjack(playerHand)) {
                            // dealer hand == blackjack && player hand == natural blackjack, player hand wins
                            player.setBalance(bet * 2);
                            roundBalance += bet;
                        } else {
                            // both blackjack or neither blackjack, nor natural blackjack, tie
                            player.setBalance(bet);
                        }
                    } else {
                        // if player hand value > dealer hand value, player hand wins
                        player.setBalance(bet * 2);
                        roundBalance += bet;
                    }
                }
            }
        }
        return roundBalance;
    }

//    public void checkWinner(List<BlackjackPlayer> players, BlackjackDealer dealer) {
//
//        List<Integer> res = new ArrayList<>();
//        int[] ans = new int[players.size()];
//
//        BlackjackHand dealerHand = dealer.getHand();
//        int dealerValue = dealerHand.getTotalValue();
//
//        if (isBust(dealerHand)) {
//            // if dealer is bust
//            for (BlackjackPlayer player : players) {
//                int roundBalance = 0;
//                for (int i = 0; i < player.getHandCount(); i++) {
//                    BlackjackHand playerHand = player.getHandAt(i);
//                    int bet = playerHand.getBet();
//
//                    if (!isBust(playerHand)) {
//                        // if not bust, player hand wins
//                        player.setBalance(bet * 2);
//                        roundBalance += playerHand.getBet();
//                    } else {
//                        // if this player hand bust, both player and dealer lose, tie
//                        player.setBalance(bet);
//                    }
//                }
//                res.add(roundBalance);
//                ans[player.getId()] = roundBalance;
//            }
//        } else {
//            // if dealer does not bust
//            for (BlackjackPlayer player : players) {
//                int roundBalance = 0;
//                for (int i = 0; i < player.getHandCount(); i++) {
//                    BlackjackHand playerHand = player.getHandAt(i);
//                    int value = playerHand.getTotalValue();
//                    int bet = playerHand.getBet();
//
//                    if (isBust(playerHand)) {
//                        // if player hand bust, player hand loses
//                        roundBalance -= bet;
//                    } else {
//                        // if player hand not bust
//                        if (value < dealerValue) {
//                            // if player hand value < dealer hand value, player hand loses
//                            roundBalance -= bet;
//                        } else if (value == dealerValue) {
//                            if (isNaturalBlackjack(dealerHand) && isNaturalBlackjack(playerHand)) {
//                                // both dealer hand & player hand is natural blackjack, tie
//                                player.setBalance(bet);
//                            } else if (isNaturalBlackjack(dealerHand) && !isBlackjack(playerHand)) {
//                                // dealer hand == natural blackjack && player hand == blackjack, player hand loses
//                                roundBalance -= bet;
//                            } else if (isBlackjack(dealerHand) && isNaturalBlackjack(playerHand)) {
//                                // dealer hand == blackjack && player hand == natural blackjack, player hand wins
//                                player.setBalance(bet * 2);
//                                roundBalance += bet;
//                            } else {
//                                // both blackjack or neither blackjack, nor natural blackjack, tie
//                                player.setBalance(bet);
//                            }
//                        } else {
//                            // if player hand value > dealer hand value, player hand wins
//                            player.setBalance(bet * 2);
//                            roundBalance += bet;
//                        }
//                    }
//                }
//                res.add(roundBalance);
//                ans[player.getId()] = roundBalance;
//            }
//        }
//        return res;
//    }
}