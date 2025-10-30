package DataStructure.Module5_PriorityQueue;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
    // nested PriorityQueueEntry class.
    protected static class PriorityQueueEntry<K,V> implements Entry<K,V>{
        private K k;
        private V v;
        public PriorityQueueEntry(K key, V value){
            k = key;
            v = value;
        }
        // Methods of the Entry interface.
        public K getKey(){return k;} 
        public V getValue(){return v;}
        // utilities not exposed as part of the Entry interface.
        protected void setKey(K key){k = key;}
        protected void setValue(V value){v = value;}
    }

    // The comparator defining the ordering of keys in the priority queue.
    private Comparator<K> comp;
    // Creates an empty priority queue using the given comparator to order keys.
    protected AbstractPriorityQueue(Comparator<K> c){comp = c;}
    // Creates an empty priority queue based on the natural order of its keys.
    protected AbstractPriorityQueue() {this(new DefaultComparator<K>());}
    // Methods for comparing two entries according to key.
    protected int compare(Entry<K,V> a, Entry<K,V> b){
        return comp.compare(a.getKey(), b.getKey());
    }
    // determine whether a key is valid.
    protected boolean checkKey(K key) throws IllegalArgumentException{
        try{
            return (comp.compare(key, key) == 0);
        }catch(ClassCastException e){
            throw new IllegalArgumentException("Incompatible Key.");
        }
    }
    public boolean isEmpty() {return size() == 0;}
    
}
