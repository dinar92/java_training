package ru.job4j.collectionsLite.bankSystem;

/**
 * Created by pacman on 02.09.17.
 * The implementation of the bank account.
 */
public class Account {

    /**
     * Start value of the account.
     */
    private double value = 0.0;

    /**
     * The account's requisites.
     */
    private final int requisites;


    /**
     * Sets the account requisites.
     *
     * @param requisites - requisites.
     */
    public Account(int requisites) {
        this.requisites = requisites;
    }

    /**
     * Add to value the specified value.
     *
     * @param value the value to add.
     * @throws IllegalArgumentException if there was an attempt to assign a sum below zero.
     */
    public void addValue(double value) throws IllegalArgumentException {
        if (this.value + value < 0) {
            throw new IllegalArgumentException("The value can't be less than 0");
        }
        this.value += value;
    }

    /**
     * Returns the current value.
     *
     * @return the value.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Returns the requisites.
     * @return the requisites.
     */
    public int getRequisites() {
        return this.requisites;
    }

    /**
     * Equals.
     *
     * @param o the another object.
     * @return result of compare.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (value != account.value) {
            return false;
        }
        return requisites == account.requisites;
    }

    /**
     * Returns the hashcode.
     *
     * @return the hashcode.
     */
    @Override
    public int hashCode() {
        return requisites;
    }
}
