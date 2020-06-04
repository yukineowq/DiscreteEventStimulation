package cs2030.simulator;

/**
 * This abstract class enforces polymorphism, whereby every event subclasses:
 * (i) ArriveEvent, (ii) ServedEvent, (iii) WaitsEvent, (iv) LeavesEvent, (v)
 * DoneEvent, (vi) ServerRestEvent and (vii) ServerBackEvent, inherits from this
 * Event class. These subclasses are able to create the next event.
 * 
 * @author Yuki Neo Wei Qian
 * @version Cs2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public abstract class Event {

    /** Variables for Event class. */
    private final double eventTime;
    protected Customer customer;
    protected ServerID serverId;

    /**
     * Constructor for creating a new event in ArriveEvent and LeavesEvent classes.
     * 
     * @param customer  Customer that the event is involving.
     * @param eventTime Time at which the event is created.
     */
    public Event(Customer customer, double eventTime) {
        this.customer = customer;
        this.eventTime = eventTime;
    }

    /**
     * Constructor for creating a new event in ServedEvent, DoneEvent and WaitsEvent
     * classes.
     * 
     * @param customer  Customer that the event is involving.
     * @param eventTime Time at which the event is created.
     * @param serverId  Server's id that is involved in ServerBackEvent.
     */
    public Event(Customer customer, double eventTime, ServerID serverId) {
        this.customer = customer;
        this.eventTime = eventTime;
        this.serverId = serverId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getCustomerId() {
        return this.customer.getId();
    }

    public double getEventTime() {
        return this.eventTime;
    }

    public int getServerId() {
        return this.serverId.getServerId();
    }

    /**
     * Return a string representation of the time at which the event is created.
     * 
     * @return a string representation of the event time.
     */
    public String toString() {
        return String.format("%.3f", getEventTime()) + " ";
    }
}
