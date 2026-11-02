package org.java.collections;

import java.util.*;

/**
 * CONCEPT: Comparable vs Comparator, Iterator
 *
 * COMPARABLE (java.lang): natural order defined INSIDE the class. int compareTo(T o). One order only.
 * COMPARATOR (java.util): custom order defined OUTSIDE the class. int compare(T a, T b). Many orders.
 *   Return negative (a first), zero (equal), positive (b first).
 *
 * ITERATOR: hasNext(), next(), remove(). Only safe way to remove while iterating.
 *   ListIterator goes both directions. Enhanced for-each uses Iterator internally.
 */
public class ComparableComparatorDemo {

    static class Student implements Comparable<Student> {
        String name; int marks;
        Student(String n, int m) { name = n; marks = m; }
        @Override public int compareTo(Student o) { return Integer.compare(this.marks, o.marks); }  // natural: marks asc
        @Override public String toString() { return name + ":" + marks; }
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>(List.of(
                new Student("Zara", 80), new Student("Amit", 95), new Student("Bela", 80)));

        Collections.sort(list);                                           // Comparable
        System.out.println("natural: " + list);

        list.sort(Comparator.comparing((Student s) -> s.name));           // Comparator
        System.out.println("by name: " + list);

        list.sort(Comparator.comparingInt((Student s) -> s.marks).reversed().thenComparing(s -> s.name));
        System.out.println("marks desc, then name: " + list);

        Iterator<Student> it = list.iterator();
        while (it.hasNext()) if (it.next().marks < 90) it.remove();      // safe removal
        System.out.println("after iterator.remove: " + list);
    }
}
