package org.java.oops;

/**
 * CONCEPT: Polymorphism (OOP Pillar 3)
 *
 * DEFINITION: "Many forms" - the same method name behaves differently.
 *
 * TWO TYPES:
 *   1. Compile-time (static) : METHOD OVERLOADING. Resolved by compiler using parameters.
 *   2. Runtime (dynamic)     : METHOD OVERRIDING. Resolved by JVM using the actual object type.
 *
 * RULES OF OVERRIDING: same name + parameters, return type same/covariant, access cannot be reduced,
 *   cannot override static/final/private methods, cannot throw broader checked exception.
 *
 * UPCASTING   : Parent p = new Child();  (automatic, safe)
 * DOWNCASTING : Child c = (Child) p;     (manual, may throw ClassCastException; check instanceof)
 *
 * INTERVIEW: fields and static methods are NOT polymorphic (hidden, not overridden).
 */
public class PolymorphismDemo {

    static class Shape {
        double area() { return 0; }
        static void info() { System.out.println("Shape static"); }
    }
    static class Circle extends Shape {
        double r = 2;
        @Override double area() { return Math.PI * r * r; }
        static void info() { System.out.println("Circle static"); }   // hiding, not overriding
        void roll() { System.out.println("Circle rolls"); }
    }
    static class Rectangle extends Shape {
        @Override double area() { return 4 * 5; }
    }

    static void print(int x) { System.out.println("int " + x); }
    static void print(String x) { System.out.println("String " + x); }
    static void print(Object x) { System.out.println("Object " + x); }

    public static void main(String[] args) {
        // Overloading (compile time)
        print(5); print("hi"); print(5.5);   // 5.5 boxes to Double -> Object

        // Overriding (runtime)
        Shape[] shapes = {new Circle(), new Rectangle()};
        for (Shape s : shapes) System.out.printf("%s area=%.2f%n", s.getClass().getSimpleName(), s.area());

        // Upcast / downcast
        Shape s = new Circle();
        if (s instanceof Circle c) c.roll();        // pattern matching instanceof (Java 16+)
        try {
            Rectangle bad = (Rectangle) s;           // wrong downcast
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught");
        }

        s.info();     // static -> uses REFERENCE type => Shape static
    }
}
