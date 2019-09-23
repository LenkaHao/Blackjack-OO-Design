/**
 * Created by Jiatong Hao, Xiankang Wu and Liqun Chen on 9/23/2019.
 */

public class BlackjackCard extends Card{
    private int gameValue;

    public BlackjackCard(String suit, int value) {
        super(suit, value);
        if (value > 10) {
            this.gameValue = 10;
        } else {
            this.gameValue = value;
        }
    }

    public int getGameValue() {
        return gameValue;
    }
}
