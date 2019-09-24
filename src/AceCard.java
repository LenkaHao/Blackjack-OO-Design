/**
 * Created by Jiatong Hao, Xiankang Wu and Liqun Chen on 9/23/2019.
 */

public class AceCard extends Card{
    public AceCard(String suit) {
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
