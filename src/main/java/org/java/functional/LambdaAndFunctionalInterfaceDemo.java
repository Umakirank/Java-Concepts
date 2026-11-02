package org.java.functional;

import java.util.List;
import java.util.function.*;

/**
 * CONCEPT: Lambda Expressions, Functional Interfaces, Method References (Java 8)
 *
 * FUNCTIONAL INTERFACE: interface with exactly ONE abstract method (@FunctionalInterface).
 * LAMBDA: short anonymous function:  (params) -> expression  |  (params) -> { statements }
 *
 * BUILT-IN INTERFACES (java.util.function):
 *   Predicate<T>      T -> boolean      test()
 *   Function<T,R>     T -> R            apply()
 *   Consumer<T>       T -> void         accept()
 *   Supplier<T>       () -> T           get()
 *   BiFunction<T,U,R>, UnaryOperator<T>, BinaryOperator<T>
 *
 * METHOD REFERENCE: shorthand for a lambda that only calls a method.
 *   Static: Integer::parseInt | Instance: String::toUpperCase | Constructor: ArrayList::new
 *
 * INTERVIEW: lambdas can capture only effectively final local variables.
 */
public class LambdaAndFunctionalInterfaceDemo {

    @FunctionalInterface
    interface Calculator { int calc(int a, int b); }

    public static void main(String[] args) {
        Calculator add = (a, b) -> a + b;
        Calculator mul = (a, b) -> a * b;
        System.out.println(add.calc(2, 3) + " " + mul.calc(2, 3));

        Predicate<Integer> isEven = n -> n % 2 == 0;
        Function<String, Integer> length = String::length;
        Consumer<String> printer = System.out::println;
        Supplier<Double> random = Math::random;
        BiFunction<Integer, Integer, Integer> power = (b, e) -> (int) Math.pow(b, e);
        UnaryOperator<String> upper = String::toUpperCase;

        System.out.println(isEven.test(4) + " " + length.apply("lambda") + " " + power.apply(2, 10));
        printer.accept(upper.apply("consumer works"));
        System.out.println(random.get() < 1);

        // Composition
        Predicate<Integer> positive = n -> n > 0;
        System.out.println(isEven.and(positive).test(-2) + " " + isEven.or(positive).test(-2) + " " + isEven.negate().test(3));
        Function<Integer, Integer> doubleIt = x -> x * 2;
        Function<Integer, Integer> plusTen = x -> x + 10;
        System.out.println(doubleIt.andThen(plusTen).apply(5) + " " + doubleIt.compose(plusTen).apply(5));

        List.of("x", "y").forEach(printer);

        int base = 100;                                   // effectively final
        Function<Integer, Integer> addBase = v -> v + base;
        System.out.println(addBase.apply(5));
    }
}
