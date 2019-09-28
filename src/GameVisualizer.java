/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameVisualizer {

    private HashMap<Integer, Integer> playerState;

    private int gameState;

    /**
     * constructor
     */
    public GameVisualizer(List<BlackjackPlayer> players) {
        gameState = 1;
        playerState = new HashMap<>();
        updatePlayerState(players);
    }

    /**
     * update and store player's balance
     * @param players
     */
    public void updatePlayerState(List<BlackjackPlayer> players) {
        for (int i = 0; i < players.size(); i++) {
            playerState.put(players.get(i).getId(), players.get(i).getBalance());
        }
    }

    /**
     * visualize the game, print game data at the end of each round
     * @param players
     */
    public void display(List<BlackjackPlayer> players) {
        System.out.println("Round " + gameState++ + " result!");

        for (Map.Entry<Integer, Integer> entry : playerState.entrySet()) {
            System.out.println("Player " + entry.getKey() + "'s balance is " + entry.getValue());
        }
    }
}