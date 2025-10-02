package DataStructure.Module3_HashTableMap;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {
    protected int n = 0;        // number of the entries in the dictionary.
    protected int capacity;     // length of the table.
    private int prime;          // prime factor.
    private long scale, shift;  // the shift and scaling factors.
    public AbstractHashMap(int cap, int p){
        prime = p;
        capacity = cap;
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }
    public AbstractHashMap(int cap){this(cap, 109345121);}  // default prime.
    public AbstractHashMap(){this(17);}                   // default capacity.
    public int size(){return n;}
    public V get(K key) {return bucketGet(hashValue(key), key);}
    public V remove(K key) {return bucketRemove(hashValue(key), key);}
    public V put(K key, V value){
        V answer = bucketPut(hashValue(key), key, value);
        if(n > capacity/2){
            resize(2*capacity - 1);
        }
        return answer;
    }

    // private utilities
    // MAD: Multiply-Add-Divide
    private int hashValue(K key){
        return (int) ((Math.abs(key.hashCode()*scale+shift)%prime)%capacity);
    }
    private void resize(int newCap){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for(Entry<K,V> e:entrySet()){
            buffer.add(e);
        }
        capacity = newCap;
        createTable();
        n = 0;
        for(Entry<K,V> e:buffer){
            put(e.getKey(), e.getValue());
        }
    }
    // createTable: This method should create an initially empty table having size equal to a deisgnated capacity instance variable.
    protected abstract void createTable();
    // This method should mimic the semantics of the public put method, but for a key k that is known to hash to bucket h.
    protected abstract V bucketGet(int h, K k);
    // mimic the public put method, but for a key that is known to hash to bucket h.
    protected abstract V bucketPut(int h, K k, V v);
    // This method should mimic the semantics of the public remove method, but for a key k known to hash to bucket h.
    protected abstract V bucketRemove(int h, K k);
}
