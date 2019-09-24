/**
 * Created by Jiatong Hao, Xiankang Wu and Liqun Chen on 9/23/2019.
 */

public class Card {
    private String suit;
    private int value;

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    // returns the soft value: 10 for face card, 1 for Ace
    public int getSoftValue() {
        return getValue();
    }

    // returns the hard value: 10 for face card, 11 for Ace
    public int getHardValue() {
        return getValue();
    }

    // returns a string with suit and value to print
    @Override
    public String toString() {
        return getSuit() + " " + getValue();
    }
}
