package org.java.oops;

/**
 * CONCEPT: Nested / Inner Classes & Anonymous Classes
 *
 * TYPES:
 *   1. Static nested class : belongs to outer class, no outer instance needed.
 *   2. Inner (non-static)  : needs outer instance, can access outer private members.
 *   3. Local class         : declared inside a method.
 *   4. Anonymous class     : unnamed one-time implementation of class/interface.
 *                            (Lambdas replace anonymous classes for functional interfaces.)
 */
public class InnerClassesDemo {
    private final String secret = "outer-private";

    static class StaticNested { void hi() { System.out.println("static nested"); } }

    class Inner { void show() { System.out.println("inner sees " + secret); } }

    interface Greeting { void greet(); }

    public static void main(String[] args) {
        new StaticNested().hi();

        InnerClassesDemo outer = new InnerClassesDemo();
        InnerClassesDemo.Inner in = outer.new Inner();
        in.show();

        class Local { void run() { System.out.println("local class"); } }
        new Local().run();

        Greeting anon = new Greeting() {                    // anonymous
            @Override public void greet() { System.out.println("anonymous hello"); }
        };
        anon.greet();

        Greeting lambda = () -> System.out.println("lambda hello");   // same idea, shorter
        lambda.greet();
    }
}
