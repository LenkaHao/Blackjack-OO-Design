/**
 * Created by Jiatong Hao, Xiankang Wu and Liqun Chen on 9/23/2019.
 */

import java.util.*;

public class Hand {
    private List<BlackjackCard> cards;

    public Hand(BlackjackCard card1, BlackjackCard card2) {
        cards = new ArrayList<BlackjackCard>();
        addCard(card1);
        addCard(card2);
    }

    public void addCard(BlackjackCard card) {
        cards.add(card);
    }

    public boolean isBusted() {
        List<Integer> scores = getScores();
        for (int score : scores) {
            if (score <= 21) {
                return false;
            }
        }
        return true;
    }

    public int getScore() {
        List<Integer> scores = getScores();
        int maxScore = 0;

        for (int score : scores) {
            if (score <= 21 && score > maxScore) {
                maxScore = score;
            }
        }
        return maxScore;
    }

    private List<Integer> getScores() {
        // there can be more than one Ace, so there can be multiple different scores
        List<Integer> scores = new ArrayList<Integer>();
        scores.add(0);

        for (BlackjackCard card : cards) {
            int gameValue = card.getGameValue();
            if (gameValue != 1) {
                for (int i = 0; i < scores.size(); i++) {
                    scores.set(i, scores.get(i) + gameValue);
                }
            } else {
                for (int i = 0; i < scores.size(); i++) {
                    scores.set(i, scores.get(i) + gameValue);
                }
                int curr_size = scores.size();
                for (int i = 0; i < curr_size; i++) {
                    scores.add(scores.get(i) + 10);
                }
            }
        }

        return scores;
    }

}
