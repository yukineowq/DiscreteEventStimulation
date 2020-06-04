package cs2030.simulator;

/**
 * ServerBackEvent stimulates the act of server getting back from rest.
 * 
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class ServerBackEvent extends Event {

    /**
     * Creates a ServerBackEvent.
     * 
     * @param customer  Customer that the event is involving.
     * @param eventTime Time at which the event is created.
     * @param serverId  Server's id that is involved in ServerBackEvent.
     */
    public ServerBackEvent(Customer customer, double eventTime, ServerID serverId) {
        super(customer, eventTime, serverId);
    }
}