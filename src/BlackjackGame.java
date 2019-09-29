import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame extends Game implements BlackjackAction {
    private List<BlackjackPlayer> playerList;
    private BlackjackDealer dealer;
    private BlackjackDeck deck;
    private BlackjackJudge judge;
    private GameVisualizer visualizer;
    private int winVal = 21;
    private int dealerVal = 17;
    private int playerCount;
    private final int WIN_VAL_DIFF = 4;
    private final int MAX_PLAYER = 3;
    private final int BALANCE = 100;
    private static String[] actions = {"hit", "stand", "doubleUp", "split"};

    // constructor
    BlackjackGame() {
        super(0);
        setGameParams();
        initGame();
    }

    public void start() {
        // Players make their bets
        for (BlackjackPlayer player : playerList) {
            player.makeBet();
        }
        System.out.println("\nGame starts!");
        boolean isGameEnd = false;
        while (!isGameEnd) {
            System.out.println("\n*****************\nRound: " + getRound());
            // Players and the dealer get initial cards
            dealCards();

            for (BlackjackPlayer player : playerList) {
                List<BlackjackHand> hands = player.getHands();
                int handIdx = 0;
                for (BlackjackHand hand : hands) {
                    System.out.println("Player: " + player.getId() + ", Hand: " + handIdx++);
                    System.out.println("Dealer face-up card: " + dealer.getVisibleCard());

                    while (!judge.isBusted(hand)) {
                        boolean isValid;
                        String next_action;
                        do {
                            System.out.println(hand);
                            next_action = getUserAction();
                            isValid = judge.isActionValid(player, hand, next_action);
                            if (!isValid)
                                System.out.println("Your input action is not valid. Please try again.");
                        } while (!isValid);
                        playAction(player, next_action, hand);
                        if (next_action.equals("stand") || next_action.equals("doubleUp")) {
                            break;
                        }
                    }
                }
            }
            // Dealer
            while (judge.canDealerHit(dealer)) {
                hit(deck, dealer.getHand());
            }
            isGameEnd = isNextRound();
            nextRound();
            visualizer.display(playerList);
        }
        // Display statistics for all players.
    }

    /**
     * Initialize game params of BlackJack Game.
     */
    private void setGameParams() {

        System.out.println("Welcome to our game!");
        System.out.println("Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.\n");
        Scanner sc = new Scanner(System.in);

        // Specify number of players
        int playerCount = 0;
        System.out.println("\n*****************\n");
        do {
            System.out.println("Please tell us how many players will join the game:");
            while (!sc.hasNextInt()) {
                System.out.println("Input must be an integer larger or equal to 1!");
                sc.next();
            }
            playerCount = sc.nextInt();
            if (playerCount > MAX_PLAYER) {
                System.out.println("Maximum number of players: " + MAX_PLAYER + ". Your input is too large.");
            } else {
                this.playerCount = playerCount;
            }
        } while (this.playerCount == 0);

        System.out.println("Before we start, do you want to change default values?\ne.g. 21 is the default blackjack " +
                "target value, you can change it to an integer.");
        System.out.println("Enter y or Y to change, otherwise to skip.");

        String choice;
        sc.nextLine(); // Read the input buffer and discard it.
        choice = sc.nextLine();
        if (!choice.equals("y") && !choice.equals("Y")) {
            System.out.println("Next step: Please enter your bet for each player individually.");
            return;
        }

        System.out.println("Please enter the new blackjack win value. Otherwise it is 21 in default");
        int newWinVal;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. You must enter an integer!");
                sc.next();
            }
            newWinVal = sc.nextInt();
            if (newWinVal > 30)
                System.out.println("Your input is too large!");
        } while (newWinVal < this.winVal);
        this.winVal = newWinVal;


        System.out.println("Please enter the new dealer value. Otherwise it is 17 in default");
        int newDealerVal;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Please enter the new dealer value in Integer:");
                sc.next();
            }
            newDealerVal = sc.nextInt();
            if (newDealerVal > this.winVal - this.WIN_VAL_DIFF)
                System.out.println("Your input is too large! It cannot exceed " + this.winVal + " - " + WIN_VAL_DIFF +
                        " = " + (this.winVal - WIN_VAL_DIFF));
            else if (newDealerVal < this.dealerVal - this.WIN_VAL_DIFF) {
                System.out.println("Your input is too small! It should be at least " + this.dealerVal + " - " + WIN_VAL_DIFF +
                        " = " + (this.dealerVal - WIN_VAL_DIFF));
            } else
                break;
        } while (true);
        this.dealerVal = newDealerVal;
        System.out.println("\nNext step: Please enter your bet for each player individually.");
    }

    private void initGame() {
        deck = new BlackjackDeck();
        judge = new BlackjackJudge(dealerVal, winVal);
        dealer = new BlackjackDealer(deck);
        playerList = new ArrayList<BlackjackPlayer>(playerCount);
        for (int id = 0; id < playerCount; id++)
            playerList.add(new BlackjackPlayer(id, BALANCE)); // balance can be specified by the input later
        visualizer = new GameVisualizer(playerList);
    }

    /**
     * Deals initial two cards to both players and delaers in alternating sequence
     */
    private void dealCards() {
        for (int idx = 0; idx < 2; idx++) {
            for (BlackjackPlayer player : playerList) {
                BlackjackCard newCard = (BlackjackCard) deck.dealCard();
                ((BlackjackHand) player.getHands().get(0)).addCard(newCard);
            }
            BlackjackCard newCard = (BlackjackCard) deck.dealCard();
            ((BlackjackHand) dealer.getHands().get(0)).addCard(newCard);
        }
    }

    private String getUserAction() {
        // Read User input
        Scanner sc = new Scanner(System.in);
        int action_idx;
        int idx = 0;
        do {
            for (String action : actions) {
                System.out.print(action + ": " + idx++ + "\t");
            }
            System.out.println("\nPlease select your next action with its corresponding number (e.g., 0 to hit):");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer!");
                sc.next();
            }
            action_idx = sc.nextInt();
            if (action_idx < 0 || action_idx >= actions.length) {
                System.out.println("Invalid action number! Please enter an integer between 0 and 3!");
            } else
                break;
        } while (true);
        System.out.println("Your action: " + actions[action_idx]);
        return actions[action_idx];
    }

    private void playAction(BlackjackPlayer player, String action, BlackjackHand hand) {
        switch (action) {
            case "hit":
                hit(deck, hand);
                break;
            case "stand":
                stand();
                break;
            case "doubleUp":
                doubleUp(deck, player, hand);
                judge.isBusted(hand);
                break;
            case "split":
                split(player, hand);
                break;
        }
    }

    private boolean isNextRound() {
        System.out.println("Do you wish to start a new round? \n Input y or Y to continue, otherwise exit.");
        Scanner sc = new Scanner(System.in);
        String choice;
        choice = sc.nextLine();
        return !choice.equals("y") && !choice.equals("Y");
    }

    /**
     * The player takes one additional card
     *
     * @param deck - deck
     * @param hand - what we have right now
     */
    @Override
    public void hit(BlackjackDeck deck, BlackjackHand hand) {
        BlackjackCard newCard = (BlackjackCard) deck.dealCard();
        hand.addCard(newCard);
    }

    /**
     * The player could split into two hands, if the initial two cards are the same rank
     *
     * @param hand - the hand that wants to split
     * @return true if successfully split hands, false otherwise
     */
    @Override
    public void split(BlackjackPlayer player, BlackjackHand hand) {
        BlackjackCard card = hand.getCardAt(0);
        BlackjackHand newHand = new BlackjackHand(card);
        hand.removeCard(card);
        player.addHand(newHand);
        player.setBalance(-hand.getBet());
    }

    /**
     * The player double up their bets and immediately followed by a hit and stand
     *
     * @param deck
     * @param hand
     */
    @Override
    public void doubleUp(BlackjackDeck deck, BlackjackPlayer player, BlackjackHand hand) {
        player.setBalance(-hand.getBet());
        hand.setBet(hand.getBet() * 2);
        hit(deck, hand);
    }

    @Override
    public void stand() {
        return;
    }

    public static void main(String args[]) {
        BlackjackGame game = new BlackjackGame();
        game.start();
    }
}