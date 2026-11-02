package org.java.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CONCEPT: Collections utility class, Immutable & Synchronized collections
 *
 * Collections: sort, reverse, shuffle, max, min, frequency, swap, binarySearch, unmodifiableXxx,
 *              synchronizedXxx, emptyList, singletonList.
 * Arrays     : see fundamentals.ArraysDemo.
 *
 * FAIL-FAST vs FAIL-SAFE ITERATORS:
 *   Fail-fast (ArrayList, HashMap)       : throw ConcurrentModificationException if modified during iteration.
 *   Fail-safe (CopyOnWriteArrayList, ConcurrentHashMap): iterate over a snapshot / weakly consistent view.
 *
 * COLLECTION vs COLLECTIONS: interface vs utility class.
 * HashMap vs ConcurrentHashMap vs Hashtable vs Collections.synchronizedMap: performance & lock granularity.
 */
public class CollectionsUtilityDemo {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(4, 2, 9, 2, 7));
        Collections.sort(nums);
        System.out.println(nums + " max=" + Collections.max(nums) + " min=" + Collections.min(nums)
                + " freq(2)=" + Collections.frequency(nums, 2));
        Collections.reverse(nums);
        Collections.swap(nums, 0, 1);
        System.out.println(nums);

        List<Integer> ro = Collections.unmodifiableList(nums);          // read-only VIEW
        try { ro.add(1); } catch (UnsupportedOperationException e) { System.out.println("read-only view"); }

        List<Integer> sync = Collections.synchronizedList(new ArrayList<>());
        sync.add(1);

        Map<String, Integer> chm = new ConcurrentHashMap<>();
        chm.put("a", 1);
        for (String k : chm.keySet()) chm.put("b", 2);                  // fail-safe: no exception
        System.out.println(chm);
    }
}
