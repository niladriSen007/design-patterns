package behavioural.state;

interface MachineState {
    void selectItem(VendingMachine context, String itemCode);

    void insertCoin(VendingMachine context, double amount);

    void dispenseItem(VendingMachine context);
}

class IDLEState implements MachineState {
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println("Item selected: " + itemCode);
        context.setSelectedItem(itemCode);
        context.setState(new ITEM_SELECTEDState());
    }

    @Override
    public void insertCoin(VendingMachine context, double amount) {
        System.out.println("Please select an item before inserting coins.");
    }

    @Override
    public void dispenseItem(VendingMachine context) {
        System.out.println("No item selected. Nothing to dispense.");
    }
}

class ITEM_SELECTEDState implements MachineState {
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println("Item already selected: " + context.getSelectedItem());
    }

    @Override
    public void insertCoin(VendingMachine context, double amount) {
        System.out.println("Inserted $" + amount + " for item: " + context.getSelectedItem());
        context.setInsertedAmount(amount);
        context.setState(new HAS_MONEYState());
    }

    @Override
    public void dispenseItem(VendingMachine context) {
        System.out.println("Please pay the amount before dispensing");
    }
}

class HAS_MONEYState implements MachineState {

    @Override
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println("Cannot change item after inserting money.");
    }

    @Override
    public void insertCoin(VendingMachine context, double amount) {
        System.out.println("Money already inserted.");
    }

    @Override
    public void dispenseItem(VendingMachine context) {
        System.out.println("Dispensing item: " + context.getSelectedItem());
        context.setState(new DISPENSINGState());
        System.out.println("Item dispensed successfully.");
        context.reset();
    }
}

class DISPENSINGState implements MachineState {

    @Override
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println("Please wait, dispensing in progress.");
    }

    @Override
    public void insertCoin(VendingMachine context, double amount) {
        System.out.println("Please wait, dispensing in progress.");
    }

    @Override
    public void dispenseItem(VendingMachine context) {
        System.out.println("Already dispensing. Please wait.");
    }
}

class VendingMachine {

    private MachineState currentState;
    private String selectedItem;
    private double insertedAmount;

    public VendingMachine() {
        this.currentState = new IDLEState();
    }

    public void setState(MachineState machineState) {
        this.currentState = machineState;
    }

    public void setSelectedItem(String itemCode) {
        this.selectedItem = itemCode;
    }

    public void setInsertedAmount(double amount) {
        this.insertedAmount = amount;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void payMoney(double amount) {
        this.currentState.insertCoin(this,amount);
    }

    public void selectItem(String itemCode) {
        this.currentState.selectItem(this, itemCode);
    }

    public void dispenseItem() {
        currentState.dispenseItem(this);
    }

    public void reset() {
        this.selectedItem = "";
        this.insertedAmount = 0.0;
        this.currentState = new IDLEState();
    }

}

public class ThisIsWhyState {
    static void main() {
        VendingMachine vm = new VendingMachine();

        vm.payMoney(1.0);   // Rejected: no item selected
        vm.selectItem("A1");  // Transitions to ItemSelectedState
        vm.payMoney(1.5);   // Transitions to HasMoneyState
        vm.dispenseItem();    // Dispenses, resets to IdleState

        System.out.println("\n--- Second Transaction ---");
        vm.selectItem("B2");
        vm.payMoney(2.0);
        vm.dispenseItem();
    }
}
