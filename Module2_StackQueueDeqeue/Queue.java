package DataStructure.Module2_StackQueueDeqeue;

public interface Queue<E> {
    int size();
    boolean isEmpty();
    // enQueue.
    void enQueue(E e);
    E peek();
    // deQueue.
    E deQueue(); 
}
