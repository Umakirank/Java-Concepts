package org.java.fundamentals;

/**
 * CONCEPT: Data Types & Variables
 *
 * DEFINITION:
 *   A variable is a named memory location. A data type tells Java what kind of
 *   value the variable can hold and how much memory it uses.
 *
 * TWO CATEGORIES:
 *   1. Primitive (8 types, hold the value directly):
 *        byte(8 bit) short(16) int(32) long(64) float(32) double(64) char(16) boolean
 *   2. Reference (class, interface, array, String, enum...): hold an ADDRESS of an object in heap.
 *
 * KEY POINTS / INTERVIEW:
 *   - Integer literal is int, decimal literal is double. Use L for long, f for float.
 *   - Each primitive has a Wrapper class (Integer, Double...). Auto-boxing/unboxing converts automatically.
 *   - Integer cache: values -128..127 are cached, so == works only in that range.
 *   - Local variables have NO default value; fields do (0, false, null).
 *   - var (Java 10+) = local type inference.
 */
public class DataTypesDemo {

    static int fieldDefault;          // default 0
    static String fieldString;        // default null

    public static void main(String[] args) {
        byte b = 10;
        short s = 1000;
        int i = 100_000;              // underscores for readability
        long l = 10_000_000_000L;
        float f = 3.14f;
        double d = 3.14159;
        char c = 'A';
        boolean flag = true;

        System.out.println("byte=" + b + ", short=" + s + ", int=" + i + ", long=" + l);
        System.out.println("float=" + f + ", double=" + d + ", char=" + c + ", boolean=" + flag);
        System.out.println("int max=" + Integer.MAX_VALUE + ", min=" + Integer.MIN_VALUE);
        System.out.println("Field defaults: " + fieldDefault + ", " + fieldString);

        Integer boxed = i;            // auto-boxing
        int unboxed = boxed;          // auto-unboxing
        System.out.println("Boxed=" + boxed + " Unboxed=" + unboxed);

        // Integer cache trap (very common interview question)
        Integer a = 127, bb = 127;
        Integer x = 128, y = 128;
        System.out.println("127 == 127 -> " + (a == bb));        // true (cached)
        System.out.println("128 == 128 -> " + (x == y));         // false
        System.out.println("128 equals 128 -> " + x.equals(y));  // true

        int overflow = Integer.MAX_VALUE + 1;
        System.out.println("Overflow: " + overflow);             // -2147483648

        var message = "Hello var";    // compiler infers String
        System.out.println(message);
    }
}
