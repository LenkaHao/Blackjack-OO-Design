public class BlackjackCard extends Card {
    public BlackjackCard(String suit, int value) {
        super(suit, value);
    }

    // returns the soft value: 10 for face card, 1 for Ace
    public int getSoftValue() {
        return getValue();
    }

    // returns the hard value: 10 for face card, 11 for Ace
    public int getHardValue() {
        return getValue();
    }

}
