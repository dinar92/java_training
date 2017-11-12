package ru.job4j.collections.orderbook;

/**
 * The order of a deal.
 */
public class Order {

    /**
     * Status of the order.
     */
    private final Status status;

    /**
     * ID of the order.
     */
    private final Integer id;

    /**
     * Price of the order.
     */
    private Double price;

    /**
     * Volume of the order.
     */
    private int volume;

    /**
     * Sets the base parameters of the order.
     * @param status - status.
     * @param id - ID.
     * @param price - price.
     * @param volume - volume.
     */
    public Order(Status status, Integer id, Double price, int volume) {
        this.status = status;
        this.id = id;
        this.price = price;
        this.volume = volume;
    }

    /**
     * Setter of price.
     * @param price - price.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Setter of volume.
     * @param volume - volume.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Getter of ID.
     * @return - ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter of status.
     * @return - status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Getter of price.
     * @return - price.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Getter of volume.
     * @return - volume.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj
     * argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Order))  {
            return false;
        }

        Order order = (Order) o;

        if (volume != order.volume) {
            return false;
        }
        if (status != order.status) {
            return false;
        }
        if (id != null ? !id.equals(order.id) : order.id != null) {
            return false;
        }
        return price != null ? price.equals(order.price) : order.price == null;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + volume;
        return result;
    }
}
