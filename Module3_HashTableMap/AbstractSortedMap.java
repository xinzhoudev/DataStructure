package DataStructure.Module3_HashTableMap;

public abstract class AbstractSortedMap<K,V> extends AbstractMap<K,V>{
    protected abstract int findIndex(K key, int low, int high);
    protected abstract int findIndex(K key);
    @Override public abstract int size();
    @Override public abstract V get(K key);
    @Override public abstract V put(K key, V value);
    @Override public abstract V remove(K key);
}
