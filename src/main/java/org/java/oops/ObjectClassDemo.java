package org.java.oops;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * CONCEPT: Object class - equals(), hashCode(), toString()
 *
 * DEFINITION:
 *   java.lang.Object is the root of every class. Key methods: toString, equals, hashCode,
 *   getClass, clone, finalize (deprecated), wait/notify/notifyAll.
 *
 * EQUALS & HASHCODE CONTRACT (very common interview question):
 *   1. If a.equals(b) is true then a.hashCode() == b.hashCode() MUST be true.
 *   2. Equal hashCodes do NOT imply equal objects (collision).
 *   3. Override BOTH together. HashMap / HashSet depend on them.
 */
public class ObjectClassDemo {

    static class Employee {
        private final int id;
        private final String name;
        Employee(int id, String name) { this.id = id; this.name = name; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee e = (Employee) o;
            return id == e.id && Objects.equals(name, e.name);
        }
        @Override public int hashCode() { return Objects.hash(id, name); }
        @Override public String toString() { return "Employee{id=" + id + ", name='" + name + "'}"; }
    }

    static class Bad {                  // equals overridden, hashCode NOT
        final int v; Bad(int v) { this.v = v; }
        @Override public boolean equals(Object o) { return o instanceof Bad b && b.v == v; }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee(1, "Ravi");
        Employee e2 = new Employee(1, "Ravi");
        System.out.println("== : " + (e1 == e2));               // false
        System.out.println("equals : " + e1.equals(e2));        // true
        System.out.println(e1);

        Set<Employee> good = new HashSet<>();
        good.add(e1); good.add(e2);
        System.out.println("Good set size (1 expected): " + good.size());

        Set<Bad> bad = new HashSet<>();
        bad.add(new Bad(1)); bad.add(new Bad(1));
        System.out.println("Bad set size (usually 2, BUG): " + bad.size());
    }
}
