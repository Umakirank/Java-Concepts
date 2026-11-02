package org.java.advanced;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * CONCEPT: JVM Architecture, Memory Model, Garbage Collection
 *
 * JDK vs JRE vs JVM: JDK = JRE + dev tools (javac). JRE = JVM + libraries. JVM runs bytecode (platform independent).
 * "Write once, run anywhere": source -> javac -> bytecode (.class) -> JVM -> machine code (JIT).
 *
 * JVM MEMORY AREAS:
 *   Heap       : all objects (shared). Young Gen (Eden + S0 + S1) -> Old Gen (tenured).
 *   Stack      : per thread; stack frames hold local variables & method calls.
 *   Metaspace  : class metadata (replaced PermGen in Java 8), uses native memory.
 *   PC Register / Native Method Stack.
 *
 * GARBAGE COLLECTION:
 *   An object is eligible when no live reference reaches it (reachability from GC roots).
 *   Minor GC cleans Young gen, Major/Full GC cleans Old gen. "Stop-the-world" pauses.
 *   Collectors: Serial, Parallel, G1 (default since Java 9), ZGC & Shenandoah (low pause).
 *   System.gc() is only a hint.
 *
 * REFERENCE TYPES: Strong (default), Soft (cleared when memory low - caches), Weak (cleared at next GC
 *   - WeakHashMap), Phantom.
 *
 * ERRORS: OutOfMemoryError (heap full / leak), StackOverflowError (infinite recursion).
 * MEMORY LEAK examples: static collections growing forever, unclosed resources, listeners never removed.
 *
 * Useful flags: -Xms -Xmx (heap), -Xss (stack), -XX:+UseG1GC. Tools: jps, jstack, jmap, jconsole, VisualVM.
 */
public class JvmMemoryAndGcDemo {

    static int depth = 0;
    static void recurse() { depth++; recurse(); }

    public static void main(String[] args) throws Exception {
        Runtime rt = Runtime.getRuntime();
        System.out.println("Max heap MB: " + rt.maxMemory() / (1024 * 1024));
        System.out.println("Processors : " + rt.availableProcessors());

        Object strong = new Object();
        WeakReference<Object> weak = new WeakReference<>(new Object());
        System.gc();
        Thread.sleep(100);
        System.out.println("strong alive: " + (strong != null) + ", weak cleared: " + (weak.get() == null));

        try { recurse(); }
        catch (StackOverflowError e) { System.out.println("StackOverflowError at depth > 1000: " + (depth > 1000)); }

        try {
            List<long[]> hog = new ArrayList<>();
            while (true) hog.add(new long[10_000_000]);
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError caught: " + e.getMessage());
        }
    }
}
