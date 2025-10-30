package DataStructure.Module5_PriorityQueue;

import java.util.Comparator;
import DataStructure.Module1_ArrayList.ArrayList;

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();  
    public HeapPriorityQueue(){super();}
    public HeapPriorityQueue(Comparator<K> comp){
        super(comp);
    }
    // protected: the same package and different child class can access the function.
    // heap is a complete binary tree, which means you can find out specific node using the formula below.
    protected int parent(int j) {return (j-1)/2;}
    protected int left(int j) {return 2*j + 1;}
    protected int right(int j) {return 2*j + 2;}
    protected boolean hasLeft(int j) {return left(j) < heap.size();}
    protected boolean hasRight(int j) {return right(j) < heap.size();}

    protected void swap(int i, int j){
        Entry<K,V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Moves the entry at index j higher, if necessary, to restore the heap property.
    // The smaller value, the upper.
    protected void upHeap(int j){
        while(j > 0){
            int p = parent(j);
            if(compare(heap.get(j), heap.get(p)) >= 0) break;
            swap(j, p);
            j = p;
        }
    }

    // Move the entry at index j lower, if necessary, to restore the heap property.
    // after add elements or deletion, downheap can maintain the property of elements.
    protected void downHeap(int j){
        while(hasLeft(j)){
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if(hasRight(j)){
                int rightIndex = right(j);
                if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0){
                    smallChildIndex = rightIndex;
                }
            }
            if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0){
                break;
            }
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }



    // public methods.
    public int size(){
        return heap.size();
    }

    // Returns an Entry with minimum key.
    public Entry<K,V> min(){
        if(heap.isEmpty()) return null;
        return heap.get(0);
    }

    public Entry<K,V> root(){
        if(heap.isEmpty()) return null;
        return heap.get(0);
    }

    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        checkKey(key);
        Entry<K,V> newest = new PriorityQueueEntry<>(key, value);
        // insert the newest node into the last position.
        heap.add(newest);
        // upheap to restore the correct order.
        upHeap(heap.size() - 1);
        return newest;
    }

    // Remove and returns an entry with minimal key(if any).
    public Entry<K,V> removeMin(){
        if(heap.isEmpty()){
            return null;
        }
        Entry<K,V> answer = heap.get(0);
        swap(0, heap.size()-1);
        heap.remove(heap.size() - 1);
        downHeap(0);
        return answer;
    } 
    
}
