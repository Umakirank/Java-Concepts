package org.java.interview;

/**
 * CONCEPT: Tricky "What is the output?" questions
 *
 * Interviewers love these. Read each snippet, predict the result, then run to verify.
 */
public class TrickyOutputQuestionsDemo {

    static class Parent {
        String name = "parent-field";
        Parent() { init(); }
        void init() { System.out.println("Parent.init"); }
    }
    static class Child extends Parent {
        String name = "child-field";
        int value = 5;
        @Override void init() { System.out.println("Child.init value=" + value); }   // value still 0 here!
    }

    static int tryFinally() {
        int x = 1;
        try { return x; } finally { x = 99; }     // returns 1, x change is lost
    }

    static void print(Object o) { System.out.println("Object"); }
    static void print(String s) { System.out.println("String"); }

    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);                          // 0.30000000000000004 (floating point)
        System.out.println(0.1f + 0.2f == 0.3f);
        System.out.println("" + 1 + 2 + " " + (1 + 2) + " " + 1 + 2);   // 12 3 12
        System.out.println('a' + 'b' + "c");                    // 195c
        System.out.println("c" + 'a' + 'b');                    // cab
        System.out.println((int) 'a' + " " + (char) 98);

        System.out.println(10 / 3 + " " + 10 % 3 + " " + -10 % 3 + " " + 10 / 3.0);
        System.out.println(Integer.MAX_VALUE + 1 == Integer.MIN_VALUE);
        System.out.println(Math.abs(Integer.MIN_VALUE));        // still negative
        System.out.println(Double.NaN == Double.NaN);           // false
        System.out.println(1.0 / 0);                            // Infinity (no exception for double)

        byte b = (byte) 200;
        System.out.println(b);                                  // -56
        short s = 10; s += 5;                                   // compound assignment does implicit cast
        System.out.println(s);

        int i = 0;
        i = i++ + ++i;                                          // 0 + 2
        System.out.println(i);

        String a = "hello", c = "hel" + "lo", d = new String("hello");
        String part = "hel"; String e = part + "lo";
        System.out.println((a == c) + " " + (a == d) + " " + (a == e) + " " + (a == e.intern()));

        print(null);                                            // most specific: String
        System.out.println(tryFinally());

        Parent p = new Child();
        System.out.println(p.name);                             // field access uses reference type
        System.out.println(((Child) p).name);

        System.out.println(new StringBuilder("ab").equals(new StringBuilder("ab")));   // false, no equals override

        Integer x1 = 1000, x2 = 1000;
        System.out.println(x1.equals(x2) + " " + (x1 == x2));
        System.out.println(Boolean.parseBoolean("TRUE") + " " + Boolean.parseBoolean("yes"));
    }
}
