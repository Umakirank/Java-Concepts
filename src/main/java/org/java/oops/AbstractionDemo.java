package org.java.oops;

/**
 * CONCEPT: Abstraction (OOP Pillar 4)
 *
 * DEFINITION: Hiding implementation details and showing only essential behaviour.
 *
 * TWO TOOLS:
 *   ABSTRACT CLASS : can have abstract + concrete methods, fields, constructors. Single inheritance.
 *                    Use when classes share state/code (IS-A with common base).
 *   INTERFACE      : contract of behaviour. Methods are public abstract by default; may have
 *                    default, static and private methods (Java 8/9). Fields are public static final.
 *                    A class can implement MANY interfaces. Use for capabilities (CAN-DO).
 *
 * INTERVIEW:
 *   - Cannot instantiate an abstract class or interface.
 *   - Functional interface = exactly one abstract method (used by lambdas).
 *   - Marker interface = empty interface (Serializable, Cloneable).
 */
public class AbstractionDemo {

    interface Payment {
        void pay(double amount);
        default void receipt() { System.out.println("Receipt generated"); }   // default method
        static boolean validate(double a) { return a > 0; }                   // static method
    }

    interface Refundable { void refund(double amount); }

    static abstract class Account {
        protected double balance;
        Account(double b) { balance = b; }
        abstract double interestRate();                       // abstract: subclass must implement
        double yearlyInterest() { return balance * interestRate(); }   // concrete
    }

    static class SavingsAccount extends Account {
        SavingsAccount(double b) { super(b); }
        double interestRate() { return 0.04; }
    }

    static class UpiPayment implements Payment, Refundable {     // multiple interfaces
        public void pay(double amount) { System.out.println("Paid " + amount + " via UPI"); }
        public void refund(double amount) { System.out.println("Refunded " + amount); }
    }

    public static void main(String[] args) {
        Payment p = new UpiPayment();                        // program to interface
        if (Payment.validate(100)) p.pay(100);
        p.receipt();
        ((Refundable) p).refund(50);

        Account acc = new SavingsAccount(10000);
        System.out.println("Interest: " + acc.yearlyInterest());
    }
}
