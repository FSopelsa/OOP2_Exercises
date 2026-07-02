package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final String name;
    private final List<Account> accounts = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void processAllAccounts() {
        for (Account account : accounts) account.processMonth();
    }

    public void printAllSummaries() {
        for (Account account : accounts) account.printSummary();
    }
}