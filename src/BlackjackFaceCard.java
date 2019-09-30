/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 *
 * A class to represent a face card in a Blackjack game.
 */

public class BlackjackFaceCard extends BlackjackCard {
    public BlackjackFaceCard(String suit, int value) {
        super(suit, value);
    }

    @Override
    public int getSoftValue() {
        return 10;
    }

    @Override
    public int getHardValue() {
        return 10;
    }

    @Override
    public String toString() {
        String rank;
        switch (getValue()) {
            case 11:
                rank = "J";
                break;
            case 12:
                rank = "Q";
                break;
            default:
                rank = "K";
        }
        return getSuit() + " " + rank;
    }
}
