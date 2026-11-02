package org.java.oops;

/**
 * CONCEPT: Records (Java 16) and Sealed Classes (Java 17)
 *
 * RECORD:
 *   A compact immutable data carrier. The compiler generates constructor, getters (name()),
 *   equals, hashCode and toString. Fields are private final. Can have a compact constructor
 *   for validation, static methods and implement interfaces; cannot extend classes.
 *
 * SEALED:
 *   Restricts which classes may extend/implement a type using 'permits'.
 *   Subclasses must be final, sealed or non-sealed. Great for closed hierarchies + pattern matching.
 */
public class RecordAndSealedDemo {

    record Point(int x, int y) {
        Point {                                         // compact constructor
            if (x < 0 || y < 0) throw new IllegalArgumentException("negative");
        }
        double distance() { return Math.sqrt(x * x + y * y); }
    }

    sealed interface Shape permits Circle, Square {}
    record Circle(double r) implements Shape {}
    record Square(double side) implements Shape {}

    static double area(Shape s) {
        return switch (s) {                             // exhaustive, no default needed
            case Circle c -> Math.PI * c.r() * c.r();
            case Square q -> q.side() * q.side();
        };
    }

    public static void main(String[] args) {
        Point p = new Point(3, 4);
        System.out.println(p + " dist=" + p.distance() + " x=" + p.x());
        System.out.println(p.equals(new Point(3, 4)));
        System.out.printf("%.2f %.2f%n", area(new Circle(1)), area(new Square(2)));
    }
}
