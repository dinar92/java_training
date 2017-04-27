package ru.job4j.foodStorage;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by pacman on 21.04.17.
 * An implementation of any food.
 * Money is represented as double type.
 */
public class Food {

    /**
     * Food's name.
     */
    private String name;

    /**
     * Food's  expiry date.
     * Is presented as an object of Calendar.
     */
    private Calendar expiryDate;

    /**
     * Food's  create date.
     * Is presented as an object of Calendar.
     */
    private Calendar createDate;

    /**
     * Food's price.
     */
    private double price;

    /**
     * Food's discount.
     */
    private double discount = 0.0;

    /**
     * Default constructor sets name and price of a product.
     * @param name product's name.
     * @param price product's price.
     */
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns an expiry date.
     * @return an expiry date.
     */
    public Calendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets an expiry date.
     * @param year year
     * @param month month
     * @param day day
     */
    public void setExpiryDate(int year, int month, int day) {
        this.expiryDate = new GregorianCalendar(year, (month - 1), day);
    }

    /**
     * Returns a create date.
     * @return a create date.
     */
    public Calendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets a create date.
     * @param year year
     * @param month month
     * @param day day
     */
    public void setCreateDate(int year, int month, int day) {
        this.createDate = new GregorianCalendar(year, (month - 1), day);
    }

    /**
     * Returns a discount of a product in monetary terms.
     * @return discount.
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Sets a discount of a product in monetary terms.
     * @param discount discount.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Returns a degree of deterioration in percent terms.
     * @return a degree of deterioration.
     */
    public int degreeOfDeterioration() {
        double passedDays = (double) (new GregorianCalendar().getTimeInMillis() - this.getCreateDate().getTimeInMillis());
        double expirationDate = (double) (this.getExpiryDate().getTimeInMillis() - this.getCreateDate().getTimeInMillis());
        return (int) ((passedDays / expirationDate) * 100);
    }
}
