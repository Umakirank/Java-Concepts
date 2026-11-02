package org.java.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * CONCEPT: How HashMap works internally (TOP interview question)
 *
 * STRUCTURE: array of buckets (Node<K,V>[] table). Default capacity 16, load factor 0.75.
 *
 * put(key, value):
 *   1. hash = hashCode(key) with bit spreading ( h ^ (h >>> 16) ).
 *   2. index = (n - 1) & hash   (n is always a power of two).
 *   3. If bucket empty -> store node.
 *   4. Else collision: walk the chain; if equals(key) matches -> replace value; else append node.
 *   5. Java 8+: if a bucket chain reaches 8 nodes (and table >= 64) it becomes a balanced
 *      Red-Black tree (O(log n)); it converts back to a list at 6 nodes.
 *   6. When size > capacity * 0.75 -> RESIZE: capacity doubles and entries are redistributed.
 *
 * get(key): compute index, compare hash then equals along the bucket.
 *
 * WHY immutable keys (String, Integer)? If key's hashCode changes after insertion the entry is lost.
 * NOT thread-safe: concurrent puts can lose data. Use ConcurrentHashMap.
 */
public class HashMapInternalsDemo {

    static class BadKey {
        final int id; BadKey(int id) { this.id = id; }
        @Override public int hashCode() { return 1; }                 // all collide into one bucket
        @Override public boolean equals(Object o) { return o instanceof BadKey b && b.id == id; }
    }

    public static void main(String[] args) {
        int n = 16;
        for (String key : new String[]{"Java", "Spring", "Hibernate"}) {
            int h = key.hashCode();
            int spread = h ^ (h >>> 16);
            System.out.println(key + " hash=" + h + " -> bucket " + ((n - 1) & spread));
        }

        Map<BadKey, String> map = new HashMap<>();
        for (int i = 0; i < 5; i++) map.put(new BadKey(i), "v" + i);   // all in one bucket, still works
        System.out.println("Collisions handled, size=" + map.size() + ", get(3)=" + map.get(new BadKey(3)));
    }
}
