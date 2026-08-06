package com.freeorg.java21.datastructures;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCacheLinkedHashMap(int capacity) {
        // initialCapacity, loadFactor, accessOrder = true (enables LRU behavior)
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // Removes the least recently used entry when size exceeds capacity
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheLinkedHashMap<Integer, String> cache = new LRUCacheLinkedHashMap<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C"); // Cache: [1=A, 2=B, 3=C]

        cache.get(1);      // Access 1 -> Cache: [2=B, 3=C, 1=A] (1 moved to most recent)

        cache.put(4, "D"); // Evicts key 2 -> Cache: [3=C, 1=A, 4=D]

        System.out.println(cache); // Output: {3=C, 1=A, 4=D}
    }
}