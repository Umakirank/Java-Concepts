package org.java.collections;

import java.util.*;

/**
 * CONCEPT: List interface (ordered, allows duplicates, index-based)
 *
 * COLLECTION FRAMEWORK HIERARCHY:
 *   Iterable -> Collection -> List | Set | Queue          Map (separate, not a Collection)
 *
 * IMPLEMENTATIONS:
 *   ArrayList  : dynamic array. get O(1), add at end O(1) amortized, insert/remove middle O(n). Not thread-safe.
 *                Grows by 50% when full (default capacity 10).
 *   LinkedList : doubly linked list. insert/remove O(1) at known node, get O(n). Also a Deque.
 *   Vector     : legacy synchronized ArrayList. Stack extends Vector (legacy; use ArrayDeque).
 *   CopyOnWriteArrayList : thread-safe, copies array on write (good for read-heavy).
 *
 * INTERVIEW:
 *   - ArrayList vs LinkedList. ArrayList vs Vector.
 *   - Fail-fast iterator: modifying during iteration -> ConcurrentModificationException.
 *   - Arrays.asList = fixed-size; List.of = immutable.
 */
public class ListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Java"); list.add("Spring"); list.add("Java"); list.add(1, "Kotlin");
        System.out.println(list + " size=" + list.size());
        System.out.println(list.get(0) + " " + list.indexOf("Java") + " " + list.contains("Spring"));
        list.remove("Spring");
        list.remove(0);                          // by index
        System.out.println(list);

        // Remove while iterating: WRONG vs RIGHT
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        try {
            for (Integer n : nums) if (n % 2 == 0) nums.remove(n);
        } catch (ConcurrentModificationException e) {
            System.out.println("Fail-fast: ConcurrentModificationException");
        }
        nums.removeIf(n -> n % 2 == 0);          // correct way
        System.out.println(nums);

        List<Integer> fixed = Arrays.asList(1, 2, 3);
        fixed.set(0, 99);                        // allowed
        try { fixed.add(4); } catch (UnsupportedOperationException e) { System.out.println("asList is fixed-size"); }
        try { List.of(1).add(2); } catch (UnsupportedOperationException e) { System.out.println("List.of immutable"); }

        LinkedList<String> ll = new LinkedList<>(List.of("b", "c"));
        ll.addFirst("a"); ll.addLast("d");
        System.out.println(ll + " first=" + ll.getFirst());

        // Sublist, sort, reverse
        List<Integer> data = new ArrayList<>(List.of(5, 2, 8, 1));
        Collections.sort(data);
        System.out.println(data + " sub=" + data.subList(1, 3));
    }
}
