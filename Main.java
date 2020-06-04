import cs2030.simulator.ArriveEvent;
import cs2030.simulator.Customer;
import cs2030.simulator.CustomerComparator;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.Event;
import cs2030.simulator.EventComparator;
import cs2030.simulator.EventManager;
import cs2030.simulator.GreedyCustomer;
import cs2030.simulator.HumanServer;
import cs2030.simulator.LeavesEvent;
import cs2030.simulator.SelfCheckOut;
import cs2030.simulator.ServedEvent;
import cs2030.simulator.Server;
import cs2030.simulator.ServerBackEvent;
import cs2030.simulator.ServerID;
import cs2030.simulator.ServerRestEvent;
import cs2030.simulator.Statistics;
import cs2030.simulator.TypicalCustomer;
import cs2030.simulator.WaitsEvent;

/**
 * This class encapsulates the entry point for the execution of this Project #3
 * - Discrete Event Simulator Plus.
 * 
 * @author Yuki Neo Wei Qian
 * @version Cs2030 AY19/20 Sem 2 Project #3: Discrete Event Simulator Plus
 */

class Main {
    /**
     * Creates an EventManager to stimulate the execution of events.
     * 
     * @param args String[]
     */
    public static void main(String[] args) {

        EventManager event = new EventManager();

        event.eventMethod();

        event.startSimulation();

        event.printStats();
    }
}
