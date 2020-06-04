package cs2030.simulator;

/**
 * This ServedEvent that handles the logic of the server serving a customer.
 * 
 * @author Yuki Neo Wei Qian
 * @version Cs2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class ServedEvent extends Event {

    /**
     * Creates a new ServedEvent.
     * 
     * @param customer  Customer that the event is involving.
     * @param eventTime Time at which the event is created.
     * @param serverId  Server's id that is involved in ServedEvent.
     */
    public ServedEvent(Customer customer, double eventTime, ServerID serverId) {
        super(customer, eventTime, serverId);
    }

    /**
     * Returns a string representation of the ServedEvent.
     * 
     * @return A string representation of the ServedEvent with 4 conditions to be
     *         taken into consideraton: (i) if the server is a Self-Checkout Counter
     *         and the customer is a GreedyCustomer, a (greedy) is attached to the
     *         customer's id and serverId is of (SelfCheckOut) type, (ii) if the
     *         server is a Self-Checkout Counter and the customer is a
     *         TypicalCustomer, only serverId is of (SelfCheckOut) type, (iii) if
     *         the server not a Self-Checkout Counter and the customer is a
     *         GreedyCustomer, only a (greedy) is attached to the customer's id and,
     *         (iv) if none of the above, return as per original statement.
     */
    public String toString() {
        if ((serverId instanceof SelfCheckOut) && (customer instanceof GreedyCustomer)) {
            return super.toString() + (GreedyCustomer) customer + " " + "served by " + (SelfCheckOut) serverId;
        } else if ((serverId instanceof SelfCheckOut) && !(customer instanceof GreedyCustomer)) {
            return super.toString() + customer + " " + "served by " + (SelfCheckOut) serverId;
        } else if (!(serverId instanceof SelfCheckOut) && (customer instanceof GreedyCustomer)) {
            return super.toString() + (GreedyCustomer) customer + " " + "served by " + serverId;
        } else {
            return super.toString() + customer + " " + "served by " + serverId;
        }
    }

}