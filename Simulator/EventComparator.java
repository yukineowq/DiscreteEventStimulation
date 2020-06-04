package cs2030.simulator;

import java.util.Comparator;

/**
 * This class is to create a comparison criteria for events.
 * 
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class EventComparator implements Comparator<Event> {

    /**
     * Compares 2 events and decides which event's time or customer id is smaller,
     * equal or greater than another event. The first key is to check for the
     * earliest time. If there is a tie-breaker, customerId is checked instead,
     * which also hints on the priority of the different events.
     * 
     * @param e1 Event 1
     * @param e2 Event 2
     * @return -1 if Event 1 is prioritised over Event 2.
     * @return 0 if there isn't a priority, which will not happen in this case.
     * @return 1 if Event 2 is prioritised over Event 1.
     */
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getEventTime() < e2.getEventTime()) {
            return -1;
        } else if (e1.getEventTime() > e2.getEventTime()) {
            return 1;
        } else if (e1.getCustomerId() < e2.getCustomerId()) {
            return -1;
        } else if (e1.getCustomerId() > e2.getCustomerId()) {
            return 1;
        } else {
            return 0;
        }
    }
}