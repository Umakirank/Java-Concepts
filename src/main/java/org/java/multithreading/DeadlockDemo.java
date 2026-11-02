package org.java.multithreading;

/**
 * CONCEPT: Deadlock, Livelock, Starvation
 *
 * DEADLOCK: two or more threads wait forever for locks held by each other.
 * 4 CONDITIONS (all needed): mutual exclusion, hold and wait, no preemption, circular wait.
 *
 * PREVENTION: always acquire locks in the same global order, use tryLock with timeout,
 *   keep synchronized blocks small, prefer higher-level concurrent utilities.
 * LIVELOCK: threads keep reacting to each other and make no progress.
 * STARVATION: a thread never gets CPU/lock because others hog it.
 *
 * This demo shows the BAD (commented) and GOOD (ordered locks) version safely.
 */
public class DeadlockDemo {
    static final Object lockA = new Object();
    static final Object lockB = new Object();

    // BAD: t1 takes A then B, t2 takes B then A -> may deadlock
    // GOOD: both take A then B
    static void safeTask(String name) {
        synchronized (lockA) {
            System.out.println(name + " got A");
            synchronized (lockB) { System.out.println(name + " got B, work done"); }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> safeTask("T1"));
        Thread t2 = new Thread(() -> safeTask("T2"));
        t1.start(); t2.start(); t1.join(); t2.join();
        System.out.println("No deadlock because of consistent lock ordering");
    }
}
