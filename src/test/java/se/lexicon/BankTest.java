package se.lexicon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankTest {

    @Test
    void constructorStoresBankName() {
        Bank bank = new Bank("Lexicon Bank");

        assertEquals("Lexicon Bank", bank.getName());
    }

    @Test
    void processAllAccountsProcessesEveryAccountPolymorphically() {
        Bank bank = new Bank("Lexicon Bank");

        Account savings = new SavingsAccount("Alice", 1000, 0.02);
        Account checking = new CheckingAccount("Bob", 500, 25);
        Account loan = new LoanAccount("Charlie", 5000, 0.03);

        bank.addAccount(savings);
        bank.addAccount(checking);
        bank.addAccount(loan);

        bank.processAllAccounts();

        assertEquals(1020, savings.getBalance(), 0.001);
        assertEquals(475, checking.getBalance(), 0.001);
        assertEquals(5150, loan.getBalance(), 0.001);
    }
}