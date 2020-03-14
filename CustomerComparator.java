/** This class is to create a comparison criteria for customers. 
 *
 * @author yukineoweiqian
 * @version CS2030 AY19/20 Sem 2 Project #1
 */

import java.util.Comparator;

class CustomerComparator implements Comparator<Customer> {
    /** Compares 2 customers and decides which customer's time or id 
     * is smaller, equal or greater than another customer.
     * The first key is to check for the earliest time.
     * If there is a tie-breaker, customerId is checked instead,
     * which also hints on the priority of the different customers. 
     * @param cust1 Customer 1
     * @param cust2 Customer 2
     * @return -1 if Customer 1 is prioritised over Customer 2. 
     * @return 0 if there isn't a priority, which will not happen in this case.
     * @return 1 if Customer 2 is prioritised over Customer 1.
     */
    @Override
    public int compare(Customer cust1, Customer cust2) {
        if (cust1.getTime() < cust2.getTime()) {
            return -1;
        } else if (cust1.getTime() > cust2.getTime()) {
            return 1;
        } else if (cust1.getId() < cust2.getId()) {
            return -1;
        } else if (cust1.getId() > cust2.getId()) {
            return 1;
        } else {
            return 0;
        }
    }
}
