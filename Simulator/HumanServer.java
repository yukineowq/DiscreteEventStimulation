package cs2030.simulator;

/**
 * This class encapsulates the creation of a HumanServer, and that it goes to
 * rest.
 * 
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class HumanServer extends Server {

    /**
     * This method creates a new HumanServer by calling the constuctor.
     * 
     * @return A new HumanServer.
     */
    public static HumanServer create() {
        return new HumanServer();
    }

    /**
     * Constructor for creating the HumanServer.
     * 
     */
    private HumanServer() {
        super();
    }

    /**
     * Declares that HumanServer is resting and records the total time of the
     * HumanServer consisting of restTime in EventManager class and the current
     * event time.
     * 
     * @param time The server's total time in a SERVERRESTEVENT.
     */
    @Override
    public void goToRest(double time) {
        isResting = true;
        nextServiceTime = time;
    }

    /**
     * Returns that HumanServer is not a self-checkout counter.
     * 
     * @return HumanServer is not a self-checkout.
     */
    @Override
    public boolean isSelfCheckOut() {
        return false;
    }

    /**
     * Returns a string representation of the server with its id.
     * 
     * @return A string representation of the server.
     */
    @Override
    public String toString() {
        return "server " + serverId;
    }
}