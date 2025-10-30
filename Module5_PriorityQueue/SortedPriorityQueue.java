package DataStructure.Module5_PriorityQueue;

import java.util.Comparator;

import DataStructure.Module1_ArrayList.LinkedPositionalList;
import DataStructure.Module1_ArrayList.Position;

public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    private LinkedPositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
    // construct function.
    public SortedPriorityQueue(){super();}
    public SortedPriorityQueue(Comparator<K> comp) {super(comp);}
    // inserts a key-value pair and returns the entry created.
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        checkKey(key);
        Entry<K,V> newest = new PriorityQueueEntry<>(key, value);
        Position<Entry<K,V>> walk = list.last();
        // walk backwards, looking for a smaller key and insert walk into the position.
        while(walk != null && compare(newest, walk.getElement()) < 0){
            walk = list.before(walk);
        }
        if(walk == null) list.addFirst(newest);
        else list.addAfter(walk, newest);
        return newest;
    }

    public Entry<K,V> min(){
        if(list.isEmpty()) return null;
        return list.first().getElement();
    }
    // removes and returns an entry with minimal key.'
    public Entry<K,V> removeMin(){
        if(list.isEmpty()) return null;
        return list.remove(list.first());
    }
    // returns the number of items in the PrioirtyQueue.
    public int size(){
        return list.size();
    }

}
