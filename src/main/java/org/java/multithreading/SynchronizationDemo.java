package org.java.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CONCEPT: Synchronization & Thread Safety
 *
 * RACE CONDITION: two threads read-modify-write shared data at the same time and corrupt it
 *   (count++ is NOT atomic: read, add, write).
 *
 * SOLUTIONS:
 *   synchronized   : keyword (method or block). One thread at a time per object monitor (intrinsic lock).
 *                    static synchronized locks the Class object.
 *   ReentrantLock  : explicit lock; tryLock, timeouts, fairness, multiple Conditions.
 *   volatile       : guarantees VISIBILITY and ordering of a variable between threads, but NOT atomicity.
 *   Atomic classes : AtomicInteger, AtomicLong... lock-free using CAS (Compare-And-Swap).
 *
 * wait()/notify()/notifyAll(): inter-thread communication; must be called inside synchronized block.
 * Java Memory Model: happens-before rules define visibility between threads.
 */
public class SynchronizationDemo {

    static int unsafeCount = 0;
    static int syncCount = 0;
    static final AtomicInteger atomicCount = new AtomicInteger();
    static final ReentrantLock lock = new ReentrantLock();
    static int lockCount = 0;
    static final Object monitor = new Object();

    static void run(Runnable r) throws InterruptedException {
        Thread a = new Thread(r), b = new Thread(r);
        a.start(); b.start(); a.join(); b.join();
    }

    public static void main(String[] args) throws Exception {
        run(() -> { for (int i = 0; i < 100_000; i++) unsafeCount++; });
        System.out.println("unsafe (expect 200000, usually less): " + unsafeCount);

        run(() -> { for (int i = 0; i < 100_000; i++) synchronized (monitor) { syncCount++; } });
        System.out.println("synchronized: " + syncCount);

        run(() -> { for (int i = 0; i < 100_000; i++) atomicCount.incrementAndGet(); });
        System.out.println("atomic: " + atomicCount.get());

        run(() -> {
            for (int i = 0; i < 100_000; i++) {
                lock.lock();
                try { lockCount++; } finally { lock.unlock(); }      // always unlock in finally
            }
        });
        System.out.println("ReentrantLock: " + lockCount);

        producerConsumer();
    }

    // wait / notify example
    static boolean ready = false;
    static void producerConsumer() throws InterruptedException {
        Thread consumer = new Thread(() -> {
            synchronized (monitor) {
                while (!ready) {                                      // always loop (spurious wakeups)
                    try { monitor.wait(); } catch (InterruptedException e) { return; }
                }
                System.out.println("consumer got data");
            }
        });
        consumer.start();
        Thread.sleep(100);
        synchronized (monitor) { ready = true; monitor.notifyAll(); }
        consumer.join();
    }
}
