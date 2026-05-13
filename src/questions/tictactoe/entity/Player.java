package questions.tictactoe.entity;

public class Player {
    private final String name;
    private final Symbol symbol;

    public Player(String name, Symbol symbol) {
        validation(name, symbol);
        this.name = name;
        this.symbol = symbol;
    }

    private void validation(String name, Symbol symbol) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        if (symbol == Symbol.EMPTY) {
            throw new IllegalArgumentException("Player symbol cannot be EMPTY");
        }
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", symbol=" + symbol +
                '}';
    }
}
