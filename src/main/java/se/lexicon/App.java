package se.lexicon;


public class App {
    public static void main(String[] args) {
        Bank bank = new Bank("Lexicon Bank");

        bank.addAccount(new SavingsAccount("Alice", 1000, 0.02));
        bank.addAccount(new CheckingAccount("Bob", 500, 25));
        bank.addAccount(new LoanAccount("Charlie", 5000, 0.03));

        System.out.println("Starting balances:");
        bank.printAllSummaries();

        System.out.println();
        System.out.println("Month-end processing:");
        bank.processAllAccounts();

        System.out.println();
        System.out.println("Updated balances:");
        bank.printAllSummaries();
    }
}