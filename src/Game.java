import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<BlackjackPlayer> playerList;
    private BlackjackDealer dealer;
    private Deck deck;
    private BlackjackJudge judge;
    private static String[] actions = {"hit", "stand", "doubleUp", "split"};

    private void initGame() {
        // Read User input
        Scanner sc = new Scanner(System.in);
        int playerCount;
        do {
            System.out.println("\n*****************\n");
            System.out.println("Please tell us how many Player will join the game.");
            while (!sc.hasNextInt()) {
                System.out.println("Input must be an integer larger or equal to 1!");
                sc.next();
            }
            playerCount = sc.nextInt();
        } while (playerCount == 0);

        playerList = new ArrayList<BlackjackPlayer>(playerCount);
        for (int id = 0; id < playerCount; id++)
            playerList.add(new BlackjackPlayer(id, 100)); // balance can be specified by the input later
        dealer = new BlackjackDealer();
        deck = new Deck();
        deck.shuffle();
    }

    private void getUserAction() {
        // Read User input
        Scanner sc = new Scanner(System.in);
        String next_action;
        boolean isValidAction = false;
        do {
            System.out.println("\n*****************\n");
            System.out.println("Please select your action:");
            int idx = 0;
            for (String action : actions) {
                System.out.println(action + ": " + idx++ + "\n");
            }
            next_action = sc.nextLine();
            for (String action : actions) {
                if (action.equals(next_action)) {
                    isValidAction = true;
                    break;
                }
            }
        } while (!isValidAction);
    }

    private void playAction(BlackjackPlayer player, String action, BlackjackHand hand) {
        switch (action) {
            case "hit":
                player.hit(deck, hand, pos);
                break;
            case "stand":
                player.stand();
                break;
            case "doubleUp":
                player.doubleUp();
                break;
            case "split":
                player.split(hand);
                break;
        }
    }

    public Game() {
        initGame();
    }

    public void start() {
        // Players make their bets
        for (BlackjackPlayer player : playerList) {
            player.bet();
        }
        while (true) {
            for (BlackjackPlayer player : playerList) {
                ArrayList<Hand> hands = player.getHands();
                for (Hand hand : hands) {
                    System.out.println(hand);
                    string action = getUserAction(hand);
                    playAction(action, hand);
                    if (action.equals("stand")) {
                        break;
                    }
                }
            }

        }
    }

    public static void main(String args[]) {
        Game game = new Game();
        game.start();
    }
}