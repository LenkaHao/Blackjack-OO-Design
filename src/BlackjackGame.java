import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame extends Game implements BlackjackAction {
    private final int WIN_VAL = 21;
    private final int DEALER_VAL = 17;
    private final int MIN_DEALER_VAL = 16;
    private final int MAX_DEALER_VAL = 18;
    private final int WIN_VAL_DIFF = 4;
    private final int MAX_PLAYER = 4;
    private final int MIN_PLAYER = 1;
    private final int BALANCE = 100;
    private final int INITIAL_CARD_NUM = 2;

    private List<BlackjackPlayer> playerList;
    private BlackjackDealer dealer;
    private BlackjackDeck deck;
    private BlackjackJudge judge;
    private GameVisualizer visualizer;
    private final String[] actions = {"hit", "stand", "doubleUp", "split"};

    private int winVal = WIN_VAL;
    private int dealerVal = DEALER_VAL;
    private int balance = BALANCE;
    private int playerCount;

    // constructor
    BlackjackGame() {
        super(0);
        visualizer = new GameVisualizer();
        visualizer.welcomeMsg();
        setGameParams();
        setPlayerNumber();
        initGame();
    }

    public void start() {
        System.out.println("\nGame starts!");
        while (!playerList.isEmpty()) {
            playARound();
            resetHands();
        }
        System.out.println("\nGame ends.");
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
     * Initialize game params of BlackJack Game.
     */
    private void setGameParams() {
        Scanner sc = new Scanner(System.in);

        // check if user want to overwrite default game parameters
        visualizer.displaySetDefaultParams();
        String choice = sc.nextLine();
        if (!choice.equals("y") && !choice.equals("Y")) {
            System.out.println("The game will use default parameters.\n");
            return;
        }

        setDealerParam();
        setPlayerBalance();
        System.out.println("The game will use " + dealerVal + " as dealer stopping value and " + balance + " as balance.\n");

//        System.out.println("Please enter the new blackjack win value. Otherwise it is 21 in default");
//        int newWinVal;
//        do {
//            while (!sc.hasNextInt()) {
//                System.out.println("Invalid input. You must enter an integer!");
//                sc.next();
//            }
//            newWinVal = sc.nextInt();
//            if (newWinVal > MAX_WIN_VAL)
//                System.out.println("Your input is too large!");
//        } while (newWinVal < this.winVal);
//        this.winVal = newWinVal;
    }

    private void setDealerParam() {
        visualizer.displaySetDealerParam();
        Scanner scanner = new Scanner(System.in);
        int dealerValInput = getInteger(scanner.nextLine());
        if (dealerValInput >= MIN_DEALER_VAL && dealerValInput <= MAX_DEALER_VAL) {
            this.dealerVal = dealerValInput;
        }
    }

    private void setPlayerBalance() {
        visualizer.displaySetBalanceParam();
        Scanner scanner = new Scanner(System.in);
        int balanceInput = getInteger(scanner.nextLine());
        if (balanceInput > 1) {
            this.balance = balanceInput;
        }
    }


    private void setPlayerNumber() {
        visualizer.displaySetPlayerCountInfo(MAX_PLAYER);
        Scanner scanner = new Scanner(System.in);
        boolean isPlayerValid = false;
        int playerCountInput = -1;
        while (!isPlayerValid) {
            playerCountInput = getInteger(scanner.nextLine());
            if (1 <= playerCountInput && playerCountInput <= MAX_PLAYER) {
                System.out.println("The game will have " + playerCountInput + " player(s) in the beginning.");
                isPlayerValid = true;
            } else {
                visualizer.displayInvalidMsgForRange(1, MAX_PLAYER);
            }
        }
        this.playerCount = playerCountInput;
    }


    private void initGame() {
        deck = new BlackjackDeck();
        judge = new BlackjackJudge(dealerVal, winVal);
        dealer = new BlackjackDealer(deck);
        playerList = new ArrayList<>(playerCount);
        for (int i = 0; i < playerCount; i++)
            playerList.add(new BlackjackPlayer(i+1, balance));
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

    /**
     * Players make move
     */
    private void playersPlay() {
        for (BlackjackPlayer player : playerList) {
            System.out.println("\n#################\nPlayer " + player.getId() + " starts!");
            // for (BlackjackHand hand : hands) {
            for (int i = 0; i < player.getHandCount(); i++) {
                BlackjackHand hand = player.getHandAt(i);
                visualizer.playHandInfo(player.getId(), player.getBalance(), i, hand.getBet());
                visualizer.displayDealerCard(dealer.getVisibleCard());

                if (judge.isNaturalBlackjack(hand)) {
                    visualizer.displayPlayerHand(hand);
                    System.out.println("Your current hand is a Natural Blackjack! Gorgeous!!!");
                    continue;
                }

                while (!judge.isBust(hand) && !judge.isBlackjack(hand)) {
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
                    int displayedIdx = i + 1;
                    System.out.println("Player " + player.getId() + " hand " + displayedIdx + " is Bust!");
                }
            }
        }
        System.out.println("\nAll players' terms end!");
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
            } else {
                visualizer.displayInvalidMsgForRange(1, actions.length);
            }
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
                break;
            case "split":
                split(player, hand);
                break;
        }
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
        System.out.println("#################\n");
    }

    private void calcRoundResult() {
        List<BlackjackPlayer> toRemove = new ArrayList<>();
        for (BlackjackPlayer player : playerList) {
            int roundBalance = judge.checkWinner(player, dealer);
            visualizer.printPlayerBalance(player.getId(), roundBalance, player.getBalance(), getRound());
            if (player.getBalance() == 0) {
                visualizer.playerLeaves(player);
                toRemove.add(player);
            }
            if (player.cashOut()) {
                visualizer.playerLeaves(player);
                toRemove.add(player);
            }
        }
        for (BlackjackPlayer player: toRemove) {
            playerList.remove(player);
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