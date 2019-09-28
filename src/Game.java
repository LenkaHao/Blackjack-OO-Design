import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<BlackjackPlayer> playerList;
    private BlackjackDealer dealer;
    private BlackjackDeck deck;
    private BlackjackJudge judge;
    private GameVisualizer visualizer;
    private int round;
    private int winVal = 21;
    private int dealerVal = 17;
    private static String[] actions = {"hit", "stand", "doubleUp", "split"};

    // constructor
    Game() {
        setGameParams();
        initGame();
    }

    private void setGameParams() {
        /**
         * Initialize black
         */
        /
        System.out.println("Welcome to our game!");
        System.out.println("Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.");
        System.out.println("Do you want to change default values? \n e.g. 21 is the default blackjack target value, " +
                "you can change it to an integer between 22 to 30");
        Scanner sc = new Scanner(System.in);

        String choice;
        choice = sc.nextLine();
        if (!choice.equals("y") && !choice.equals("Y"))
            return;

        System.out.println("Please input the new blackjack win value. Otherwise it is 21 in default");
        int winVal;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Your input must be an integer between 22 to 30!");
                sc.next();
            }
            winVal = sc.nextInt();
            if (winVal > 30)
                System.out.println("Your input is too large!");
        } while (winVal < 21);
        this.winVal = winVal;


        System.out.println("Please input the new dealer value. Otherwise it is 17 in default");
        int dealerVal;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Your input must be an integer between 22 to 30!");
                sc.next();
            }
            dealerVal = sc.nextInt();
            if (dealerVal > winVal - 4)
                System.out.println("Your input is too large!");
        } while (dealerVal < 21);
        this.dealerVal = dealerVal;

    }

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

        dealer = new BlackjackDealer();
        judge = new BlackjackJudge(dealer_val, win_val);
        deck = new BlackjackDeck();
        visualizer = new GameVisualizer(playerList);
        deck.shuffle();
        round = 1;
        playerList = new ArrayList<BlackjackPlayer>(playerCount);
        for (int id = 0; id < playerCount; id++)
            playerList.add(new BlackjackPlayer(id, 100)); // balance can be specified by the input later
    }

    private String getUserAction() {
        // Read User input
        Scanner sc = new Scanner(System.in);
        int action_idx;
        do {
            System.out.println("Please select your next action with its corresponding number (e.g., 0):");
            int idx = 0;
            for (String action : actions) {
                System.out.println(action + ": " + idx++ + "\n");
            }
            action_idx = sc.nextInt();
            if (action_idx < 0 || action_idx >= actions.length) {
                System.out.println("Invalid input!");
            } else
                break;
        } while (true);
        return actions[action_idx];
    }

    private boolean playAction(BlackjackPlayer player, String action, BlackjackHand hand) {
        switch (action) {
            case "hit":
                player.hit(deck, hand);
                break;
            case "stand":
                player.stand();
                break;
            case "doubleUp":
                player.doubleUp(deck, hand);
                judge.isBusted(hand);
                break;
            case "split":
                player.split(hand);
                break;
        }
        return true;
    }

    private boolean isNextRound() {
        System.out.println("Do you wish to start a new round? \n Input y or Y to continue, otherwise exit.");
        Scanner sc = new Scanner(System.in);
        String choice;
        choice = sc.nextLine();
        return !choice.equals("y") && !choice.equals("Y");
    }

    public void start() {
        // Players make their bets
        for (BlackjackPlayer player : playerList) {
            player.bet();
        }
        boolean isGameEnd = false;
        while (!isGameEnd) {
            System.out.println("Current Round: " + round);
            // Player
            for (BlackjackPlayer player : playerList) {
                ArrayList<BlackjackHand> hands = player.getHands();
                for (BlackjackHand hand : hands) {
                    boolean isValid;
                    String next_action;
                    do {
                        System.out.println(hand);
                        next_action = getUserAction();
                        isValid = judge.isActionValid(player, hand, next_action);
                        if (!isValid)
                            System.out.println("Your input action is not valid. Please try again.");
                    } while (!isValid);
                    if (playAction(player, next_action, hand))
                        break;
                }
            }
            // Dealer
            while (!judge.canDealerHit(dealer)) {
                dealer.hit();
            }
            isGameEnd = isNextRound();
            round++;
            visualizer.display(playerList);
        }
        // Display statistics for all players.
    }


    public static void main(String args[]) {
        Game game = new Game();
        game.start();
    }
}