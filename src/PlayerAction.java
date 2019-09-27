public interface PlayerAction {

    /**
     * player make bet
     */
    void bet(int currency);

    /**
     * player could split into two hands, if the initial two cards are the same rank
     */
    void split();

    /**
     * player could double up their bet (they could only take one more card after)
     */
    void doubleUp();

    /**
     * the player takes one additional card
     */
    void hit();

    /**
     * the player ends and maintains the value of current hand
     */
    void stand();
}
