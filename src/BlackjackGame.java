import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame extends Game implements BlackjackAction {
    private List<BlackjackPlayer> playerList;
    private BlackjackDealer dealer;
    private BlackjackDeck deck;
    private BlackjackJudge judge;
    private GameVisualizer visualizer;
    private final String[] actions = {"hit", "stand", "doubleUp", "split"};
    private final int WIN_VAL_DIFF = 4;
    private final int MAX_WIN_VAL = 4;
    private final int MAX_PLAYER = 4;
    private final int MIN_PLAYER = 1;
    private final int BALANCE = 100;
    private final int INITIAL_CARD_NUM = 2;
    private int winVal = 21;
    private int dealerVal = 17;
    private int playerCount;

    // constructor
    BlackjackGame() {
        super(0);
        setGameParams();
        initGame();
    }

    private void initGame() {
        deck = new BlackjackDeck();
        judge = new BlackjackJudge(dealerVal, winVal);
        dealer = new BlackjackDealer(deck);
        playerList = new ArrayList<>(playerCount);
        for (int id = 0; id < playerCount; id++)
            playerList.add(new BlackjackPlayer(id, BALANCE)); // balance can be specified by the input later
        visualizer = new GameVisualizer();
    }

    public void start() {
        // Players make their bets
        System.out.println("\nGame starts!");
        while (!playerList.isEmpty()) {
            playARound();
            resetHands();
        }
    }

    public void playARound() {
        System.out.println("\n*****************\nRound: " + getRound());

        for (BlackjackPlayer player : playerList) {
            player.makeBet();
        }

        dealInitialCards();
        playersPlay();
        dealerPlay();
        calcRoundResult();

        setRound(getRound()+1);
    }

    /**
     * Initialize two cards to both players and dealers in alternating sequence.
     */
    private void dealInitialCards() {
        for (int idx = 0; idx < this.INITIAL_CARD_NUM; idx++) {
            for (BlackjackPlayer player : playerList) {
                BlackjackCard newCard = (BlackjackCard) deck.dealCard();
                player.getHandAt(0).addCard(newCard);
            }
            BlackjackCard newCard = (BlackjackCard) deck.dealCard();
            dealer.getHand().addCard(newCard);
        }
    }

    private void playersPlay() {
        for (BlackjackPlayer player : playerList) {
            System.out.println("\n#################\nPlayer " + player.getId() + " starts!");
            List<BlackjackHand> hands = player.getHands();
            int handIdx = 0;

            for (BlackjackHand hand : hands) {
                visualizer.playHandInfo(player.getId(), player.getBalance(), handIdx, hand.getBet());
                visualizer.displayDealerCard(dealer.getVisibleCard());

                System.out.println(hand);

                if (judge.isNaturalBlackjack(hand)) {
                    visualizer.displayPlayerHand(hand);
                    System.out.println("Your current hand is a Natural Blackjack! Gorgeous!!!");
                    continue;
                }

                while (!judge.isBust(hand) || judge.isBlackjack(hand)) {
                    visualizer.displayPlayerHand(hand);

                    String next_action = getUserAction(player, hand);
                    playAction(player, next_action, hand);
                    if (next_action.equals("stand") || next_action.equals("doubleUp")) {
                        break;
                    }
                }

                visualizer.displayPlayerHand(hand);

                if (judge.isBlackjack(hand)) {
                    System.out.println("Your current hand is a Blackjack! Congrats!");
                }

                if (judge.isBust(hand)) {
                    System.out.println("Player " + player.getId() + " hand " + handIdx++ + " is Bust!");
                }
            }
        }
        System.out.println("\nAll players' terms end!");
    }

    private void dealerPlay() {
        System.out.println("\n#################\nDealer starts!");

        BlackjackHand dealerHand = dealer.getHand();

        visualizer.displayDealerHand(dealerHand);

        if (judge.isNaturalBlackjack(dealerHand)) {
            System.out.println("Dealer's current hand is a Natural Blackjack! Gorgeous!!!");
        }

        while (judge.canDealerHit(dealer)) {
            hit(deck, dealer.getHand());
            System.out.println("Dealer hits!");
            visualizer.displayDealerHand(dealerHand);
        }

        if (judge.isBlackjack(dealerHand)) {
            System.out.println("Your current hand is a Blackjack! Congrats!");
        }

        if (judge.isBust(dealerHand)) {
            System.out.println("Dealer hand is Bust!");
        }

        System.out.println("Dealer's term ends!");
    }

    private int getInteger(String str) {
        try {
            int res = Integer.parseInt(str);
            return res;
        } catch (Exception e) {
            return -1;
        }
    }

    private String getUserAction(BlackjackPlayer player, BlackjackHand hand) {
        // Read User input
        Scanner sc = new Scanner(System.in);
        boolean isValid = false;
        int input = -1;
        while (!isValid) {
            visualizer.displayActionChoices(actions);

            // if input is 1, 2, 3, 4 and action is valid, change isValid = true
            input = getInteger(sc.nextLine());
            if (1 <= input && input <= actions.length && judge.isActionValid(player, hand, actions[input-1])) {
                System.out.println("Your action: " + actions[input - 1]);
                isValid = true;
            }
            System.out.println("Invalid input. Please enter an integer between 1 to 4.\n");
        }
        return actions[input - 1];
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
                judge.isBust(hand);
                break;
            case "split":
                split(player, hand);
                break;
        }
    }

    private void resetHands() {
        dealer.clearHands();
        dealer.addHand(new BlackjackHand());
        for (BlackjackPlayer player : playerList) {
            player.clearHands();
            player.addHand(new BlackjackHand());
        }
    }

    private void calcRoundResult() {
        for (BlackjackPlayer player : playerList) {
            int roundBalance = judge.checkWinner(player, dealer);
            visualizer.printPlayerBalance(player.getId(), roundBalance, player.getBalance(), getRound());
            if (player.getBalance() == 0) {
                visualizer.playerLeaves(player);
                playerList.remove(player);
            }
            if (player.cashOut()) {
                visualizer.playerLeaves(player);
                playerList.remove(player);
            }
        }
    }

    /**
     * Initialize game params of BlackJack Game.
     */
    private void setGameParams() {

        System.out.println("Welcome to our game!");
        System.out.println("Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/23/2019.\n");
        Scanner sc = new Scanner(System.in);

        // Specify number of players
        int playerCount;
        System.out.println("\n*****************\n");
        do {
            System.out.println("Please tell us how many players will join the game.");
            System.out.println("We do not allow players to join or quit, until he/she loses all the balance.");
            while (!sc.hasNextInt()) {
                System.out.println("Input must be an integer larger or equal to 1!");
                sc.next();
            }
            playerCount = sc.nextInt();
            if (playerCount > MAX_PLAYER) {
                System.out.println("Maximum number of players: " + MAX_PLAYER + ". Your input is too large.");
            } else if (playerCount <= 0) {
                System.out.println("Minimum number of players: " + MIN_PLAYER + ". Your input is too small.");
            } else {
                this.playerCount = playerCount;
                break;
            }
        } while (true);

        System.out.println("Before we start, do you want to change default values?\ne.g. 21 is the default blackjack " +
                "target value, you can change it to an integer.");
        System.out.println("Enter y or Y to change, otherwise to skip.");

        String choice;
        sc.nextLine(); // Read the input buffer and discard it.
        choice = sc.nextLine();
        if (!choice.equals("y") && !choice.equals("Y")) {
            System.out.println("\nYou skipped the configuration.\n" +
                    "Next step: Please enter your bet for each player individually.");
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
            if (newWinVal > MAX_WIN_VAL)
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
            } else {
                break;
            }
        } while (true);
        this.dealerVal = newDealerVal;
        System.out.println("\nNext step: Please enter your bet for each player individually.");
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
}