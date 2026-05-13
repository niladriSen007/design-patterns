package questions.tictactoe.entity;

import questions.tictactoe.observer.GameObserver;
import questions.tictactoe.strategy.ColumnWinningStrategy;
import questions.tictactoe.strategy.DiagonalWinningStrategy;
import questions.tictactoe.strategy.RowWinningStrategy;
import questions.tictactoe.strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    private final Board board;
    private final List<Player> players;
    private final List<WinningStrategy> winningStrategies;
    private final List<GameObserver> gameObservers;
    private int currentPlayerIndex;
    private GameStatus status;

    public Game(Player player1, Player player2, int size) {
        this.board = new Board(size);
        this.players = initializePlayers(player1, player2);
        this.currentPlayerIndex = 0;
        this.status = GameStatus.IN_PROGRESS;
        this.winningStrategies = initializeStrategies();
        this.gameObservers = new CopyOnWriteArrayList<>(); // The observer list uses CopyOnWriteArrayList, which allows safe iteration even if observers are added during notification.
    }

    private List<Player> initializePlayers(Player player1, Player player2) {
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    private List<WinningStrategy> initializeStrategies() {
        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());
        return winningStrategies;

    }

    // The makeMove method is synchronized. This prevents two threads from making moves simultaneously, which could corrupt the game state
    public synchronized void move(int row, int col) {
        // check if the game is IN_PROGRESS or not
        if (this.status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game is already over");
        }
        // check if the cell is empty or not
        if (!board.getCell(row, col).isEmpty()) {
            throw new IllegalArgumentException("Cell is already occupied");
        }
        // place the symbol
        Player currentPlayer = players.get(currentPlayerIndex);
        board.placeSymbol(row, col, currentPlayer.getSymbol());
        // check for WIN
        if (checkWin(row, col, currentPlayer.getSymbol())) {
            this.status = currentPlayer.getSymbol() == Symbol.X ? GameStatus.WINNER_X : GameStatus.WINNER_O;
             notifyObservers();
            return;
        }
        // check for DRAW
        if (board.isBoardFull()) {
            this.status = GameStatus.DRAW;
             notifyObservers();
            return;
        }
        // switch to next player
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private boolean checkWin(int row, int col, Symbol symbol) {
        for (WinningStrategy strategy : winningStrategies) {
            if (strategy.checkWin(board, row, col, symbol)) {
                return true;
            }
        }
        return false;
    }

    public void addObserver(GameObserver observer) {
        gameObservers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        gameObservers.remove(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer : gameObservers) {
            observer.update(this);
        }
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public GameStatus getStatus() {
        return status;
    }

    public void printBoard() {
        board.printBoard();
    }

    public Player getWinner() {
        if (status == GameStatus.WINNER_X) {
            return players.get(0).getSymbol() == Symbol.X ? players.get(0) : players.get(1);
        } else if (status == GameStatus.WINNER_O) {
            return players.get(0).getSymbol() == Symbol.O ? players.get(0) : players.get(1);
        }
        return null;
    }
}
