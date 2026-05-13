package questions.tictactoe.observer;

import questions.tictactoe.entity.Game;
import questions.tictactoe.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

public class Scorecard implements GameObserver {

    private final ConcurrentHashMap<String, Integer> scoreboard;

    public Scorecard() {
        this.scoreboard = new ConcurrentHashMap<>();
    }

    @Override
    public void update(Game game) {
        Player winner = game.getWinner();
        if (winner != null) {
            recordWin(winner);
            System.out.println("Scoreboard updated: " + winner.getName() + " wins!");
        }
    }

    public void recordWin(Player player) {
        scoreboard.merge(player.getName(), 1, Integer::sum);
    }

    public int getScore(String playerName) {
        return scoreboard.getOrDefault(playerName, 0);
    }

    public void printScoreboard() {
        System.out.println("\n===== SCOREBOARD =====");
        if (scoreboard.isEmpty()) {
            System.out.println("No games played yet.");
        } else {
            scoreboard.forEach((name, score) -> System.out.println(name + ": " + score + " wins"));
        }
        System.out.println("======================\n");
    }
}
