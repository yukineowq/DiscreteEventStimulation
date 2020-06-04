package cs2030.simulator;

import java.util.Comparator;

/**
 * This class is to create a comparison criteria for customers.
 * 
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class CustomerComparator implements Comparator<Customer> {

    /**
     * Compares 2 customers and decides which customer's time or id is smaller,
     * equal or greater than another customer. The first key is to check for the
     * earliest time. If there is a tie-breaker, customerId is checked instead,
     * which also hints on the priority of the different customers.
     * 
     * @param c1 Customer 1
     * @param c2 Customer 2
     * @return -1 if Customer 1 is prioritised over Customer 2.
     * @return 0 if there isn't a priority, which will not happen in this case.
     * @return 1 if Customer 2 is prioritised over Customer 1.
     */

    @Override
    public int compare(Customer c1, Customer c2) {
        if (c1.getTime() < c2.getTime()) {
            return -1;
        } else if (c1.getTime() > c2.getTime()) {
            return 1;
        } else if (c1.getId() < c2.getId()) {
            return -1;
        } else if (c1.getId() > c2.getId()) {
            return 1;
        } else {
            return 0;
        }
    }
}