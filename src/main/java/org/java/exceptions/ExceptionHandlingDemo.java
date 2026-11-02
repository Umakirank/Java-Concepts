package org.java.exceptions;

/**
 * CONCEPT: Exception Handling
 *
 * DEFINITION: A mechanism to handle runtime problems so the program does not crash.
 *
 * HIERARCHY:  Throwable -> Error (OutOfMemoryError, StackOverflowError: do not catch)
 *                       -> Exception
 *                            -> Checked   (compiler forces handling): IOException, SQLException
 *                            -> Unchecked (RuntimeException): NullPointerException, ArithmeticException,
 *                                           IllegalArgumentException, ClassCastException ...
 *
 * KEYWORDS: try, catch, finally, throw (throw an object), throws (declare), try-with-resources.
 *
 * INTERVIEW:
 *   - finally always runs (except System.exit or JVM crash). A return in finally overrides try's return.
 *   - Multi-catch: catch (A | B e). Specific catch blocks must come before general ones.
 *   - try-with-resources auto-closes any AutoCloseable.
 *   - Custom exceptions: extend Exception (checked) or RuntimeException (unchecked).
 *   - final vs finally vs finalize: modifier vs block vs GC hook (deprecated).
 */
public class ExceptionHandlingDemo {

    static class InsufficientBalanceException extends Exception {          // custom checked
        InsufficientBalanceException(String msg) { super(msg); }
    }

    static class InvalidAgeException extends RuntimeException {            // custom unchecked
        InvalidAgeException(String msg) { super(msg); }
    }

    static class Resource implements AutoCloseable {
        final String name;
        Resource(String name) { this.name = name; System.out.println("open " + name); }
        @Override public void close() { System.out.println("close " + name); }
    }

    static void withdraw(double bal, double amt) throws InsufficientBalanceException {
        if (amt > bal) throw new InsufficientBalanceException("Need " + (amt - bal) + " more");
    }

    @SuppressWarnings("finally")
    static int finallyOverride() {
        try { return 1; } finally { return 2; }     // returns 2 (bad practice, interview trick)
    }

    public static void main(String[] args) {
        try {
            int r = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("finally always runs");
        }

        try {
            String s = null;
            s.length();
        } catch (NullPointerException | IllegalStateException e) {   // multi-catch
            System.out.println("Multi-catch: " + e.getClass().getSimpleName());
        }

        try {
            withdraw(100, 500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Checked custom: " + e.getMessage());
        }

        try {
            throw new InvalidAgeException("Age must be >= 18");
        } catch (InvalidAgeException e) {
            System.out.println("Unchecked custom: " + e.getMessage());
        }

        try (Resource r1 = new Resource("A"); Resource r2 = new Resource("B")) {   // closed in reverse
            System.out.println("using resources");
        }

        System.out.println("finallyOverride returns " + finallyOverride());

        try {                                                       // exception chaining
            try { Integer.parseInt("abc"); }
            catch (NumberFormatException e) { throw new RuntimeException("Wrapped", e); }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + " <- cause: " + e.getCause().getClass().getSimpleName());
        }
    }
}
