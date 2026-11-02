package org.java.collections;

import java.util.*;

/**
 * CONCEPT: Set interface (NO duplicates)
 *
 * IMPLEMENTATIONS:
 *   HashSet       : backed by HashMap. No order. O(1) add/contains. Allows one null.
 *   LinkedHashSet : keeps INSERTION order. Slightly slower.
 *   TreeSet       : sorted (Red-Black tree). O(log n). No null. Needs Comparable/Comparator.
 *   EnumSet       : very fast set for enums.
 *
 * INTERVIEW:
 *   - How does HashSet detect duplicates? hashCode() then equals().
 *   - HashSet internally uses a HashMap where the element is the key and a dummy object is the value.
 */
public class SetDemo {
    public static void main(String[] args) {
        Set<Integer> hs = new HashSet<>(List.of(5, 1, 3, 1, 5));
        System.out.println("HashSet (no dup): " + hs);

        Set<String> lhs = new LinkedHashSet<>();
        lhs.add("z"); lhs.add("a"); lhs.add("m"); lhs.add("a");
        System.out.println("LinkedHashSet (insertion order): " + lhs);

        TreeSet<Integer> ts = new TreeSet<>(List.of(50, 10, 30, 20));
        System.out.println("TreeSet (sorted): " + ts);
        System.out.println("first=" + ts.first() + " last=" + ts.last()
                + " floor(25)=" + ts.floor(25) + " ceiling(25)=" + ts.ceiling(25)
                + " headSet(30)=" + ts.headSet(30));

        // Set operations
        Set<Integer> a = new HashSet<>(List.of(1, 2, 3, 4));
        Set<Integer> b = new HashSet<>(List.of(3, 4, 5));
        Set<Integer> union = new HashSet<>(a); union.addAll(b);
        Set<Integer> inter = new HashSet<>(a); inter.retainAll(b);
        Set<Integer> diff = new HashSet<>(a); diff.removeAll(b);
        System.out.println("union=" + union + " intersection=" + inter + " difference=" + diff);

        // Remove duplicates from list preserving order
        List<Integer> withDup = List.of(3, 1, 3, 2, 1);
        System.out.println(new ArrayList<>(new LinkedHashSet<>(withDup)));
    }
}
