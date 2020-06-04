package cs2030.simulator;

import java.util.PriorityQueue;

/**
 * This class encapsulates the creation of a Self-Checkout Counter, and that it
 * does not rest.
 * 
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class SelfCheckOut extends Server {

    /**
     * This method creates a new Self-Checkout Counter with its own shared queue by
     * calling the constructor.
     * 
     * @param selfCheckOutQueue The Self-Checkout Counters' shared queue of
     *                          customers.
     * @return A new self-checkout counter.
     */
    public static SelfCheckOut create(PriorityQueue<Customer> selfCheckOutQueue) {
        return new SelfCheckOut(selfCheckOutQueue);
    }

    /**
     * Constructor for creating the Self-Checkout Counter.
     * 
     * @param selfCheckOutQueue The Self-Checkout Counters' shared queue of
     *                          customers.
     */
    private SelfCheckOut(PriorityQueue<Customer> selfCheckOutQueue) {
        super(selfCheckOutQueue);
    }

    /**
     * Declares that Self-Checkout Counter is not resting.
     * 
     * @param time The current server's total time in a SERVERRESTEVENT.
     */
    @Override
    public void goToRest(double time) {
        isResting = false;
    }

    /**
     * Returns that Self-Checkout Counter is a self-checkout counter.
     * 
     * @return Self-Checkout Counter is a self-checkout counter.
     */
    @Override
    public boolean isSelfCheckOut() {
        return true;
    }

    /**
     * Returns a string representation of the self-checkout counter with its id.
     * 
     * @return A string representation of the self-checkout counter.
     */
    @Override
    public String toString() {
        return "self-check " + serverId;
    }
}