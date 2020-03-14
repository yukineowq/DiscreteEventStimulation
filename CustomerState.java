/** This class encapsulates all the customer states.
 * There are five main components: (i) Arrives, (ii) Served, (iii) Leaves,
 * (iv) Waits and (v) Done.
 *
 * @author yukineoweiqian
 * @version CS2030 AY19/20 Sem 2 Project #1
 */

enum CustomerState {

    /** To stimulate the act of a customer arriving.
     * @return arrives state.
     */
    Arrives {
        @Override
        public String toString() {
            return "arrives";
        }
    },
    
    /** To stimulate the start of service to a customer by a server.
     * @return served state.
     */
    Served {
        @Override
        public String toString() {
            return "served";
        }
    },
    
    /** To stimulate the act of customer leaving without without being served. 
     * @return leaves state.
     */
    Leaves {
        @Override
        public String toString() {
            return "leaves";
        }
    },
    
    /** To stimulate the act of customer waiting.
     * @return waits state.
     */
    Waits {
        @Override
        public String toString() {
            return "waits";
        }
    },
    
    /** To stimulate the completion of service to a customer by a server.
     * @return done state.
     */
    Done {
        @Override
        public String toString() {
            return "done";
        }
    }
}
