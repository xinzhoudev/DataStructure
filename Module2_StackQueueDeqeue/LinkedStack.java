package DataStructure.Module2_StackQueueDeqeue;

import java.util.LinkedList;

public class LinkedStack<E> implements Stack<E> {
    private LinkedList<E> list = new LinkedList<>();
    public LinkedStack(){}
    public int size(){return list.size();} 
    public boolean isEmpty(){return list.isEmpty();}
    public void push(E element) {list.addFirst(element);}
    public E peek(){return list.getFirst();}
    public E pop(){return list.removeFirst();}
}
