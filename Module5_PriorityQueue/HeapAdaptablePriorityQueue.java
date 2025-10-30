package DataStructure.Module5_PriorityQueue;

import java.util.Comparator;

import DataStructure.Module5_PriorityQueue.AbstractPriorityQueue.PriorityQueueEntry;

public class HeapAdaptablePriorityQueue<K,V> extends HeapPriorityQueue<K,V> implements AdaptablePriorityQueue<K,V> {
    private int index;
    // nested AdaptablePriorityQueueEntry class
    public static class AdaptablePriorityQueueEntry<K,V> extends PriorityQueueEntry<K,V>{
        private int index;
        public AdaptablePriorityQueueEntry(K key, V value, int j){
            super(key, value);
            index = j;
        }
        public int getIndex(){ return index;}
        public void setIndex(int j) { index = j;}
    }

    public HeapAdaptablePriorityQueue(){
        super();
    }
    public HeapAdaptablePriorityQueue(Comparator<K> comp) {super(comp);}

    // judge whether an Entry is in the heap or not.
    protected AdaptablePriorityQueueEntry<K,V> validate(Entry<K,V> entry) throws IllegalArgumentException{
        if(!(entry instanceof AdaptablePriorityQueueEntry)) throw new IllegalArgumentException("Invalid Entry");
        AdaptablePriorityQueueEntry<K,V> locator = (AdaptablePriorityQueueEntry<K,V>) entry;
        int j = locator.getIndex();
        if(j >= heap.size() || heap.get(j) != locator) throw new IllegalArgumentException("Invalid Entry"){
            return locator;
        }
    }

    // Exchanges the entries at indicies i and j of the array List.
    protected void swap(int i, int j){
        super.swap(i, j);
        ((AdaptablePriorityQueueEntry<K,V>) heap.get(i)).setIndex(i);
        ((AdaptablePriorityQueueEntry<K,V>) heap.get(j)).setIndex(j);
    }

    protected void bubble(int j){
        if(j > 0 && compare(heap.get(j), heap.get(parent(j))) < 0){
            upHeap(j);
        }else{
            downHeap(j);
        }
    }

    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        checkKey(key);
        Entry<K,V> newest = new AdaptablePriorityQueueEntry<>(key, value, heap.size());
        heap.add(newest);
        upHeap(heap.size()-1);
        return newest;
    }

    public void remove(Entry<K,V> entry) throws IllegalArgumentException{
        AdaptablePriorityQueueEntry<K,V> locator = validate(entry);
        int j = locator.getIndex();
        if(j == heap.size() - 1){
            heap.remove(heap.size() - 1);
        }else{
            swap(j, heap.size() - 1);
            heap.remove(heap.size() - 1);
            bubble(j);
        }
    }

    public void replaceKey(Entry<K,V> entry, K key) throws IllegalArgumentException{
        AdaptablePriorityQueueEntry<K,V> locator = validate(entry);
        checkKey(key);
        locator.setKey(key);
        bubble(locator.getIndex());
    }

    public void replaceValue(Entry<K,V> entry, V value) throws IllegalArgumentException{
        AdaptablePriorityQueueEntry<K,V> locator = validate(entry);
        locator.setValue(value);
    }


}
