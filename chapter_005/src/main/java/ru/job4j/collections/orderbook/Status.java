package ru.job4j.collections.orderbook;

/**
 * The statuses of orders.
 */
public enum Status {

    BUY {
        @Override
        public String toString() {
            return "BUY";
        }
    },
    SELL {
        @Override
        public String toString() {
            return "SELL";
        }
    };

    /**
     * Returns the status by the specified name.
     * Name's case ignored.
     * If there are not matches returns null.
     *
     * @param name - the name of status.
     * @return - the status or null.
     */
    public static Status getByName(String name) {
        Status result = null;
        if (BUY.toString().equalsIgnoreCase(name)) {
            result = BUY;
        } else if (SELL.toString().equalsIgnoreCase(name)) {
            result = SELL;
        }
        return result;
    }
}
