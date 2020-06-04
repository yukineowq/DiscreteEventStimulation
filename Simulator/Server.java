package cs2030.simulator;

import java.util.Optional;
import java.util.PriorityQueue;

/**
 * This abstract class redirects the service of the customers according to their
 * availability. The status of the server is determined by getting and setting
 * its serving, waiting and resting states.
 * 
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public abstract class Server implements ServerID {

    /** Variables for Server class. */
    private static int nextServerId = 1;
    private static int maxQLength;
    private static double probabilityRest;
    protected double nextServiceTime = 0.0;
    protected final int serverId;
    protected boolean isServing = false;
    protected boolean isResting = false;
    protected PriorityQueue<Customer> waitingQueue;

    /**
     * Records the probability of resting from input.
     * 
     * @param probabilityRest The probability of resting.
     */
    public static void setProbabilityRest(double probabilityRest) {
        Server.probabilityRest = probabilityRest;
    }

    public static double getProbabilityRest() {
        return Server.probabilityRest;
    }

    public static void setMaxQLength(int maxQLength) {
        Server.maxQLength = maxQLength;
    }

    /**
     * Constructor for creating a new server. As for the waitingQueue initializing,
     * it is initializing a new PrioriyQueue of customers based on
     * CustomerComparator. The current comparator is using a FIFO ordering.
     */
    protected Server() {
        this.serverId = Server.nextServerId++;
        this.waitingQueue = new PriorityQueue<>(new CustomerComparator());
    }

    /**
     * Constructor with overloaded params taking in waitingQueue, such that every
     * server has its own queue length to take in customers.
     * 
     * @param waitingQueue The waitingQueue of customers per Server.
     */
    protected Server(PriorityQueue<Customer> waitingQueue) {
        this.serverId = Server.nextServerId++;
        this.waitingQueue = waitingQueue;
    }

    public int getServerId() {
        return this.serverId;
    }

    /**
     * This method functions in the instance of WAITS Event of EventManager class.
     * Adds a customer into the queue.
     * 
     * @param customer Customer to be added into the waitingQueue.
     */
    public void waits(Customer customer) {
        waitingQueue.add(customer);
    }

    /**
     * Retrieve the next customer from the waitingQueue.
     * 
     * @return An (Optional) Customer if there is a customer, else returns an
     *         Optional.empty().
     */
    public Optional<Customer> getNextCustomer() {
        return Optional.ofNullable(waitingQueue.poll());
    }

    /**
     * Schedules the server's nextServiceTime whenever a DONE event is generated.
     * 
     * @param nextServiceTime The nextServiceTime of a server.
     */
    public void serve(double nextServiceTime) {
        this.nextServiceTime = nextServiceTime;
        isServing = true;
    }

    /**
     * Sets the serving state of the Server to false in the DONE event.
     */
    public void doneServing() {
        isServing = false;
    }

    /**
     * Records the total time of the server consisting of restTime in EventManager
     * class and the current event time.
     * 
     * @param time The server's total time in a SERVERRESTEVENT.
     */
    public abstract void goToRest(double time);

    /**
     * Sets the resting state of the server to false.
     */
    public void backFromRest() {
        isResting = false;
    }

    public boolean isServing() {
        return this.isServing;
    }

    public boolean isResting() {
        return this.isResting;
    }

    /**
     * Returns true if Server's maxQLength is less than or equals to the
     * waitingQueue length.
     * 
     * @return if Server is busy, will return true.
     */
    public boolean isBusy() {
        return Server.maxQLength <= waitingQueue.size(); // max : 3 <= 4 : waitingQ
    }

    /**
     * Returns true if it is a SelfCheckOut, otherwise returns false;
     */
    public abstract boolean isSelfCheckOut();

    public double getNextServiceTime() {
        return this.nextServiceTime;
    }

}
