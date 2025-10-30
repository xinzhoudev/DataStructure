package DataStructure.Module2_StackQueueDeqeue;

public interface Queue<E> {
    int size();
    boolean isEmpty();
    // enQueue.
    void enQueue(E e);
    void offer(E e);
    E top();
    E first();
    E peek();
    // deQueue.
    E deQueue(); 
    E poll();
}
