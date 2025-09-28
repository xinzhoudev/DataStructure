package DataStructure.Module2_StackQueueDeqeue;

import java.util.LinkedList;

public class LinkedQueue<E> implements Queue<E> {
    private LinkedList<E> list = new LinkedList<>();
    public LinkedQueue(){};
    public int size() {return list.size();}
    public boolean isEmpty() {return list.isEmpty();}
    public void offer(E element) {list.addLast(element);}
    public E peek() {return list.first();}
    public E poll() { return list.removeFirst();}

}
