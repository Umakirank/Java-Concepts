package org.java.fundamentals;

/**
 * CONCEPT: Methods (Functions)
 *
 * DEFINITION:
 *   A reusable block of code that performs one task.
 *   Syntax: accessModifier returnType name(parameters) { body }
 *
 * IMPORTANT TOPICS:
 *   - Overloading: same name, different parameters (compile-time polymorphism).
 *   - Varargs: type... name; accepts 0 or more arguments; must be the LAST parameter.
 *   - Pass by value: Java ALWAYS passes a copy. For objects the copy is of the reference, so the
 *     callee can change the object's content but cannot re-point the caller's variable.
 *   - Recursion: method calls itself; needs a base case or StackOverflowError occurs.
 */
public class MethodsDemo {

    static int add(int a, int b) { return a + b; }
    static double add(double a, double b) { return a + b; }
    static int add(int a, int b, int c) { return a + b + c; }

    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) total += n;
        return total;
    }

    static void changePrimitive(int x) { x = 100; }
    static void changeArray(int[] arr) { arr[0] = 100; }
    static void reassign(int[] arr) { arr = new int[]{7, 7}; }

    static long factorial(int n) { return n <= 1 ? 1 : n * factorial(n - 1); }
    static int fib(int n) { return n < 2 ? n : fib(n - 1) + fib(n - 2); }

    public static void main(String[] args) {
        System.out.println(add(1, 2) + " " + add(1.5, 2.5) + " " + add(1, 2, 3));
        System.out.println("varargs: " + sum() + " " + sum(1, 2, 3, 4));

        int num = 5;
        changePrimitive(num);
        System.out.println("primitive unchanged: " + num);

        int[] arr = {1, 2};
        changeArray(arr);
        System.out.println("content changed: " + arr[0]);
        reassign(arr);
        System.out.println("reassign has no effect outside: " + arr[0]);

        System.out.println("5! = " + factorial(5) + ", fib(7) = " + fib(7));
    }
}
