package cs2030.simulator;

/**
 * This abstract class encapsulates the customer information and act as a parent
 * class to GreedyCustomer and TypicalCustomer classes.
 *
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public abstract class Customer {
    /** Variables for Customer class. */
    private static int nextCustomerId = 1;
    private final double time;
    protected final int id;
    protected boolean isGreedy;

    /**
     * Constuctor for creating Customer with an arrive state. Every new Customer
     * created will have an updated id of its own.
     * 
     * @param time The customer's arrival time
     * 
     */
    protected Customer(double time) {
        this.id = Customer.nextCustomerId++;
        this.time = time;
    }

    public int getId() {
        return this.id;
    }

    public double getTime() {
        return this.time;
    }

    /**
     * Checks whether the Customer arrives is Greedy or Typical.
     */
    public abstract boolean isGreedy();

    /**
     * Returns a string representation of the customer's id.
     * 
     * @return A string representation of Customer's id.
     */
    public String toString() {
        return String.format(Integer.toString(id));
    }

}
