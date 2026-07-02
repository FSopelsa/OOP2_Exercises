![Lexicon Logo](https://lexicongruppen.se/media/wi5hphtd/lexicon-logo.svg)

# OOP

## Exercise 1 — The Bank

A bank manages three types of accounts: savings, checking, and loan. Every account has a holder name and a balance. But what happens at the end of each month is different — a savings account earns interest, a checking account is charged a monthly fee, and a loan account accumulates interest debt. The bank processes all accounts at month-end without caring which type each one is.

You will implement this system as one app with five classes: `Account`, `SavingsAccount`, `CheckingAccount`, `LoanAccount`, and `Bank`.

### Class Diagram

Study this diagram before you write any code. `Account` is `<<abstract>>` — a customer always opens a specific type of account, never a plain one.

```mermaid
classDiagram
    class Account {
        <<abstract>>
        - holder: String
        - balance: double
        + Account(holder, balance)
        + getHolder() String
        + getBalance() double
        + deposit(amount double)
        + processMonth()
        + printSummary()
    }
    class SavingsAccount {
        - interestRate: double
        + SavingsAccount(holder, balance, interestRate)
        + processMonth()
    }
    class CheckingAccount {
        - monthlyFee: double
        + CheckingAccount(holder, balance, monthlyFee)
        + processMonth()
    }
    class LoanAccount {
        - loanRate: double
        + LoanAccount(holder, balance, loanRate)
        + processMonth()
    }
    class Bank {
        - name: String
        - accounts: List~Account~
        + Bank(name)
        + addAccount(account Account)
        + processAllAccounts()
        + printAllSummaries()
    }
    Account <|-- SavingsAccount
    Account <|-- CheckingAccount
    Account <|-- LoanAccount
    Bank "1" --> "0..*" Account : manages
```

**Relationship types:** `<|--` is inheritance — all three account types share `holder` and `balance` from `Account`. `Bank --> Account` is aggregation — accounts exist independently of the bank object. `processMonth()` is declared `abstract` in `Account` — every subclass applies its own month-end rule.

### Implementation

**`Account`** (abstract)
- Constructor: reject a blank holder name or a negative balance
- `deposit(double amount)` — concrete; adds to balance, reject a negative amount
- `processMonth()` — declare as `abstract`; no body
- `printSummary()` — concrete; prints: `[holder] | Balance: [balance] kr`

**`SavingsAccount`**
- Constructor: call `super(holder, balance)`, store `interestRate`
- `processMonth()` — adds `balance * interestRate` to balance; prints: `[holder]: interest credited`

**`CheckingAccount`**
- Constructor: call `super(holder, balance)`, store `monthlyFee`
- `processMonth()` — deducts `monthlyFee` from balance; prints: `[holder]: monthly fee charged`

**`LoanAccount`**
- Constructor: call `super(holder, balance)`, store `loanRate`
- `processMonth()` — adds `balance * loanRate` to balance (debt grows); prints: `[holder]: loan interest added`

**`Bank`**
- `processAllAccounts()` — calls `processMonth()` on every account
- `printAllSummaries()` — calls `printSummary()` on every account

### Demonstrate in `App`

1. Create a bank named `"Lexicon Bank"`
2. Add a savings account (`1000 kr`, `2% rate`), a checking account (`500 kr`, `25 kr fee`), and a loan account (`5000 kr`, `3% rate`)
3. Call `printAllSummaries()` — print the starting balances
4. Call `processAllAccounts()` — each account applies its own month-end rule
5. Call `printAllSummaries()` again — show the updated balances

> **Think:** `processAllAccounts()` calls `processMonth()` on every account using a `List<Account>` — it never checks the type. How does Java know which version of `processMonth()` to run for each account? Why can you not write `new Account("Ali", 1000)`?

---

## Exercise 2 — University Departments

A university is made up of several departments — Computer Science, Business, and Design. Each department belongs to one university. If the university closes, its departments cease to exist with it.

Each department has a number of lecturers. A lecturer can teach across more than one department and may move between departments at the end of the year. If a department is dissolved, its lecturers do not disappear — they can be reassigned elsewhere in the university.

What is the relationship between `University` and `Department`? What is the relationship between `Department` and `Lecturer`? Draw a class diagram with the correct arrow types and multiplicity on both ends.

---

## Exercise 3 — Sports Club

A football club runs three teams: a first team, a reserve team, and a youth team. Each team belongs to the club — the First Team of Malmö FF has no meaning outside of Malmö FF. If the club shuts down, its teams cease to exist.

Players are assigned to a team. A player can be transferred to another club or loaned to a different team. If a team is disbanded, the players still exist — they remain registered with the club and can be reassigned.

What is the relationship between `Club` and `Team`? What is the relationship between `Team` and `Player`? Draw a class diagram with the correct arrow types and multiplicity on both ends.

---

## Exercise 4 — Customer Orders

When a customer places an order in an online shop, the order is made up of order lines — one line per product, recording what was ordered and how many units. If the order is cancelled and deleted, its order lines have no meaning and are removed with it.

The products in the catalog exist on their own. They were there before any order was placed and remain after orders are completed or cancelled.

What is the relationship between `Order` and `OrderLine`? What is the relationship between `OrderLine` and `Product`? What is the relationship between `Customer` and `Order`? Draw a class diagram with the correct arrow types and multiplicity on both ends.

---
