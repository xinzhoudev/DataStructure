package DataStructure.Module2_StackQueueDeqeue;

public class ArrayQueue<E> implements Queue<E> {
    // instance variables.
    public static final int Capacity = 1000;
    // use an Array to store the data of the queue.
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

    // I use the enqueue and offer the denote the add function.
    public void enQueue(E e) throws IllegalStateException{
        if(sz == queue.length) throw new IllegalStateException("Queue is full");
        // allocate the (f+sz)%queue.length to the avail, and set the new element e in that position.
        // if f + sz >= queue.length, the mod guarantees that the initial position can be reused.
        int avail = (f+sz) % queue.length;
        queue[avail] = e;
        sz++;
    }

    public void offer(E e) throws IllegalStateException{
        if(sz == queue.length) throw new IllegalStateException("Queue is full");
        int avail = (f+sz) % queue.length;
        queue[avail] = e;
        sz++;
    }


    // return the last data of the Array, f is the position of the first element.
    public E peek(){
        if(isEmpty()) return null;
        return queue[f];
    }

    public E top(){
        if(isEmpty()) return null;
        return queue[f];
    }

    public E first(){
        if(isEmpty()) return null;
        return queue[f];
    }

    // dequeue and poll have the same functions: to remove the top/first element in the queue.
    public E deQueue(){
        if(isEmpty()) return null;
        E answer = queue[f];
        queue[f] = null;
        f = (f+1)%queue.length;
        sz--;
        return answer;
    }

    public E poll(){
        if(isEmpty()) return null;
        E answer = queue[f];
        queue[f] = null;
        f = (f+1)%queue.length;
        sz--;
        return answer;
    }

}
