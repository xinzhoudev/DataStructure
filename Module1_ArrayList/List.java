package DataStructure.Module1_ArrayList;

// Define the list from scratch.
public interface List<E>{
    // This is a function that returns the number of elements in the list.
    int size();
    // Judge whether it is empyty or not.
    boolean isEmpty();
    // get the element of index i.
    E get(int i) throws IndexOutOfBoundsException;
    // Set the element in index i.
    E set(int i, E e) throws IndexOutOfBoundsException;
    // add the element in specific position.
    void add(int i, E e) throws IndexOutOfBoundsException;
    // delete the element in spefic position.
    E remove(int i) throws IndexOutOfBoundsException;
}




