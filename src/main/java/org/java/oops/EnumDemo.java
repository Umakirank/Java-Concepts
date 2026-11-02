package org.java.oops;

/**
 * CONCEPT: Enum
 *
 * DEFINITION: A special class representing a fixed set of constants. Type-safe, can have fields,
 *   constructors (always private), methods, and implement interfaces. Cannot extend a class.
 *
 * USEFUL METHODS: values(), valueOf(), name(), ordinal(), compareTo().
 * Best way to create a thread-safe Singleton: enum with a single INSTANCE.
 */
public class EnumDemo {

    enum Day { MON, TUE, WED }

    enum Level {
        LOW(1), MEDIUM(5), HIGH(10);
        private final int weight;
        Level(int w) { this.weight = w; }
        int getWeight() { return weight; }
    }

    enum Operation {
        ADD { int apply(int a, int b) { return a + b; } },
        MUL { int apply(int a, int b) { return a * b; } };
        abstract int apply(int a, int b);                 // constant-specific behaviour
    }

    public static void main(String[] args) {
        for (Day d : Day.values()) System.out.print(d + "(" + d.ordinal() + ") ");
        System.out.println();
        System.out.println(Level.valueOf("HIGH").getWeight());
        System.out.println(Operation.MUL.apply(3, 4));

        Day today = Day.TUE;
        switch (today) {
            case MON -> System.out.println("Start");
            case TUE -> System.out.println("Second day");
            default -> System.out.println("Other");
        }
    }
}
