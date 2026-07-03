package se.lexicon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    @Test
    void constructorStoresHolderAndBalance() {
        Account account = new SavingsAccount("Alice", 1000, 0.02);

        assertEquals("Alice", account.getHolder());
        assertEquals(1000, account.getBalance(), 0.001);
    }

    @Test
    void constructorRejectsBlankHolderName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingsAccount(" ", 1000, 0.02);
        });
    }

    @Test
    void constructorRejectsNegativeBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingsAccount("Alice", -100, 0.02);
        });
    }

    @Test
    void depositIncreasesBalance() {
        Account account = new SavingsAccount("Alice", 1000, 0.02);

        account.deposit(250);

        assertEquals(1250, account.getBalance(), 0.001);
    }

    @Test
    void depositRejectsNegativeAmount() {
        Account account = new SavingsAccount("Alice", 1000, 0.02);

        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-50);
        });
    }

    @Test
    void savingsAccountAddsInterestWhenMonthIsProcessed() {
        Account account = new SavingsAccount("Alice", 1000, 0.02);

        account.processMonth();

        assertEquals(1020, account.getBalance(), 0.001);
    }

    @Test
    void checkingAccountDeductsMonthlyFeeWhenMonthIsProcessed() {
        Account account = new CheckingAccount("Bob", 500, 25);

        account.processMonth();

        assertEquals(475, account.getBalance(), 0.001);
    }

    @Test
    void loanAccountAddsInterestDebtWhenMonthIsProcessed() {
        Account account = new LoanAccount("Charlie", 5000, 0.03);

        account.processMonth();

        assertEquals(5150, account.getBalance(), 0.001);
    }
}