package DataStructure.Module3_HashTableMap;

public interface Entry<K,V> {
    public K getKey();
    public V getValue();
    public V setValue(V value);
}
