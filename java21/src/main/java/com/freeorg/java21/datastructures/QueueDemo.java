package com.freeorg.java21.datastructures;

import java.util.*;

public class QueueDemo {
    public static void main(String[] args) {
        demonstrateLinkedListQueue();
        demonstratePriorityQueue();
        demonstrateArrayDeque();
    }

    /**
     * Demonstrates the basic FIFO (First-In, First-Out) behavior of a Queue
     * using a LinkedList implementation.
     */
    private static void demonstrateLinkedListQueue() {
        System.out.println("--- 1. LinkedList as a Queue (FIFO) ---");
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println("Initial queue: " + queue);
        System.out.println("Removed head (poll): " + queue.poll());
        System.out.println("Queue after poll: " + queue);
        System.out.println("----------------------------------------\n");
    }

    /**
     * Demonstrates the behavior of a PriorityQueue, which orders elements
     * based on natural order (min-heap) or a supplied comparator (max-heap).
     */
    private static void demonstratePriorityQueue() {
        System.out.println("--- 2. PriorityQueue (Heap Behavior) ---");
        // Min Heap - Natural Order
        System.out.println("\n* Min-Heap (Natural Order) *");
        var pQueue = new PriorityQueue<>();
        pQueue.add(30);
        pQueue.add(10);
        pQueue.add(20);
        System.out.println("Initial min-heap: " + pQueue);
        System.out.println("Removed smallest (poll): " + pQueue.poll());
        System.out.println("Min-heap after poll: " + pQueue);

        // Max Heap - Reverse Order
        System.out.println("\n* Max-Heap (Reverse Order) *");
        var pQueue2 = new PriorityQueue<>(Collections.reverseOrder());
        pQueue2.add(30);
        pQueue2.add(10);
        pQueue2.add(20);
        System.out.println("Initial max-heap: " + pQueue2);
        System.out.println("Removed largest (poll): " + pQueue2.poll());
        System.out.println("Max-heap after poll: " + pQueue2);
        System.out.println("----------------------------------------\n");
    }

    /**
     * Demonstrates the versatility of ArrayDeque, using it as both a
     * standard queue (FIFO) and a stack (LIFO).
     */
    private static void demonstrateArrayDeque() {
        System.out.println("--- 3. ArrayDeque (Queue and Stack) ---");
        var arrayDequeue = new ArrayDeque<Integer>();

        // --- Using ArrayDeque as a Queue (FIFO - First-In, First-Out) ---
        System.out.println("\n* As a Queue (FIFO) *");
        // offerLast() is equivalent to add() or addLast() for a queue
        arrayDequeue.offerLast(10); // Add to the tail
        arrayDequeue.offerLast(20);
        arrayDequeue.offerLast(30);
        System.out.println("Queue after offerLast: " + arrayDequeue);

        // peekFirst() lets you see the head element without removing it
        System.out.println("Peeking at the head: " + arrayDequeue.peekFirst());

        // pollFirst() removes and returns the head of the queue
        System.out.println("Polling the head (FIFO): " + arrayDequeue.pollFirst());
        System.out.println("Queue state: " + arrayDequeue);
        System.out.println("Polling the head (FIFO): " + arrayDequeue.pollFirst());
        System.out.println("Queue state: " + arrayDequeue);
        arrayDequeue.clear(); // Clear for the next example

        // --- Using ArrayDeque as a Stack (LIFO - Last-In, First-Out) ---
        System.out.println("\n* As a Stack (LIFO) *");
        // push() is the conventional stack method, equivalent to addFirst()
        arrayDequeue.push(100);
        arrayDequeue.push(200);
        arrayDequeue.push(300);
        System.out.println("Stack after push (addFirst): " + arrayDequeue);

        // peek() is equivalent to peekFirst(), showing the top of the stack
        System.out.println("Peeking at the top of the stack: " + arrayDequeue.peek());

        // pop() removes and returns the element at the top of the stack (the head)
        System.out.println("Popping the top (LIFO): " + arrayDequeue.pop());
        System.out.println("Stack state: " + arrayDequeue);
        System.out.println("Popping the top (LIFO): " + arrayDequeue.pop());
        System.out.println("Stack state: " + arrayDequeue);
        System.out.println("Final element: " + arrayDequeue.getFirst());
        System.out.println("Is Stack empty? " + arrayDequeue.isEmpty());
        System.out.println("----------------------------------------\n");
    }
}
