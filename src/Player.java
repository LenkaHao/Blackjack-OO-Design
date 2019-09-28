/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.
 */

import java.util.*;

public class Player<E extends Hand> {

    private int id;

    private List<E> hands;

    public Player(){
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

    public void addHand(E hand) {
        hands.add(hand);
    }
}