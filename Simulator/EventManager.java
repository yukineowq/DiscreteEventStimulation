package cs2030.simulator;

import java.util.Arrays;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * This class handles the sequence of event executions. There are 3 main
 * components: (i) event queue and self-checkout queue that act like FIFO
 * (first-in-first-out) queues for customers with a given maximum capacity, (ii)
 * checking of event state for printing and (iii) checking which server (Human
 * or Self-Checkout Counter) is available to serve which customer (Greedy or
 * Typical).
 * 
 * @author Yuki Neo Wei Qian
 * @version CS2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

public class EventManager {
    /** Variables for Event Manager class. */
    private final Statistics statistics = new Statistics();
    /** Array of servers that determines the way Events are processed. */
    private Server[] servers;
    /** Array of customers that determines the wat Customers are processed. */
    private Customer[] customers;
    /** PriorityQueue of Events. */
    private PriorityQueue<Event> eventQueue;
    /** PriorityQueue of Customers for Self-Checkout Counters. */
    private PriorityQueue<Customer> checkoutQ;
    RandomGenerator generator;

    /**
     * (i) Log the number of servers required into an array and create HumanServer
     * and Self-Checkout Counter accordingly. HumanServers are identified from the
     * start of input while Self-Checkout Counters are identified after the end of
     * taking in HumanServers. Example, if there are 6 servers and 3 Self-CheckOut
     * Counters input, [1,2,3] are HumanServers, [4,5,6] are Self-Checkout Counters.
     * (ii) Log a customer's id and time into the customerQueue from line 76 to 86.
     * (iii) The 3 Optional methods are to process the full sequence allocating,
     * depending on server's availability to serve the customer. (iv) Check if the
     * state of the event is in one of the 5 states: Arrives, Served, Waits, Leaves
     * and Done, and update their states and time accordingly. Printing of
     * statistics will be executed the last.
     */
    public void eventMethod() {

        /** Scanner object used for taking in inputs. */
        Scanner sc = new Scanner(System.in);

        int seedRG = sc.nextInt();
        int numOfServers = sc.nextInt();
        int numOfSelfCheckoutCounters = sc.nextInt();
        Server.setMaxQLength(sc.nextInt());
        int numOfCustomers = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        double restingRate = sc.nextDouble();
        Server.setProbabilityRest(sc.nextDouble());
        double probabilityGreedy = sc.nextDouble();

        checkoutQ = new PriorityQueue<Customer>(new CustomerComparator());

        generator = new RandomGenerator(seedRG, arrivalRate, serviceRate, restingRate);

        servers = new Server[numOfServers + numOfSelfCheckoutCounters];
        for (int i = 0; i < numOfServers; i++) {
            this.servers[i] = HumanServer.create();
        }
        for (int j = numOfServers; j < servers.length; j++) {
            this.servers[j] = SelfCheckOut.create(checkoutQ);
        }

        eventQueue = new PriorityQueue<>(new EventComparator());

        this.customers = new Customer[numOfCustomers];
        double custArrivalTime = 0.0;
        for (int i = 0; i < numOfCustomers; i++) {
            if (generator.genCustomerType() < probabilityGreedy) {
                customers[i] = GreedyCustomer.createGreedyCustomer(custArrivalTime);
            } else {
                customers[i] = TypicalCustomer.createTypicalCustomer(custArrivalTime);
            }
            eventQueue.add(new ArriveEvent(customers[i], custArrivalTime));
            custArrivalTime += generator.genInterArrivalTime();
        }

        sc.close();
    }

    /**
     * To retrieve an available server.
     * 
     * @return Converting the array of servers into a stream, and filter out by
     *         checking if the server is not serving and not resting.
     */
    private Optional<Server> getAvailableServer() {
        return Arrays.stream(servers).filter(server -> !server.isServing() && !server.isResting()).findFirst();
    }

    /**
     * To retrieve a non-busy server.
     * 
     * @return Converting the array of servers into a stream, and filter out by
     *         checking if the server is not busy.
     */
    public Optional<Server> getNonBusyServer() {
        return Arrays.stream(servers).filter(server -> !server.isBusy()).findFirst();
    }

    /**
     * To retrieve a non-busy server with the shortest queue for a greedy customer
     * by comparing the waitingQueue size between 2 servers.
     * 
     * @param isGreedy A greedy customer.
     * @return if is a greedy customer, nonBusyStream will reduce to find if
     *         server2's queue is shorter than server1's queue, it will return
     *         server2. Else, if is a typical customer, it is done by converting the
     *         array of servers into a stream, and filter out by checking if the
     *         server is not busy.
     */
    private Optional<Server> getGreedyNonBusyServer(boolean isGreedy) {
        Stream<Server> nonBusyStream = Arrays.stream(servers).filter(server -> !server.isBusy());
        return (isGreedy) ? nonBusyStream.reduce(
                (server1, server2) -> (server1.waitingQueue.size() > server2.waitingQueue.size()) ? server2 : server1)
                : nonBusyStream.findFirst();
    }

