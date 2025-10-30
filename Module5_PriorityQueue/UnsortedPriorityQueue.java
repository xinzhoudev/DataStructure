package DataStructure.Module5_PriorityQueue;

import java.util.Comparator;

import DataStructure.Module1_ArrayList.LinkedPositionalList;
import DataStructure.Module1_ArrayList.Position;


// It is time consuming because you have to spend a lot time to find the minimal element in the queue. Maybe O(n);
public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    // main collection of priority queue entries.
    private LinkedPositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
    // create an empty priority queue.
    public UnsortedPriorityQueue(){super();}
    public UnsortedPriorityQueue(Comparator<K> comp) {super(comp);}
    // returns the position of an entry having minimal key.
    private Position<Entry<K,V>> findMin(){
        Position<Entry<K,V>> small = list.first();
        for(Position<Entry<K,V>> walk : list.positions()){
            if(compare(walk.getElement(), small.getElement()) < 0){
                small = walk;
            }
        }
        return small;
    }
    // insert a key-value pair and returns the entry created.
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        checkKey(key);
        Entry<K,V> newest = new PriorityQueueEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }
    // returns (but does not remove) an entry with minimal key.
    public Entry<K,V> min(){
        if(list.isEmpty()) return null;
        return findMin().getElement();
    }
    // removes and returns an entry with minimal key.
    public Entry<K,V> removeMin(){
        if(list.isEmpty()) return null;
        return list.remove(findMin());
    }
    // return the size of the PQ.
    public int size(){return list.size();}
}
