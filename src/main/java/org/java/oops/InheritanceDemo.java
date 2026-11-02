package org.java.oops;

/**
 * CONCEPT: Inheritance (OOP Pillar 2)
 *
 * DEFINITION:
 *   A child class acquires fields and methods of a parent class using 'extends'.
 *   Gives IS-A relationship and code reuse.
 *
 * TYPES: single, multilevel, hierarchical. Java does NOT support multiple inheritance with classes
 *        (diamond problem) but allows it through interfaces.
 *
 * KEYWORDS:
 *   super   -> refers to parent (super() calls parent constructor; must be first line).
 *   Parent constructor always runs BEFORE child constructor.
 *   Private members are not inherited. Constructors are not inherited.
 *
 * COMPOSITION (HAS-A) vs INHERITANCE (IS-A): prefer composition when the relation is not truly IS-A.
 */
public class InheritanceDemo {

    static class Animal {
        protected String name;
        Animal(String name) { this.name = name; System.out.println("Animal constructor"); }
        void eat() { System.out.println(name + " eats"); }
    }

    static class Dog extends Animal {
        Dog(String name) { super(name); System.out.println("Dog constructor"); }
        void bark() { System.out.println(name + " barks"); }
        @Override void eat() { super.eat(); System.out.println(name + " eats bones"); }
    }

    static class Puppy extends Dog {            // multilevel
        Puppy(String name) { super(name); }
        void weep() { System.out.println(name + " weeps"); }
    }

    // Composition (HAS-A)
    static class Engine { void start() { System.out.println("Engine started"); } }
    static class Car {
        private final Engine engine = new Engine();
        void drive() { engine.start(); System.out.println("Car moving"); }
    }

    public static void main(String[] args) {
        Puppy p = new Puppy("Bruno");
        p.eat(); p.bark(); p.weep();
        new Car().drive();
    }
}
