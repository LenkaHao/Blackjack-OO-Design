/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
 */

public interface PlayerAction<D, H> {

    /**
     * the player takes one additional card
     */
    void hit(D deck, H hand);

    /**
     * the player ends and maintains the value of current hand
     */
    void stand();
}
