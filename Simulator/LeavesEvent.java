package cs2030.simulator;

/**
 * This LeavesEvent that handles the logic of a customer that leaves without
 * being served.
 * 
 * @author Yuki Neo Wei Qian
 * @version Cs2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class LeavesEvent extends Event {

    /**
     * Creates a new LeavesEvent.
     * 
     * @param customer  Customer that the event is involving.
     * @param eventTime Time at which the event is created.
     */
    public LeavesEvent(Customer customer, double eventTime) {
        super(customer, eventTime);
    }

    /**
     * Returns a string representation of the LeavesEvent.
     * 
     * @return A string representation of the LeavesEvent with (greedy) attached to
     *         the customer's id if the customer type is of GreedyCustomer, else
     *         return as per original statement.
     */
    @Override
    public String toString() {
        if (customer instanceof GreedyCustomer) {
            return super.toString() + (GreedyCustomer) customer + " " + "leaves";
        } else {
            return super.toString() + customer + " " + "leaves";
        }
    }

}