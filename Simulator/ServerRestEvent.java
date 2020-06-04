package cs2030.simulator;

/**
 * ServerRestEvent stimulates the act of server getting rest when the value
 * generated from the genRandomRest() in RandomGenerated class is less than the
 * probability of resting.
 * 
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class ServerRestEvent extends Event {

    /**
     * Creates a ServerRestEvent.
     * 
     * @param customer  Customer that the event is involving.
     * @param eventTime Time at which the event is created.
     * @param serverId  Server's id that is involved in ServerRestEvent.
     */
    public ServerRestEvent(Customer customer, double eventTime, ServerID serverId) {
        super(customer, eventTime, serverId);
    }
}