package org.java.fundamentals;

/**
 * CONCEPT: Operators
 *
 * DEFINITION:
 *   Symbols that perform operations on variables and values.
 *
 * TYPES:
 *   Arithmetic  + - * / %        Relational  == != > < >= <=
 *   Logical     && || !          Assignment  = += -= *= /=
 *   Unary       ++ -- + - !      Ternary     condition ? a : b
 *   Bitwise     & | ^ ~ << >> >>>
 *
 * INTERVIEW:
 *   - && and || are short-circuit (right side skipped when result is known). & and | are not.
 *   - i++ (post): use then increment. ++i (pre): increment then use.
 *   - >> keeps the sign bit, >>> fills with zero.
 *   - Integer division truncates: 7/2 = 3.
 */
public class OperatorsDemo {
    public static void main(String[] args) {
        int a = 7, b = 2;
        System.out.println("a/b=" + (a / b) + " a%b=" + (a % b) + " (double) a/b=" + ((double) a / b));

        int i = 5;
        int post = i++;    // post = 5, i = 6
        int pre = ++i;     // i = 7, pre = 7
        System.out.println("post=" + post + " pre=" + pre + " i=" + i);

        int x = 0;
        boolean r1 = (x != 0) && (10 / x > 1);   // right side skipped, no exception
        System.out.println("short-circuit safe: " + r1);

        int age = 20;
        System.out.println(age >= 18 ? "Adult" : "Minor");

        int n = 5;                                  // 0101
        System.out.println("5 & 3 = " + (n & 3));   // 1
        System.out.println("5 | 3 = " + (n | 3));   // 7
        System.out.println("5 ^ 3 = " + (n ^ 3));   // 6
        System.out.println("5 << 1 = " + (n << 1)); // 10 (x2)
        System.out.println("-16 >> 2 = " + (-16 >> 2));
        System.out.println("-16 >>> 28 = " + (-16 >>> 28));

        int p = 10, q = 20;                         // swap without temp
        p = p ^ q; q = p ^ q; p = p ^ q;
        System.out.println("swapped p=" + p + " q=" + q);
        System.out.println("7 is " + ((7 & 1) == 0 ? "even" : "odd"));
    }
}
