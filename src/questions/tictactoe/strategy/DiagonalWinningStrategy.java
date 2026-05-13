package questions.tictactoe.strategy;

import questions.tictactoe.entity.Board;
import questions.tictactoe.entity.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWin(Board board, int row, int col, Symbol symbol) {
        int size = board.getSize();
        boolean mainDiagonal = true;

        // top-left to bottom-right
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, i).getSymbol() != symbol) {
                mainDiagonal = false;
                break;
            }
        }
        if (mainDiagonal) return true;

        // top-right to bottom-left
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, size - 1 - i).getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }
}
