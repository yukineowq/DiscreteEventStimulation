/** This class redirects the service of the customers
 *  according to their availability.  Checks if the state of the 
 *  customers is in one of the 5 states: Arrives, Served, Waits,
 *  Leaves and Done, and update their states and time accordingly.
 *
 *  @author yukineoweiqian
 *  @version CS2030 AY19/20 Sem 2 Project #1
 */

class Server {
    /** Variables for Server class. */
    private static final double SERVICE_TIME = 1.0;
    private double nextServiceTime;
    private boolean isWaiting = false;
    private final Statistics statistics = new Statistics();

    /** Constuctor for Server in the idle state:
     *  setting the nextServiceTime as 0.0
     *  and boolean value of isWaiting as false. */
    public Server() {
        this.nextServiceTime = 0.0;
        this.isWaiting = false;
    }

    /** Server checks the current customer state if it fits into one
     *  of the five states.
     *  @param incomingCust Server to serve the customer it receives
     *  @return new Customer of its original id, current or updated time and updated state.
     */
    public Customer serve(Customer incomingCust) {

        switch (incomingCust.getState()) {
            
            /** Retrive the current customer's state that is Arrives state.
             *  There are three possible scenarios: (i) if the customer time
             *  is more than the server's next service time. (ii) if there is 
             *  no earlier customer that is waiting, set this customer's state 
             *  to Waits. (iii) else, set the customer's state to Leaves.
             *  @return new Customer with the updated states accordingly.
             */
            case Arrives: 
                if (incomingCust.getTime() >= nextServiceTime) {
                    return new Customer(incomingCust.getId(), 
                            incomingCust.getTime(), CustomerState.Served);
                } else if (!isWaiting) {
                    isWaiting = true;
                    return new Customer(incomingCust.getId(), 
                            incomingCust.getTime(), CustomerState.Waits);
                } else {
                    statistics.updateNumLeft();
                    return new Customer(incomingCust.getId(), 
                            incomingCust.getTime(), CustomerState.Leaves);
                }
            
            /** Retrieve the current customer's state that is Served state.
             *  When the waiting customer's state in case Waits sets to an
             *  updated state of Served, isWaiting value will reset to false.
             *  nextServiceTime will also be updated to taking the first customer's
             *  finished time. Number of customers served will also be updated in
             *  statistics class.
             *  @return new Customer with its id, nextServiceTime and updated state to 
             *  Done.
             */
            case Served:
                isWaiting = false;
                nextServiceTime = incomingCust.getTime() + SERVICE_TIME;
                statistics.updateNumServed();
                return new Customer(incomingCust.getId(),
                        nextServiceTime, CustomerState.Done);
            
            /** Retrieve the current customer's state that is Waits state.
             *  To calculate the difference of the waiting time is taking 
             *  the nextServiceTime - current cuustomer's time, and this
             *  will be updated in the statistics class.
             *  @return new Customer with its id, nextServiceTime and updated
             *  state to become served.
             */
            case Waits:
                statistics.totalWaitingTime(nextServiceTime - incomingCust.getTime());
                return new Customer(incomingCust.getId(), 
                        nextServiceTime, CustomerState.Served);

            case Leaves:
            case Done:
            default:
                return null;
        }   
    }

    /** Return a print statement of the statistics system
     *  which consists of the number of customers served,
     *  leaves and total waiting time to be counted into average.
     */
    public void printStats() {
        System.out.println(statistics);
    }
}