    /**
     * This method consists of the main logic for the execution of the start of
     * stimulation. Firstly, it is done by checking if this event of the eventQueue
     * is not empty, and if this event is not an instanceof ServerRestEvent and
     * ServerBackEvent, it will be printed out and goes through the fitting of which
     * different event states. Then, it will be removed from the queue, and this
     * repeats till the last event.
     */
    public void startSimulation() {
        while (!eventQueue.isEmpty()) {

            Event event = eventQueue.poll();

            if (!(event instanceof ServerRestEvent) && !(event instanceof ServerBackEvent)) {
                System.out.println(event);
            }

            /**
             * Retrieve the current event's state that is Arrives state. There 2 conditions:
             * if the customer is a Greedy or Typical customer. (i) if is a greedy customer,
             * it will invoke getGreedyNonBusyServer() method. (ii) if is a typical
             * customer, it will invoke getNonBusyServer() method. There are also three
             * possible scenarios: (i) Check whether the server is serving any customer and
             * not resting. Adds a new customer of updated state based on the availability
             * of servers into the customerQueue, set the customer's state to Served. (ii)
             * Check whether the server's maxQLength is less than or equals to the
             * waitingQueue length, and set the customer's state to Waits. (iii) Check that
             * if both scenarios above fulfill, this would set the customer's state to
             * Leaves.
             */
            if (event instanceof ArriveEvent) {

                if (event.getCustomer() instanceof GreedyCustomer) {
                    getAvailableServer().ifPresentOrElse(
                            server -> eventQueue
                                    .add(new ServedEvent(event.getCustomer(), event.getEventTime(), server)),
                            () -> getGreedyNonBusyServer(customers[event.getCustomerId() - 1] instanceof GreedyCustomer)
                                    .ifPresentOrElse(
                                            server -> eventQueue.add(
                                                    new WaitsEvent(event.getCustomer(), event.getEventTime(), server)),
                                            () -> eventQueue
                                                    .add(new LeavesEvent(event.getCustomer(), event.getEventTime()))));
                } else {
                    getAvailableServer().ifPresentOrElse(
                            server -> eventQueue
                                    .add(new ServedEvent(event.getCustomer(), event.getEventTime(), server)),
                            () -> getNonBusyServer().ifPresentOrElse(
                                    server -> eventQueue
                                            .add(new WaitsEvent(event.getCustomer(), event.getEventTime(), server)),
                                    () -> eventQueue.add(new LeavesEvent(event.getCustomer(), event.getEventTime()))));
                }
                /**
                 * Retrieve the current event's state that is Served state. Schedules the
                 * server's nextServiceTime by adding event time with service time generated by
                 * genServiceTime() method. A new event would be added into the eventQueue with
                 * updated Done state. Number of customers served will also be updated in the
                 * Statistics class. Lastly, the totalWaitingTime is taking the difference of
                 * the server's nextServiceTime and customer's arrival time, that would be
                 * updated in the Statistics class.
                 */
            } else if (event instanceof ServedEvent) {

                int serverIndex = event.getServerId() - 1;
                servers[serverIndex].serve(event.getEventTime() + generator.genServiceTime());

                eventQueue.add(new DoneEvent(event.getCustomer(), servers[serverIndex].getNextServiceTime(),
                        servers[serverIndex]));
                statistics.updateNumServed();
                statistics.totalWaitingTime(event.getEventTime() - customers[event.getCustomerId() - 1].getTime());

                /**
                 * Retrieve the current event's state that is Waits state. Add the current
                 * customer into the waitingQueue of the server.
                 */
            } else if (event instanceof WaitsEvent) {
                int serverIndex = event.getServerId() - 1;

                servers[serverIndex].waits(customers[event.getCustomerId() - 1]);

                /**
                 * Retrieve the current event's state that is Leaves state. Update the number of
                 * customers left in the Statistics class.
                 */
            } else if (event instanceof LeavesEvent) {

                statistics.updateNumLeft();

                /**
                 * Retrieve the current event's state that is Done state. Set the server's state
                 * to done serving. There are 2 conditions: (i) if the server is a HumanSever
                 * and its random number generated from genRandomRest() method is less than the
                 * probability of resting, the server rests with a new ServerRestEvent
                 * generated. (ii) else, the server continues serving the next customer.
                 */
            } else if (event instanceof DoneEvent) {
                int serverIndex = event.getServerId() - 1;
                servers[serverIndex].doneServing();

                if (!servers[serverIndex].isSelfCheckOut() && generator.genRandomRest() < Server.getProbabilityRest()) {
                    eventQueue
                            .add(new ServerRestEvent(event.getCustomer(), event.getEventTime(), servers[serverIndex]));
                } else {
                    servers[serverIndex].getNextCustomer().ifPresent(customer -> {
                        eventQueue.add(new ServedEvent(customer, event.getEventTime(), servers[serverIndex]));
                    });
                }

                /**
                 * Retrieve the current event's state that is ServerRestEvent state. Records the
                 * server's total time in its goToRest() method by adding the event time and
                 * restTime generated from genRestPeriod() method. Then, creates a new
                 * ServerBackEvent.
                 */
            } else if (event instanceof ServerRestEvent) {
                int serverIndex = event.getServerId() - 1;

                double restTime = generator.genRestPeriod();
                servers[serverIndex].goToRest(event.getEventTime() + restTime);
                eventQueue.add(new ServerBackEvent(event.getCustomer(), event.getEventTime() + restTime,
                        servers[serverIndex]));

                /**
                 * Retrieve the current event's state that is ServerBackEvent state. Set the
                 * state of the server to end of resting and the server serves the next customer
                 * in the queue immediately.
                 */
            } else if (event instanceof ServerBackEvent) {
                int serverIndex = event.getServerId() - 1;

                servers[serverIndex].backFromRest();
                servers[serverIndex].getNextCustomer().ifPresent(customer -> {
                    eventQueue.add(
                            new ServedEvent(customer, servers[serverIndex].getNextServiceTime(), servers[serverIndex]));
                });
            }
        }
    }

    /**
     * Return a print statement of the statistics system which consists of the
     * number of customers served, leaves and total waiting time to be counted into
     * average.
     */
    public void printStats() {
        System.out.println(statistics);
    }
}