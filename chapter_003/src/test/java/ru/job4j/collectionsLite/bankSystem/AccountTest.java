package ru.job4j.collectionsLite.bankSystem;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNot;
import org.junit.Test;

/**
 * Created by pacman on 03.09.17.
 * Tests class Account.
 */
public class AccountTest {

    /**
     * Tests addValue(), in the normal mode.
     */
    @Test
    public void whenArgumentIsPositiveShouldIncreaseValue() {
        Account account = new Account(1);
        double money = 6.0;
        double expectBalance = 6.0;

        account.addValue(money);

        assertThat(account.getValue(), is(expectBalance));
    }

    /**
     * Tests addValue(), with an attempt is made to make a balance below zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenBalanceBelowZeroShouldException() {
        Account account = new Account(1);
        double makesBalanceLessThanZero = -1.0;

        account.addValue(makesBalanceLessThanZero);
    }

    /**
     * Tests equals(), where the argument is null.
     */
    @Test
    public void whenArgumentIsNullThenNotEquals() {
        Account account = new Account(1);
        Account account2 = null;
        boolean expectResult = false;

        assertThat(account.equals(account2), is(expectResult));
    }

    /**
     * Tests equals(), when argument is not Account.
     */
    @Test
    public void whenArgumentHasAnotherTypeThenNotEqual() {
        Account account = new Account(1);
        User user = new User("Mike", 1);
        boolean expectResult = false;

        assertThat(account.equals(user), is(expectResult));
    }

    /**
     * Tests equals(), when accounts isn't equal.
     */
    @Test
    public void whenAccountsIsNotEqualsThenReturnFalse() {
        Account account1 = new Account(1);
        Account account2 = new Account(2);
        boolean expectResult = false;

        assertThat(account1.equals(account2), is(expectResult));
    }

    /**
     * Tests equals(), when accounts is equal.
     */
    @Test
    public void whenAccountsIsEqualThenReturnTrue() {
        Account account1 = new Account(1);
        Account account2 = new Account(1);
        boolean expectResult = true;

        assertThat(account1.equals(account2), is(expectResult));
    }



    /**
     * Tests hashCode(), when Accounts is different.
     */
    @Test
    public void whenDifferentAccountsThenDifferentHash() {
        Account account1 = new Account(1);
        Account account2 = new Account(2);

        assertThat(account1.hashCode(), IsNot.not(account2.hashCode()));
    }

    /**
     * Tests hashCode(), when Accounts is identical.
     */
    @Test
    public void whenAccountsIdenticalThenTheSameHash() {
        Account account1 = new Account(1);
        Account account2 = new Account(1);

        assertThat(account1.hashCode(), is(account2.hashCode()));
    }
}
