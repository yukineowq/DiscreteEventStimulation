/**
 * This class encapsulates the calculation of server counter, 
 * leave counter and total waiting time of the customers.    
 * There are three main components: (i) the number of customers served, 
 * (ii) the number of customers who left without being served and 
 * (iii) the average waiting time for customers who have been served.
 *
 * @author yukineoweiqian
 * @version CS2030 AY19/20 Sem 2 Project #1
 */
class Statistics {
    /** Variables for Statistics class. */
    private int serveCounter = 0; 
    private int leaveCounter = 0;
    private double totalWaitTime = 0.0;

    /** Update the number of customers served. */
    public void updateNumServed() {
        serveCounter++;
    }
    
    /** Update the number of customers left without being served. */
    public void updateNumLeft() {
        leaveCounter++;;
    }

    /** 
     * Calculate the total waiting time of each customer 
     * by adding to the difference of (serving time - waiting time).
     * @param diffTime The difference in timing between the serving time and waiting time. 
     */
    public void totalWaitingTime(double diffTime) {
        totalWaitTime += diffTime;
    }
    
    /** 
     * Return a string representation of the statistics state, which consists of 
     * all the average total waiting time, serve counter and leave counter.
     * @return A string representation of the statistics.
     */
    @Override
    public String toString() {
        return "[" + String.format("%.3f", (totalWaitTime / serveCounter)) + " " +
            serveCounter + " " + leaveCounter + "]";
    }
}
