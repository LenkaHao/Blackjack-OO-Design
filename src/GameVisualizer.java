import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameVisualizer {

    HashMap<Integer, Integer> playerState;

    int gameState;

    public GameVisualizer(List<BlackjackPlayer> players) {
        gameState = 1;
        playerState = new HashMap<>();
        updatePlayerState(players);
    }

    public HashMap updatePlayerState(List<BlackjackPlayer> players) {
        for (int i = 0; i < players.size(); i++) {
            playerState.put(players.get(i).getId(), players.get(i).getBalance());
        }
        return playerState;
    }

    public int updateGameState() {
        return gameState++;
    }

    public void display(List<BlackjackPlayer> players) {
        System.out.println("Round " + gameState + " result!");

        for (Map.Entry<Integer, Integer> entry : playerState.entrySet()) {
            System.out.println("Player " + entry.getKey() + "'s balance is " + entry.getValue());
        }
    }
}