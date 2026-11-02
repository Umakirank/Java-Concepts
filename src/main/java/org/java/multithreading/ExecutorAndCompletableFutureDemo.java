package org.java.multithreading;

import java.util.List;
import java.util.concurrent.*;

/**
 * CONCEPT: Executor Framework, Callable/Future, CompletableFuture
 *
 * EXECUTOR / THREAD POOL: reuses a fixed set of threads instead of creating a thread per task.
 *   newFixedThreadPool(n) | newCachedThreadPool() | newSingleThreadExecutor()
 *   newScheduledThreadPool(n) | newVirtualThreadPerTaskExecutor() (Java 21)
 *   In production prefer new ThreadPoolExecutor(core, max, keepAlive, queue, rejectionPolicy).
 *
 * CALLABLE vs RUNNABLE: Callable returns a value and may throw checked exceptions.
 * FUTURE: handle to async result; get() BLOCKS.
 * COMPLETABLEFUTURE (Java 8): non-blocking async pipelines
 *   supplyAsync, thenApply, thenAccept, thenCompose (flatMap), thenCombine, allOf, anyOf, exceptionally.
 *
 * OTHER UTILITIES: CountDownLatch (wait for N events), CyclicBarrier, Semaphore (limit concurrency),
 *   BlockingQueue (producer-consumer), ConcurrentHashMap, ForkJoinPool.
 * Always shutdown() executors.
 */
public class ExecutorAndCompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        Future<Integer> f = pool.submit(() -> { Thread.sleep(100); return 42; });   // Callable
        System.out.println("Future result: " + f.get());

        List<Callable<String>> tasks = List.of(() -> "A", () -> "B", () -> "C");
        for (Future<String> r : pool.invokeAll(tasks)) System.out.print(r.get() + " ");
        System.out.println();

        // CompletableFuture pipeline
        CompletableFuture<String> cf = CompletableFuture
                .supplyAsync(() -> "user-1", pool)
                .thenApply(id -> id + "-profile")
                .thenCompose(p -> CompletableFuture.supplyAsync(() -> p + "-orders", pool));
        System.out.println(cf.get());

        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> 10, pool);
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> 20, pool);
        System.out.println("combined: " + a.thenCombine(b, Integer::sum).get());

        CompletableFuture<Object> failing = CompletableFuture
                .supplyAsync(() -> { throw new RuntimeException("boom"); }, pool)
                .handle((res, ex) -> ex != null ? "recovered from " + ex.getCause().getMessage() : res);
        System.out.println(failing.get());

        CompletableFuture.allOf(a, b).join();

        // CountDownLatch
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) pool.execute(latch::countDown);
        latch.await();
        System.out.println("latch released");

        // Producer-Consumer with BlockingQueue
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        Thread producer = new Thread(() -> {
            try { for (int i = 1; i <= 4; i++) queue.put(i); queue.put(-1); } catch (InterruptedException ignored) {}
        });
        producer.start();
        int v;
        while ((v = queue.take()) != -1) System.out.print("consumed " + v + " ");
        System.out.println();

        pool.shutdown();
        System.out.println("terminated: " + pool.awaitTermination(2, TimeUnit.SECONDS));
    }
}
