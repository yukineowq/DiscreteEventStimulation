/** This class handles the sequence of event executions.
 *  There are two main components: (i) customer queue 
 *  and (ii) checking of customer state for printing.
 *
 *  @author yukineoweiqian
 *  @version CS2030 AY19/20 Sem 2 Project #1
 */

import java.util.Scanner;
import java.util.PriorityQueue;

class EventManager {
    /** Scanner object used for taking in inputs. */
    Scanner sc = new Scanner(System.in);

    /** Server object used for checking if a server can execute the flow of events
     *  given the customer's state. */
    Server server = new Server();

    /** Log a customer's id and time into the customerQueue,
     *  Customer id and counter will have increment by 1 when one customer is recorded.
     *  The second while method is to check if the element of the customerQueue 
     *  is present, print statement will be executed. Followed by checking the state of
     *  the customers and adding back into the customerQueue, otherwise, it will
     *  then be printed and removed.
     *  Printing of statistics will be executed the last.
     *  @param hasNextDouble Scanner class check if the next input is a double value.
     *  @param isEmpty To check if the customer queue is empty.
     */
    public void eventMethod() {

        PriorityQueue<Customer> customerQueue = new PriorityQueue<>(new CustomerComparator());
        int customerId = 1;
        int customerCounter = 0;

        while (sc.hasNextDouble()) {
            double entryTime = sc.nextDouble();
            Customer newCust = new Customer(customerId, entryTime);
            customerQueue.add(newCust);
            customerId++;
            customerCounter++;
        }

        while (!customerQueue.isEmpty()) {
            System.out.println(customerQueue.peek());

            if (customerQueue.peek().getState() == CustomerState.Arrives ||
                    customerQueue.peek().getState() == CustomerState.Served || 
                    customerQueue.peek().getState() == CustomerState.Waits) {
                customerQueue.add(server.serve(customerQueue.poll()));
            } else {
                customerQueue.poll();
            }
        }
        server.printStats();
    }
}
