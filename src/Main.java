public class Main {

    public static void main(String[] args) {
        BlackjackPlayer player1 = new BlackjackPlayer(1, 50);
        BlackjackPlayer player2 = new BlackjackPlayer(2, 100);
        BlackjackPlayer player3 = new BlackjackPlayer(3, 500);
        BlackjackPlayer player4 = new BlackjackPlayer(4, 730);

        Deck deck = new Deck();

        // test hit
        player1.hit(deck, player1.getHands(), 0);
        player1.hit(deck, player1.getHands(), 0);
        System.out.println(player1.getHandAt(0).getCardAt(0).getSuit() + " " + player1.getHandAt(0).getCardAt(0).getValue());
        System.out.println(player1.getHandAt(0).getCardAt(1).getSuit() + " " + player1.getHandAt(0).getCardAt(1).getValue());


        // test split
        player1.split(player1.getHands(), 0);
        System.out.println(player1.getHandAt(0).getCardAt(0).getSuit() + " " + player1.getHandAt(0).getCardAt(0).getValue());
        System.out.println(player1.getHandAt(1).getCardAt(0).getSuit() + " " + player1.getHandAt(1).getCardAt(0).getValue());
    }
}
