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
}