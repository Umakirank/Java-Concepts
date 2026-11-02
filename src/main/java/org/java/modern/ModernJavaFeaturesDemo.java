package org.java.modern;

import java.util.List;
import java.util.Map;

/**
 * CONCEPT: Modern Java Features (Java 9 to 21) - quick revision sheet
 *
 *  9  : List.of / Set.of / Map.of (immutable), private interface methods, modules (JPMS)
 *  10 : var (local type inference)
 *  11 : String.isBlank/strip/lines/repeat, HttpClient, run single file: java Hello.java
 *  14 : switch expressions (arrow, yield)
 *  15 : text blocks """ """
 *  16 : records, pattern matching for instanceof
 *  17 : sealed classes (LTS)
 *  21 : pattern matching for switch, record patterns, virtual threads, sequenced collections (LTS)
 *
 *  LTS releases: 8, 11, 17, 21 (every 2 years). Know the difference between JDK 8 vs 17 features.
 */
public class ModernJavaFeaturesDemo {

    sealed interface Vehicle permits Car, Bike {}
    record Car(String brand, int doors) implements Vehicle {}
    record Bike(String brand) implements Vehicle {}

    static String describe(Object o) {
        return switch (o) {                                       // pattern matching for switch (21)
            case null -> "null!";
            case Integer i when i > 10 -> "big int " + i;
            case Integer i -> "small int " + i;
            case String s -> "string of length " + s.length();
            case Car(String brand, int doors) -> brand + " car with " + doors + " doors";   // record pattern
            case Bike b -> "bike " + b.brand();
            default -> "something else";
        };
    }

    public static void main(String[] args) {
        var list = List.of(1, 2, 3);
        var map = Map.of("a", 1, "b", 2);
        System.out.println(list + " " + map.size());

        int day = 3;
        String name = switch (day) {
            case 1, 2 -> "early";
            case 3 -> { String t = "mid"; yield t + "week"; }     // yield
            default -> "late";
        };
        System.out.println(name);

        Object o = "hello";
        if (o instanceof String s && s.length() > 3) System.out.println("pattern instanceof: " + s.toUpperCase());

        for (Object x : new Object[]{5, 50, "abc", new Car("Tata", 4), new Bike("Hero"), 2.5, null})
            System.out.println(describe(x));

        var seq = new java.util.ArrayList<>(List.of(1, 2, 3));
        System.out.println("Sequenced: first=" + seq.getFirst() + " last=" + seq.getLast() + " reversed=" + seq.reversed());
    }
}
