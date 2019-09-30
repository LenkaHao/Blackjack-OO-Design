/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 *
 * A generic class that represent a player in a card game
 */

import java.util.ArrayList;
import java.util.List;

public class Player<E extends Hand> {

    private int id;

    private List<E> hands;

    public Player() {
        hands = new ArrayList<E>();
    }

    public Player(int id) {
        this.id = id;
        hands = new ArrayList<E>();
    }

    public int getId() {
        return id;
    }

    public List<E> getHands() {
        return hands;
    }

    public void clearHands() {
        hands = new ArrayList<E>();
    }

    public void addHand(E hand) {
        hands.add(hand);
    }
}