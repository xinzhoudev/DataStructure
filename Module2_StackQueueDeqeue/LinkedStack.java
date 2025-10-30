package DataStructure.Module2_StackQueueDeqeue;
import DataStructure.Module1_ArrayList.LinkedPositionalList;



public class LinkedStack<E> implements Stack<E> {
    private LinkedPositionalList<E> list = new LinkedPositionalList<>();
    public LinkedStack(){}
    public int size(){return list.size();} 
    public boolean isEmpty(){return list.isEmpty();}
    public void push(E element) {list.addFirst(element);}
    public E peek(){return list.getFirst();}
    public E pop(){return list.removeFirst();}
}
