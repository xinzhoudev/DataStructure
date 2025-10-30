package DataStructure.Module3_HashTableMap;

import java.util.LinkedList;

public class HashTable {
    // use linked list to implement the hash table.
    // use separate chaining to solve the confliction problems.
    private LinkedList<String>[] table;
    private int capacity;
    private int numOfElements;
    @SuppressWarnings("unchecked")
    public HashTable(int capacity){
        this.capacity = capacity;
        this.numOfElements = 0;
        table = new LinkedList[capacity];
        for(int i = 0; i < capacity; i++){
            table[i] = new LinkedList<>();
        }
    }

    // hash function, to change to key into the hashValue, convert a string into an integer.
    private int hash(String key){
        int hashValue = 0;
        for(int i = 0; i < key.length(); i++){
            hashValue = (hashValue<<5)|(hashValue>>27);
            hashValue += (int)key.charAt(i);
        }
        return hashValue;
    }

    // insert elements.
    public void insert(String key){
        // map the hash value into the capacity.
        int index = Math.abs(hash(key))%capacity;
        LinkedList<String> temp = table[index];
        if(!temp.contains(key)){
            temp.add(key);
            numOfElements++;
        }
    }

    // judge whether it contains the element.
    public boolean contains(String key){
        int index = Math.abs(hash(key))%capacity;
        LinkedList<String> temp = table[index];
        if(temp.contains(key)) return true;
        return false;
    }



    // return the size of HashTable.
    public int size(){
        return numOfElements;
    }

    // print the HashTable.
    public void printTable(){
        for(int i = 0; i < capacity; i++){
            if(!table[i].isEmpty()){
                for(String s:table[i]){
                    System.out.print(s + " ");
                }
            }
        }
        System.out.println();
    }


}
