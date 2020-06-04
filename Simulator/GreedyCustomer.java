package cs2030.simulator;

/**
 * This class holds the GreedyCustomer information .
 *
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class GreedyCustomer extends Customer {

    /**
     * This method creates a new GreedyCustomer with its time recorded, by calling
     * the constructor.
     * 
     * @param timeArrived GreedyCustomer's arrival time.
     * @return A new GreedyCustomer.
     */
    public static GreedyCustomer createGreedyCustomer(double timeArrived) {
        return new GreedyCustomer(timeArrived);
    }

    /**
     * Constructor for creating the GreedyCustomer.
     */
    private GreedyCustomer(double timeArrived) {
        super(timeArrived);
    }

    /**
     * Checks if GreedyCustomer is greedy.
     * 
     * @return true that GreedyCustomer is greedy.
     */
    @Override
    public boolean isGreedy() {
        return true;
    }

    /**
     * Return a string representation of the GreedyCustomer with its id.
     * 
     * @return A string representation of the GreedyCustomer.
     */
    @Override
    public String toString() {
        return String.format(Integer.toString(this.getId()) + "(greedy)");
    }

}