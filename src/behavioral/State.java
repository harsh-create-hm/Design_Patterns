package behavioral;

/**
 * State Pattern
 *
 * Intent: Allow an object to alter its behavior when its internal state changes.
 * The object will appear to change its class.
 *
 * Use when:
 * - An object's behavior depends on its state, and it must change behavior at run-time.
 * - Operations have large, multipart conditional statements that depend on the object's state.
 */
public class State {

    // State interface
    interface VendingMachineState {
        void insertCoin();
        void selectProduct();
        void dispense();
    }

    // Context
    static class VendingMachine {
        private VendingMachineState idleState;
        private VendingMachineState hasCoinState;
        private VendingMachineState dispensingState;
        private VendingMachineState soldOutState;

        private VendingMachineState currentState;
        private int inventory;

        VendingMachine(int inventory) {
            idleState       = new IdleState(this);
            hasCoinState    = new HasCoinState(this);
            dispensingState = new DispensingState(this);
            soldOutState    = new SoldOutState(this);

            this.inventory = inventory;
            currentState = (inventory > 0) ? idleState : soldOutState;
        }

        public void setState(VendingMachineState state) { this.currentState = state; }
        public VendingMachineState getIdleState()       { return idleState; }
        public VendingMachineState getHasCoinState()    { return hasCoinState; }
        public VendingMachineState getDispensingState() { return dispensingState; }
        public VendingMachineState getSoldOutState()    { return soldOutState; }

        public void insertCoin()    { currentState.insertCoin();    }
        public void selectProduct() { currentState.selectProduct(); }
        public void dispense()      { currentState.dispense();      }

        public void releaseProduct() {
            if (inventory > 0) {
                System.out.println("Product dispensed!");
                inventory--;
            }
        }

        public int getInventory() { return inventory; }
    }

    // Concrete states
    static class IdleState implements VendingMachineState {
        private VendingMachine machine;
        IdleState(VendingMachine machine) { this.machine = machine; }

        @Override public void insertCoin() {
            System.out.println("Coin inserted.");
            machine.setState(machine.getHasCoinState());
        }
        @Override public void selectProduct() { System.out.println("Please insert a coin first."); }
        @Override public void dispense()      { System.out.println("Please insert a coin first."); }
    }

    static class HasCoinState implements VendingMachineState {
        private VendingMachine machine;
        HasCoinState(VendingMachine machine) { this.machine = machine; }

        @Override public void insertCoin()    { System.out.println("Coin already inserted."); }
        @Override public void selectProduct() {
            System.out.println("Product selected.");
            machine.setState(machine.getDispensingState());
        }
        @Override public void dispense()      { System.out.println("Please select a product first."); }
    }

    static class DispensingState implements VendingMachineState {
        private VendingMachine machine;
        DispensingState(VendingMachine machine) { this.machine = machine; }

        @Override public void insertCoin()    { System.out.println("Please wait, dispensing product."); }
        @Override public void selectProduct() { System.out.println("Please wait, dispensing product."); }
        @Override public void dispense() {
            machine.releaseProduct();
            if (machine.getInventory() > 0) {
                machine.setState(machine.getIdleState());
            } else {
                System.out.println("Out of stock!");
                machine.setState(machine.getSoldOutState());
            }
        }
    }

    static class SoldOutState implements VendingMachineState {
        private VendingMachine machine;
        SoldOutState(VendingMachine machine) { this.machine = machine; }

        @Override public void insertCoin()    { System.out.println("Machine is sold out. Coin returned."); }
        @Override public void selectProduct() { System.out.println("Machine is sold out."); }
        @Override public void dispense()      { System.out.println("Machine is sold out."); }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("State pattern demo:");
        System.out.println();

        VendingMachine machine = new VendingMachine(2);

        machine.selectProduct();      // no coin
        machine.insertCoin();
        machine.insertCoin();         // already has coin
        machine.selectProduct();
        machine.dispense();           // 1 product dispensed, 1 left

        System.out.println();
        machine.insertCoin();
        machine.selectProduct();
        machine.dispense();           // last product dispensed → sold out

        System.out.println();
        machine.insertCoin();         // sold out
    }
}
