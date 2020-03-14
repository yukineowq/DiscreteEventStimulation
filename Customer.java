/** This class encapsulates the customer information. 
 *
 * @author yukineoweiqian
 * @version CS2030 AY19/20 Sem 2 Project #1
 */
class Customer {
    /** Variables for Customer class. */
    private final int id;
    private final double time;
    private final CustomerState state;
    
    /** 
     * Constuctor for creating Customer with an arrive state.
     * @param id The customer's id
     * @param time The customer's arrival time
     */
    public Customer(int id, double time) {
        this.id = id;
        this.time = time;
        this.state = CustomerState.Arrives;
    }
    
    /** 
     * Overloaded constructor for returning new Customer with an updated state in Server class.
     * @param id The customer's id.
     * @param time The customer's time.
     * @param state The customer's updated state.
     */
    public Customer(int id, double time, CustomerState state) {
        this.id = id;
        this.time = time;
        this.state = state;
    }

    public int getId() {
        return this.id;
    }

    public double getTime() {
        return this.time;
    }

    public CustomerState getState() {
        return this.state;
    }
    
    /**
     * Returns a string representation of the customer state, 
     * which consists of the time, id and state. 
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return String.format("%.3f", this.time) + " " + this.id + " " + this.state;
    }
}
