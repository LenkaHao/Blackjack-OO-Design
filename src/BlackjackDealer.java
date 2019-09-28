/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

public class BlackjackDealer extends Player implements PlayerAction<BlackjackDeck, BlackjackHand>{

    public BlackjackHand getHand() {
        return (BlackjackHand) getHands().get(0);
    }

    // implement PlayerAction

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

    @Override
    public void stand() {
        return;
    }
}
