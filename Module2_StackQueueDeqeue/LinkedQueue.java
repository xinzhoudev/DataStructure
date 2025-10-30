package DataStructure.Module2_StackQueueDeqeue;
import DataStructure.Module1_ArrayList.LinkedPositionalList;

public class LinkedQueue<E> implements Queue<E> {
    private LinkedPositionalList<E> list = new LinkedPositionalList<>();
    public LinkedQueue(){};
    public int size() {return list.size();}
    public boolean isEmpty() {return list.isEmpty();}
    public void offer(E element) {list.addLast(element);}
    public void enQueue(E element) {list.addLast(element);}

    public E peek() {return (list.isEmpty())?null:list.first().getElement();}
    public E top() {return (list.isEmpty())?null:list.first().getElement();}
    public E first() {return (list.isEmpty())?null:list.first().getElement();}

    public E deQueue() {return (list.isEmpty())?null:list.remove(list.first());}
    public E poll() { return (list.isEmpty())?null:list.remove(list.first());}

}
