package questions.tictactoe.entity;

public class Board {
    private final int size;
    private final Cell[][] board;

    public Board(int size) {
        this.size = size;
        board = new Cell[size][size];
        initializeBoard();
    }

    public int getSize() {
        return size;
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void placeSymbol(int row, int col, Symbol symbol) {
        validatePosition(row, col);
        board[row][col].setSymbol(symbol);
    }

    public boolean isCellAvailable(int row, int col) {
        validatePosition(row, col);
        return true;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Cell getCell(int row, int col) {
        validatePosition(row, col);
        return board[row][col];
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Position out of bounds");
        }
//        if (!board[row][col].isEmpty()) {
//            throw new IllegalArgumentException("Cell is already occupied");
//        }
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j].getSymbol()+" ");
                if (j < size - 1) System.out.print("| ");
            }
            System.out.println();
            if (i < size - 1) {
                System.out.println("-".repeat(size * 4 - 1));
            }
        }
        System.out.println();

    }
}