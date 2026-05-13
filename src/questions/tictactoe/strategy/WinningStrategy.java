package questions.tictactoe.strategy;

import questions.tictactoe.entity.Board;
import questions.tictactoe.entity.Symbol;

public interface WinningStrategy {
    boolean checkWin(Board board, int row, int col, Symbol symbol);
}
