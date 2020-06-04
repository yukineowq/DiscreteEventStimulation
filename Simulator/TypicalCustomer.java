package cs2030.simulator;

/**
 * This class holds the TypicalCustomer information .
 *
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class TypicalCustomer extends Customer {

    /**
     * This method creates a new TypicalCustomer with its time recorded, by calling
     * the constructor.
     * 
     * @param timeArrived TypicalCustomer's arrival time.
     * @return A new TypicalCustomer.
     */
    public static TypicalCustomer createTypicalCustomer(double timeArrived) {
        return new TypicalCustomer(timeArrived);
    }

    /**
     * Constructor for creating the TypicalCustomer.
     */
    private TypicalCustomer(double timeArrived) {
        super(timeArrived);
    }

    /**
     * Checks if TypicalCustomer is greedy.
     * 
     * @return false that TypicalCustomer is not greedy.
     */
    @Override
    public boolean isGreedy() {
        return false;
    }

    /**
     * Return a string representation of the TypicalCustomer with its id.
     * 
     * @return A string representation of the TypicalCustomer.
     */
    @Override
    public String toString() {
        return String.format(Integer.toString(this.getId()));
    }
}