package ru.job4j.collectionsLite.bankSystem;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pacman on 03.09.17.
 * Tests the class Bank.
 */
public class BankTest {

    /**
     * The instance of the bank accounting system.
     */
    private final Bank bank = new Bank();

    /**
     * Tests addAccountToUser().
     */
    @Test
    public void whenAddAccountToUserThenUserHasAccount() {
        User user1 = new User("Mike", 1);
        Account account = new Account(1);
        this.bank.addUser(user1);
        this.bank.addAccountToUser(user1, account);

        assertThat(this.bank.getUserAccounts(user1), Matchers.contains(account));
    }

    /**
     * Tests deleteAccountFromUser().
     */
    @Test
    public void whenDeleteAccountFromUserShouldDelete() {
        User user2 = new User("John", 2);
        Account account = new Account(2);
        this.bank.addUser(user2);
        this.bank.addAccountToUser(user2, account);
        this.bank.deleteAccountFromUser(user2, account);

        assertThat(this.bank.getUserAccounts(user2), IsNot.not(Matchers.contains(account)));
    }

    /**
     * Tests transferMoney(), normal mode.
     */
    @Test
    public void whenTransferMoneyThenMoneyToAnotherUser() {
        User srcUser = new User("Mike", 3);
        User dstUser = new User("Lenny", 4);
        Account srcAccount = new Account(3);
        srcAccount.addValue(6.0);
        Account dstAccount = new Account(4);
        dstAccount.addValue(2.0);
        double expectBalance = 4.0;
        boolean success = true;

        bank.addUser(srcUser);
        bank.addUser(dstUser);
        bank.addAccountToUser(srcUser, srcAccount);
        bank.addAccountToUser(dstUser, dstAccount);

        assertThat(bank.transferMoney(srcUser, srcAccount, dstUser, dstAccount, 2.0), is(success));
        assertThat(dstAccount.getValue(), is(expectBalance));
    }

    /**
     * Tests transferMoney(), if the user is not in the bank.
     */
    @Test
    public void whenUserIsNotContainsInBankThenReturnFalse() {
        User srcUser = new User("Mike", 5);
        User dstUser = new User("Lenny", 4);
        Account srcAccount = new Account(5);
        srcAccount.addValue(6.0);
        Account dstAccount = new Account(4);
        dstAccount.addValue(2.0);
        boolean success = false;

        bank.addUser(srcUser);
        bank.addAccountToUser(srcUser, srcAccount);

        assertThat(bank.transferMoney(srcUser, srcAccount, dstUser, dstAccount, 2.0), is(success));
    }

    /**
     * Tests transferMoney(), if the account is not in the bank.
     */
    @Test
    public void whenAccountIsNotInBankThenReturnFalse() {
        User srcUser = new User("Mike", 6);
        User dstUser = new User("Lenny", 7);
        Account srcAccount = new Account(6);
        srcAccount.addValue(6.0);
        Account dstAccount = new Account(7);
        dstAccount.addValue(2.0);
        Account anotherAccount = new Account(8);
        boolean success = false;

        bank.addUser(srcUser);
        bank.addUser(dstUser);
        bank.addAccountToUser(srcUser, srcAccount);
        bank.addAccountToUser(dstUser, dstAccount);

        assertThat(bank.transferMoney(srcUser, srcAccount, dstUser, anotherAccount, 2.0), is(success));
    }

    /**
     * Tests transferMoney(), if insufficient funds.
     */
    @Test
    public void whenInsufficientFundsThenReturnFalse() {
        User srcUser = new User("Mike", 9);
        User dstUser = new User("Lenny", 10);
        Account srcAccount = new Account(9);
        srcAccount.addValue(6.0);
        Account dstAccount = new Account(10);
        dstAccount.addValue(2.0);
        boolean success = false;

        bank.addUser(srcUser);
        bank.addUser(dstUser);
        bank.addAccountToUser(srcUser, srcAccount);
        bank.addAccountToUser(dstUser, dstAccount);

        assertThat(bank.transferMoney(srcUser, srcAccount, dstUser, dstAccount, 10.0), is(success));
    }
}
