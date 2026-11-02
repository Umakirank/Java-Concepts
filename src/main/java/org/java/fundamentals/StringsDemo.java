package org.java.fundamentals;

/**
 * CONCEPT: Strings
 *
 * DEFINITION:
 *   String is an immutable sequence of characters. Its content never changes;
 *   every "modification" creates a new object.
 *
 * STRING POOL:
 *   Literals live in the String Constant Pool and are reused.
 *   "a" == "a" is true, but new String("a") creates a separate heap object.
 *
 * CLASSES:
 *   String        immutable, thread-safe
 *   StringBuilder mutable, NOT thread-safe, fast
 *   StringBuffer  mutable, thread-safe (synchronized), slower
 *
 * INTERVIEW:
 *   - Why immutable? security, hashCode caching, thread safety, pool sharing.
 *   - == compares references, equals() compares content, intern() returns the pooled instance.
 */
public class StringsDemo {
    public static void main(String[] args) {
        String s1 = "java";
        String s2 = "java";
        String s3 = new String("java");
        System.out.println("s1==s2 " + (s1 == s2));                 // true
        System.out.println("s1==s3 " + (s1 == s3));                 // false
        System.out.println("s1.equals(s3) " + s1.equals(s3));       // true
        System.out.println("s1==s3.intern() " + (s1 == s3.intern()));// true

        String text = "  Hello, Java World  ";
        System.out.println(text.trim().toUpperCase());
        System.out.println(text.contains("Java") + " " + text.indexOf("Java"));
        System.out.println(text.trim().substring(7));
        System.out.println(text.replace("Java", "Spring"));
        System.out.println(String.join("-", "a", "b", "c"));
        System.out.println("ab".repeat(3) + " " + "".isEmpty() + " " + "  ".isBlank());

        String name = "Tom";
        name.concat(" Cruise");           // result thrown away!
        System.out.println("name still " + name);

        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World").insert(0, ">> ").reverse();
        System.out.println(sb);

        System.out.println(String.format("Name: %s, Age: %d, Pi: %.2f", "Sam", 25, 3.14159));
        String block = """
                {
                  "name": "Sam"
                }
                """;                       // text block (Java 15+)
        System.out.print(block);
    }
}
