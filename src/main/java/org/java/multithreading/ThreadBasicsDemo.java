package org.java.multithreading;

/**
 * CONCEPT: Threads & Life Cycle
 *
 * DEFINITION:
 *   Process = running program (own memory). Thread = lightweight unit of execution inside a process
 *   (shares heap, has its own stack). Multithreading = running many threads concurrently.
 *
 * WAYS TO CREATE A THREAD:
 *   1. extends Thread              (uses up your single inheritance)
 *   2. implements Runnable         (preferred; no return value)
 *   3. Callable + Future/Executor  (returns value, can throw checked exception)
 *   4. Virtual threads (Java 21)   Thread.ofVirtual().start(...)  cheap threads for I/O heavy work
 *
 * LIFE CYCLE: NEW -> RUNNABLE -> (BLOCKED | WAITING | TIMED_WAITING) -> TERMINATED
 *
 * start() vs run(): start() creates a NEW thread; calling run() directly runs on the current thread.
 * join() waits for a thread to finish; sleep() pauses without releasing locks; yield() hints scheduler.
 * Daemon thread: background thread; JVM exits when only daemon threads remain (e.g. GC).
 * Never use stop(); use interrupt() and check isInterrupted().
 */
public class ThreadBasicsDemo {

    static class MyThread extends Thread {
        @Override public void run() { System.out.println("extends Thread -> " + getName()); }
    }

    public static void main(String[] args) throws Exception {
        MyThread t1 = new MyThread();
        System.out.println("state before start: " + t1.getState());
        t1.start();

        Thread t2 = new Thread(() -> System.out.println("Runnable lambda -> " + Thread.currentThread().getName()), "worker-2");
        t2.start();
        t1.join(); t2.join();                          // wait for both
        System.out.println("state after finish: " + t1.getState());

        // run() vs start()
        new Thread(() -> System.out.println("run() directly executes on: " + Thread.currentThread().getName())).run();

        // Interrupt
        Thread sleeper = new Thread(() -> {
            try { Thread.sleep(5000); }
            catch (InterruptedException e) { System.out.println("sleeper interrupted"); }
        });
        sleeper.start();
        sleeper.interrupt();
        sleeper.join();

        // Virtual thread (Java 21)
        Thread v = Thread.ofVirtual().start(() -> System.out.println("virtual thread, isVirtual=" + Thread.currentThread().isVirtual()));
        v.join();
    }
}
