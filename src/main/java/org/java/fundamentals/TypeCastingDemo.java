package org.java.fundamentals;

/**
 * CONCEPT: Type Casting / Type Conversion
 *
 * DEFINITION:
 *   Converting a value of one data type into another.
 *
 * TYPES:
 *   1. Widening (implicit, safe):          byte -> short -> int -> long -> float -> double
 *   2. Narrowing (explicit, may lose data): double -> float -> long -> int -> short -> byte
 *
 * INTERVIEW:
 *   - byte + byte = int (arithmetic promotes to int).
 *   - Object casting (upcast/downcast) is in the oops package (PolymorphismDemo).
 */
public class TypeCastingDemo {
    public static void main(String[] args) {
        int i = 100;
        long l = i;
        double d = l;
        System.out.println("Widening: " + i + " -> " + l + " -> " + d);

        double pi = 9.99;
        int truncated = (int) pi;          // decimal dropped, NOT rounded
        System.out.println("Narrowing 9.99 -> " + truncated);

        byte wrapped = (byte) 130;         // wraps around
        System.out.println("130 as byte = " + wrapped);   // -126

        char ch = 'A';
        int code = ch;                     // 65
        char next = (char) (ch + 1);       // 'B'
        System.out.println(ch + " -> " + code + " -> " + next);

        byte b1 = 10, b2 = 20;
        int sum = b1 + b2;                 // byte + byte => int
        System.out.println("byte sum: " + sum);

        int parsed = Integer.parseInt("123");
        String text = String.valueOf(456);
        System.out.println((parsed + 1) + " and " + text + 1);   // 124 and 4561
    }
}
