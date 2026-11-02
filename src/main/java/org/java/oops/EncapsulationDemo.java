package org.java.oops;

/**
 * CONCEPT: Encapsulation (OOP Pillar 1)
 *
 * DEFINITION:
 *   Wrapping data (fields) and the methods that work on it into one unit (class) and
 *   hiding the data from outside using private + public getters/setters.
 *
 * WHY: control access, validation, flexibility to change internals, security.
 *
 * ACCESS MODIFIERS (widest -> narrowest):
 *   public     : everywhere
 *   protected  : same package + subclasses
 *   (default)  : same package only
 *   private    : same class only
 */
public class EncapsulationDemo {

    static class BankAccount {
        private double balance;            // hidden
        private final String accountNo;

        BankAccount(String accountNo, double opening) {
            this.accountNo = accountNo;
            setBalance(opening);
        }

        public double getBalance() { return balance; }

        private void setBalance(double amount) {
            if (amount < 0) throw new IllegalArgumentException("Balance cannot be negative");
            this.balance = amount;
        }

        public void deposit(double amt) {
            if (amt <= 0) throw new IllegalArgumentException("Deposit must be positive");
            balance += amt;
        }

        public void withdraw(double amt) {
            if (amt > balance) throw new IllegalStateException("Insufficient funds");
            balance -= amt;
        }
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("AC-1", 1000);
        acc.deposit(500);
        acc.withdraw(200);
        System.out.println("Balance: " + acc.getBalance());
        // acc.balance = -5;  // compile error: private
        try {
            acc.withdraw(99999);
        } catch (IllegalStateException e) {
            System.out.println("Blocked by encapsulation rule: " + e.getMessage());
        }
    }
}
