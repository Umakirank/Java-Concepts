package org.java.oops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CONCEPT: Immutable Class & Deep vs Shallow Copy
 *
 * DEFINITION: An object whose state cannot change after creation (String, Integer, LocalDate).
 *
 * HOW TO CREATE:
 *   1. Make class final.            2. Make all fields private final.
 *   3. No setters.                  4. Defensive copy of mutable fields in constructor AND getters.
 *
 * BENEFITS: thread-safe, safe as HashMap keys, cacheable.
 *
 * SHALLOW COPY : copies references (both objects share inner objects).
 * DEEP COPY    : copies inner objects too.
 */
public class ImmutableDemo {

    static final class Person {
        private final String name;
        private final List<String> hobbies;

        Person(String name, List<String> hobbies) {
            this.name = name;
            this.hobbies = new ArrayList<>(hobbies);       // defensive copy in
        }
        String getName() { return name; }
        List<String> getHobbies() { return Collections.unmodifiableList(hobbies); }  // safe out
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("chess", "music"));
        Person p = new Person("Neha", list);
        list.add("hacking");                               // does not affect Person
        System.out.println(p.getHobbies());
        try {
            p.getHobbies().add("x");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify: immutable");
        }
    }
}
