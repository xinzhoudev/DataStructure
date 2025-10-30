package DataStructure.Module5_PriorityQueue;

import DataStructure.Module5_PriorityQueue.AbstractPriorityQueue.PriorityQueueEntry;

public interface AdaptablePriorityQueue<K,V> {
    public void remove(Entry<K,V> e);
    public void replaceKey(Entry<K,V> e, K key);
    public void replaceValue(Entry<K,V> e, V value);
}
