package questions.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final DoublyLinkedList<K, V> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.list = new DoublyLinkedList<>();
    }

    public synchronized V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        Node<K, V> node = cache.get(key);
        list.moveToFront(node);
        return node.value;
    }

    public synchronized void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.value = value;
            list.moveToFront(node);
        } else {
            if (cache.size() == capacity) {
                Node<K, V> lastNode = list.removeLast();
                if (lastNode != null) {
                    cache.remove(lastNode.key);
                }
            }
            Node<K, V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            list.addFirst(newNode);
        }
    }
}
