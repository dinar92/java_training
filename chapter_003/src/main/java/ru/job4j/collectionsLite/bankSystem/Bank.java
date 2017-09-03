package ru.job4j.collectionsLite.bankSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pacman on 02.09.17.
 * The implementation of the bank storage.
 */
public class Bank {

    /**
     * The storage.
     */
    private Map<User, List<Account>> map = new HashMap<>();

    /**
     * Adds the new user.
     *
     * @param user new user.
     */
    public void addUser(User user) {
        this.map.put(user, new ArrayList<>());
    }

    /**
     * Removes the user.
     *
     * @param user the user.
     */
    public void deleteUser(User user) {
        this.map.remove(user);
    }

    /**
     * Adds the account to the user.
     *
     * @param user    user.
     * @param account account.
     */
    public void addAccountToUser(User user, Account account) {
        List<Account> accounts = this.map.get(user);
        if (!accounts.contains(account)) {
            accounts.add(account);
        }
    }

    /**
     * Removes the account from the specified user.
     *
     * @param user    user.
     * @param account account.
     */
    public void deleteAccountFromUser(User user, Account account) {
        List<Account> accounts = this.map.get(user);
        accounts.remove(account);
    }

    /**
     * Returns the user's accounts list.
     *
     * @param user - user.
     * @return the list of the accounts.
     */
    public List<Account> getUserAccounts(User user) {
        return this.map.get(user);
    }


    /**
     * Transfers money from srcUser to dstUser, form dstAccount to dst Account.
     * Returns false if one of specified users is not found;
     * if one of specified accounts is not found;
     * if in the dstAccount not enough money.
     *
     * @param srcUser    the source user.
     * @param srcAccount the source account.
     * @param dstUser    the destination user.
     * @param dstAccount the destination account.
     * @param amount     value for transfer.
     * @return the result of the transfer success.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = true;
        if (!(this.map.containsKey(srcUser) && this.map.containsKey(dstUser))) {
            result = false;
        } else if (!(this.map.get(srcUser).contains(srcAccount) && this.map.get(dstUser).contains(dstAccount))) {
            result = false;
        } else {
            try {
                srcAccount.addValue(-amount);
                dstAccount.addValue(amount);
            } catch (IllegalArgumentException ex) {
                result = false;
            }
        }
        return result;
    }
}
