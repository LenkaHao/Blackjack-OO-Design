/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 *
 * A class to represent an Ace card in a Blackjack game.
 */

public class BlackjackAceCard extends BlackjackCard {
    public BlackjackAceCard(String suit, int value) {
        super(suit, value);
    }

    public BlackjackAceCard(String suit) {
        super(suit, 1);
    }

    @Override
    public int getSoftValue() {
        return 1;
    }

    @Override
    public int getHardValue() {
        return 11;
    }

    @Override
    public String toString() {
        return getSuit() + " A";
    }
}
