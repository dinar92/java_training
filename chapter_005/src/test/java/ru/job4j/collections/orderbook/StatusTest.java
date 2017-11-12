package ru.job4j.collections.orderbook;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Tests the enum Status.
 */
public class StatusTest {

    /**
     * Tests getByName(String).
     */
    @Test
    public void whenEnterNameThenReturnsStatus() {
        String buy = "buy";
        String sell = "sell";

        MatcherAssert.assertThat(Status.getByName(buy), is(Status.BUY));
        MatcherAssert.assertThat(Status.getByName(sell), is(Status.SELL));
    }

}