package questions.tictactoe.facade;

import questions.tictactoe.entity.Game;
import questions.tictactoe.entity.GameStatus;
import questions.tictactoe.entity.Player;
import questions.tictactoe.observer.Scorecard;

public class TicTacToeSystem {
    private static volatile TicTacToeSystem instance;

    private final Scorecard scorecard;
    private Game currentGame;

    public TicTacToeSystem() {
        this.scorecard = new Scorecard();
    }

    public static TicTacToeSystem getInstance() {
        if (instance == null) {
            synchronized (TicTacToeSystem.class) {
                if (instance == null) {
                    instance = new TicTacToeSystem();
                }
            }
        }
        return instance;
    }

    public Game createGame(Player player1, Player player2, int size) {
        currentGame = new Game(player1, player2, size);
        currentGame.addObserver(scorecard);
        System.out.println("New game started: " + player1.getName() +
                " vs " + player2.getName());
        return currentGame;
    }

    public void makeMove(Player player, int row, int col) {
        if (currentGame == null) {
            throw new IllegalStateException("Game is null");
        }
        System.out.println(player.getName() + " plays at (" + row + ", " + col + ")");
        currentGame.move(row, col);
        currentGame.printBoard();
    }

    public GameStatus  getGameStatus() {
        if (currentGame == null) {
            throw new IllegalStateException("Game is null");
        }
        return currentGame.getStatus();
    }

    public void printScoreboard() {
        scorecard.printScoreboard();
    }
}
