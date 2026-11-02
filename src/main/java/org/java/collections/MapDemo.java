package org.java.collections;

import java.util.*;

/**
 * CONCEPT: Map interface (key -> value pairs, unique keys)
 *
 * IMPLEMENTATIONS:
 *   HashMap           : no order, O(1), one null key, many null values, NOT thread-safe.
 *   LinkedHashMap     : insertion order (or access order -> LRU cache).
 *   TreeMap           : sorted by key, O(log n), no null key.
 *   Hashtable         : legacy, synchronized, no nulls.
 *   ConcurrentHashMap : modern thread-safe map (bucket-level locking / CAS).
 *   EnumMap, IdentityHashMap, WeakHashMap : special purpose.
 *
 * MODERN METHODS: getOrDefault, putIfAbsent, computeIfAbsent, merge, entrySet iteration.
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 3); map.put("banana", 5); map.put("apple", 10);   // overwrite
        map.put(null, 0);
        System.out.println(map + " size=" + map.size());
        System.out.println(map.get("apple") + " " + map.getOrDefault("kiwi", -1) + " " + map.containsKey("banana"));

        map.putIfAbsent("cherry", 7);
        map.merge("banana", 1, Integer::sum);                  // 5 + 1
        map.remove(null);

        for (Map.Entry<String, Integer> e : map.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());
        map.forEach((k, v) -> System.out.print(k + ":" + v + " "));
        System.out.println();

        // Word frequency (classic interview)
        String text = "to be or not to be";
        Map<String, Integer> freq = new TreeMap<>();
        for (String w : text.split(" ")) freq.merge(w, 1, Integer::sum);
        System.out.println(freq);

        // computeIfAbsent: grouping
        Map<Integer, List<String>> byLength = new TreeMap<>();
        for (String w : List.of("a", "bb", "cc", "d", "eee"))
            byLength.computeIfAbsent(w.length(), k -> new ArrayList<>()).add(w);
        System.out.println(byLength);

        // LRU cache using LinkedHashMap
        Map<Integer, String> lru = new LinkedHashMap<>(16, 0.75f, true) {
            @Override protected boolean removeEldestEntry(Map.Entry<Integer, String> e) { return size() > 2; }
        };
        lru.put(1, "a"); lru.put(2, "b"); lru.get(1); lru.put(3, "c");
        System.out.println("LRU keeps: " + lru.keySet());       // [1, 3]
    }
}
