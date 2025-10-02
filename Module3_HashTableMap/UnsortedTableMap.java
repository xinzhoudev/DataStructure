package DataStructure.Module3_HashTableMap;

import java.util.Iterator;
import java.util.NoSuchElementException;

import DataStructure.Module1_ArrayList.ArrayList;

public class UnsortedTableMap<K,V> extends AbstractMap<K,V>{
    // It can store the map of entries.
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
    // build an initially empty map.
    public UnsortedTableMap(){}
        // private utility.
        // Returns the index of an entry with equal key.
    private int findIndex(K key){
        int n = table.size();
        for(int j = 0; j < n; j++){
            if(table.get(j).getKey().equals(key)){
                return j;
            }
        }
        return -1;
    }
    public int size(){return table.size();}
    public V get(K key){
        int j = findIndex(key);
        if(j == -1) return null;
        return table.get(j).getValue();
    }
    // Associaated given value with given key, replacing a previous value(if any).
    public V put(K key, V value){
        int j = findIndex(key);
        if(j == -1){
            table.add(new MapEntry<K,V>(key, value));
            return null;
        }else{
            return table.get(j).setValue(value);
        }
    }

    // Removes the entry with the specific key (if any) and returns its value.
    public V remove(K key){
        int j = findIndex(key);
        int n = size();
        if(j == -1) return null;
        V answer = table.get(j).getValue();
        if(j != n-1){
            table.set(j, table.get(n-1));
        }
        table.remove(n-1);
        return answer;
    }
    // Support for the public entrySet method.
    private class EntryIterator implements Iterator<Entry<K,V>> {
        private int j = 0;
        public boolean hasNext(){return j < table.size();}
        public Entry<K,V> next(){
            if(j == table.size()) throw new NoSuchElementException();
            return table.get(j++);
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    private class EntryIterable implements Iterable<Entry<K,V>>{
        public Iterator<Entry<K,V>> iterator() {return new EntryIterator();}
    }
    public Iterable<Entry<K,V>> entrySet() {
        return new EntryIterable();
    }
    


}
