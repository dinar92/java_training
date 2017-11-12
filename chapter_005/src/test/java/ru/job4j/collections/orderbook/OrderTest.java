package ru.job4j.collections.orderbook;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsNot;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Tests the class Order.
 */
public class OrderTest {

    /**
     * The instance of base order.
     */
    Order order = new Order(Status.BUY, 1, 12.3, 5);

    /**
     * Tests equals(Order).
     */
    @Test
    public void whenEqualToAnotherOrderThenTrue() {
        Order anotherOrder = new Order(Status.BUY, 1, 12.3, 5);

        MatcherAssert.assertThat(order.equals(anotherOrder), is(true));
        MatcherAssert.assertThat(order.hashCode(), is(anotherOrder.hashCode()));
    }

    /**
     * Tests equals(Order).
     */
    @Test
    public void whenNotEqualToAnotherOrderThenFalse() {
        Order anotherOrder = new Order(Status.BUY, 1, 1.3, 5);

        MatcherAssert.assertThat(order.equals(anotherOrder), is(false));
        MatcherAssert.assertThat(order.hashCode(), IsNot.not(anotherOrder.hashCode()));
    }

    /**
     * Tests equals(Order).
     */
    @Test
    public void whenTypesIsDifferentThenFalse() {
           Book book = new OrderBook("notOrder");

           MatcherAssert.assertThat(order.equals(book), is(false));
    }

    /**
     * Tests equals(Order).
     */
    @Test
    public void whenIDIsDifferentThenFalse() {
        Order anotherOrder = new Order(Status.BUY, 2, 12.3, 5);

        MatcherAssert.assertThat(order.equals(anotherOrder), is(false));
    }


    @Test
    public void whenSetParametersInConstructorThenGetWithGetters() {
        Double price = 5.3;
        Integer volume = 5;

        order.setPrice(price);
        order.setVolume(volume);

        MatcherAssert.assertThat(order.getPrice(), is(price));
        MatcherAssert.assertThat(order.getVolume(), is(volume));
    }


}