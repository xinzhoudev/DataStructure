package DataStructure.Module2_StackQueueDeqeue;

public interface Stack<E> {
    int size();
    boolean isEmpty();
    void push(E e);
    E peek();
    E pop();
}
