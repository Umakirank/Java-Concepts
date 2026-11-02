package org.java.collections;

import java.util.*;

/**
 * CONCEPT: Queue, Deque, PriorityQueue, Stack
 *
 * QUEUE : FIFO (First In First Out).  offer/add, poll/remove, peek/element.
 *         offer/poll/peek return null/false on failure; add/remove/element throw exceptions.
 * DEQUE : double-ended queue, works as queue AND stack. ArrayDeque is the best implementation
 *         (faster than LinkedList and legacy Stack). No nulls.
 * STACK : LIFO. Use Deque: push / pop / peek.
 * PRIORITYQUEUE : binary min-heap; head is the smallest (or per Comparator). Not FIFO. O(log n).
 */
public class QueueDequeDemo {
    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();
        q.offer("A"); q.offer("B"); q.offer("C");
        System.out.println("peek=" + q.peek() + " poll=" + q.poll() + " now=" + q);

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1); stack.push(2); stack.push(3);
        System.out.println("stack pop=" + stack.pop() + " peek=" + stack.peek());

        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerFirst(1); dq.offerLast(2); dq.offerFirst(0);
        System.out.println("deque=" + dq);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(List.of(5, 1, 8, 3));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(List.of(5, 1, 8, 3));
        System.out.println("min=" + minHeap.poll() + " max=" + maxHeap.poll());

        // Balanced parentheses (classic stack problem)
        System.out.println(isBalanced("{[()]}") + " " + isBalanced("{[(])}"));
    }

    static boolean isBalanced(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if ("({[".indexOf(c) >= 0) st.push(c);
            else {
                if (st.isEmpty()) return false;
                char o = st.pop();
                if ((c == ')' && o != '(') || (c == '}' && o != '{') || (c == ']' && o != '[')) return false;
            }
        }
        return st.isEmpty();
    }
}
