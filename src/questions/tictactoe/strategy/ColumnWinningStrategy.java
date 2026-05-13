package questions.tictactoe.strategy;

import questions.tictactoe.entity.Board;
import questions.tictactoe.entity.Symbol;

public class ColumnWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWin(Board board, int row, int col, Symbol symbol) {
        int size = board.getSize();
        for (int r = 0; r < size; r++) {
            if (board.getCell(r, col).getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }
}
