package DataStructure.Module2_StackQueueDeqeue;

public class ArrayQueue<E> implements Queue<E> {
    // instance variables.
    public static final int Capacity = 1000;
    private E[] queue;
    private int f = 0;
    private int sz = 0;
    public ArrayQueue(){
        this(Capacity);
    }
    public ArrayQueue(int capacity){
        queue = (E[]) new Object[capacity];
    }
    public int size(){
        return this.sz;
    }
    public void resize(int capacity){
        E[] temp = (E[]) new Object[capacity];
        for(int i = 0; i < sz; i++){
            temp[i] = queue[i];
        }
        queue = temp;
    }


    public boolean isEmpty(){
        return sz == 0;
    }
    public void enQueue(E e) throws IllegalStateException{
        if(sz == queue.length) throw new IllegalStateException("Queue is full");
        int avail = (f+sz) % queue.length;
        queue[avail] = e;
        sz++;
    }
    public E peek(){
        if(isEmpty()) return null;
        return queue[f];
    }

    public E deQueue(){
        if(isEmpty()) return null;
        E answer = queue[f];
        queue[f] = null;
        f = (f+1)%queue.length;
        sz--;
        return answer;
    }



}
