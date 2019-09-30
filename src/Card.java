/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 *
 * A generic class to represent a poker card in any card game.
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

    // returns a string with suit and value to print
    @Override
    public String toString() {
        return getSuit() + " " + getValue();
    }
}
