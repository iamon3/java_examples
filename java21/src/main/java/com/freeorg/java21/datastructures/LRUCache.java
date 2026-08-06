package com.freeorg.java21.datastructures;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C"); // Cache: [1=A, 2=B, 3=C]

        cache.get(1);      // Access 1 -> Cache: [2=B, 3=C, 1=A] (1 moved to most recent)

        cache.put(4, "D"); // Evicts key 2 -> Cache: [3=C, 1=A, 4=D]

        System.out.println(cache); // Output: {3=C, 1=A, 4=D}
    }

    // Node structure for Doubly Linked List
    private class Node {
        K key;
        V value;
        Node prev, next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<K, Node> map;
    private final Node head, tail; // Dummy head and tail nodes

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        // Dummy head and tail nodes to avoid edge-case checks
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node node = map.get(key);
        moveToHead(node); // Accessing key makes it Most Recently Used
        return node.value;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (map.size() >= capacity) {
                // Evict Least Recently Used item (node right before tail)
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            addNodeToHead(newNode);
            map.put(key, newNode);
        }
    }

    // --- Helper Methods ---

    private void addNodeToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNodeToHead(node);
    }

    /**
     * Returns a string representation of the cache, from most-recently-used to least-recently-used.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Node current = head.next;
        while (current != tail) {
            sb.append(current.key).append("=").append(current.value);
            if (current.next != tail) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("}");
        return sb.toString();
    }
}