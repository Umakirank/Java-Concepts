package org.java.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * CONCEPT: Generics
 *
 * DEFINITION:
 *   Classes, interfaces and methods that work with a type parameter (T), giving
 *   compile-time type safety and removing casts. Example: List<String>.
 *
 * NAMING: T type, E element, K key, V value, N number.
 *
 * BOUNDED TYPES:  <T extends Number>  upper bound.
 * WILDCARDS:      ?  unknown | ? extends T  (read-only, producer) | ? super T (write, consumer)
 *                 PECS rule: Producer Extends, Consumer Super.
 *
 * TYPE ERASURE (interview): generics exist only at compile time; at runtime List<String> and
 *   List<Integer> are both just List. So no new T(), no T[] creation, no instanceof List<String>.
 *
 * List<Object> is NOT a parent of List<String> (generics are invariant).
 */
public class GenericsDemo {

    static class Box<T> {
        private T value;
        Box(T value) { this.value = value; }
        T get() { return value; }
    }

    static class Pair<K, V> {
        final K key; final V value;
        Pair(K k, V v) { key = k; value = v; }
        @Override public String toString() { return key + "=" + value; }
    }

    static <T extends Comparable<T>> T max(T a, T b) {       // generic method + bound
        return a.compareTo(b) >= 0 ? a : b;
    }

    static double sum(List<? extends Number> list) {         // producer: read only
        double s = 0;
        for (Number n : list) s += n.doubleValue();
        return s;
    }

    static void addIntegers(List<? super Integer> list) {    // consumer: write
        list.add(1); list.add(2);
    }

    public static void main(String[] args) {
        Box<String> b = new Box<>("hello");
        String s = b.get();                                  // no cast
        System.out.println(s + " " + new Pair<>("age", 30));
        System.out.println(max(10, 20) + " " + max("apple", "banana"));
        System.out.println(sum(List.of(1, 2.5, 3L)));

        List<Number> nums = new ArrayList<>();
        addIntegers(nums);
        System.out.println(nums);

        // Type erasure proof
        System.out.println(new ArrayList<String>().getClass() == new ArrayList<Integer>().getClass()); // true
    }
}
