package behavioural.state;


public class WhyState {
    private enum State {
        IDLE, ITEM_SELECTED, HAS_MONEY, DISPENSING
    }

    private State currentState = State.IDLE;
    private String selectedItem = "";
    private double insertedAmount = 0.0;

    public void selectItem(String item) {
        switch (currentState) {
            case IDLE:
                selectedItem = item;
                currentState = State.ITEM_SELECTED;
                break;
            case ITEM_SELECTED:
                System.out.println("Item already selected");
                break;
            case HAS_MONEY:
                System.out.println("Payment Already received for the order");
                break;
            case DISPENSING:
                System.out.println("Currently Dispensing");
                break;
        }
    }

    public void insertCoin(double amount) {
        switch (currentState) {
            case IDLE:
                System.out.println("No Item has been selected yet");
                break;
            case ITEM_SELECTED:
                insertedAmount = amount;
                System.out.println("Inserted $" + amount + " for item");
                currentState = State.HAS_MONEY;
                break;
            case HAS_MONEY:
                System.out.println("Payment Already received for the order");
                break;
            case DISPENSING:
                System.out.println("Currently Dispensing");
                break;
        }
    }

    public void dispense() {
        switch (currentState) {
            case IDLE:
                System.out.println("No Item has been selected yet");
                break;
            case ITEM_SELECTED:
                System.out.println("Please pay the amount first");
            case HAS_MONEY:
                System.out.println("Dispensing item '" + selectedItem + "'");
                currentState = State.DISPENSING;
                System.out.println("Item dispensed successfully.");
                resetMachine();
                break;
            case DISPENSING:
                System.out.println("Already dispensing. Please wait.");
                break;
        }
    }

    private void resetMachine() {
        selectedItem = "";
        insertedAmount = 0.0;
        currentState = State.IDLE;
    }

    static void main() {

    }
}

