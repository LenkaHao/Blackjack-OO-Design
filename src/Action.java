import java.util.Scanner;

public class Action implements BlackjackAction {

    /**
     * The player takes one additional card
     * @param deck - deck
     * @param hand - what we have right now
     */
    @Override
    public void hit(BlackjackDeck deck, BlackjackHand hand) {
        BlackjackCard newCard = (BlackjackCard) deck.dealCard();
        hand.addCard(newCard);
    }

    /**
     * Ask player to make a bet
     */
    @Override
    public void bet(BlackjackPlayer player, BlackjackHand hand) {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int playerBet = 0;
        while (!isValid) {
            System.out.println("Current balance of player " + player.getId() + " is: " + player.getBalance());
            System.out.println("Please enter an integer between 1 and your balance as bet: ");
            playerBet = scanner.nextInt();
            if (playerBet >= 1 && playerBet <= player.getBalance()) {
                isValid = true;
            } else {
                System.out.println("Please enter a valid bet.");
            }
        }
        hand.setBet(playerBet);
    }

    /**
     * The player could split into two hands, if the initial two cards are the same rank
     * @param hand - the hand that wants to split
     * @return true if successfully split hands, false otherwise
     */
    @Override
    public void split(BlackjackPlayer player, BlackjackHand hand) {
        BlackjackCard card = (BlackjackCard) hand.getCardAt(0);
        BlackjackHand newHand = new BlackjackHand(card);
        hand.removeCard(card);
        player.addHand(newHand);
    }

    /**
     * The player double up their bets and immediately followed by a hit and stand
     * @param deck
     * @param hand
     */
    @Override
    public void doubleUp(BlackjackDeck deck, BlackjackHand hand) {
        hand.setBet(hand.getBet() * 2);
        hit(deck, hand);
    }

    @Override
    public void stand() {
        return;
    }
}
