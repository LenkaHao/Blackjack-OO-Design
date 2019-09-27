public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome, PlayerAction.");

        Hand myHand = new Hand(new BlackjackCard("Spade", 1), new BlackjackCard("Heart", 1));
        System.out.println(myHand.getScore());
    }
}