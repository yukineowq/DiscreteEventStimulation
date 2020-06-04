package cs2030.simulator;

/**
 * This ArrivalEvent that handles the logic of receiving a customer that
 * arrives.
 * 
 * @author Yuki Neo Wei Qian
 * @version Cs2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class ArriveEvent extends Event {

    /**
     * Creates a new ArriveEvent.
     * 
     * @param customer  Customer that the event is involving.
     * @param eventTime Time at which the event is created.
     */
    public ArriveEvent(Customer customer, double eventTime) {
        super(customer, eventTime);
    }

    /**
     * Returns a string representation of the ArriveEvent.
     * 
     * @return A string representation of the ArriveEvent with (greedy) attached to
     *         the customer's id if the customer type is of GreedyCustomer, else
     *         return as per original statement.
     */
    @Override
    public String toString() {
        if (customer instanceof GreedyCustomer) {
            return super.toString() + (GreedyCustomer) customer + " " + "arrives";
        } else {
            return super.toString() + customer + " " + "arrives";
        }
    }
}