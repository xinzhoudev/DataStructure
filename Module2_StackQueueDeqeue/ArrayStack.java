package DataStructure.Module2_StackQueueDeqeue;

public class ArrayStack<E> implements Stack<E> {
    public static final int Capacity = 1000;
    private E[] data;
    private int t = -1;
    public ArrayStack() {this(Capacity);} 
    public ArrayStack(int Capacity) {
        data = (E[]) new Object[Capacity];
    }
    public int size(){ 
        return (t+1);
    }
    public boolean isEmpty(){
        return (t == -1);
    }
    public void push(E e) throws IllegalStateException{
        if(size() == data.length){
            resize(this.size()*2);
        }
        data[++t] = e;
    }
    public E peek(){
        if(isEmpty()) return null;
        return data[t];
    }
    public E pop(){
        if(isEmpty()) return null;
        E answer = data[t];
        data[t] = null;
        t--;
        return answer;
    }
    // A generic method for reversing an array.
    public void reverse(E[] a){
        Stack<E> buffer = new ArrayStack<>();
        for(int i = 0; i < a.length; i++){
            buffer.push(a[i]);
        }
        for(int i = 0; i < a.length; i++){
            a[i] = buffer.pop();
        }
    }
    // if the size of stack is full, and it can extend or enlarge the size of stack automatically.
    public void resize(int capacity){
        E[] temp = (E[]) new Object[capacity];
        for(int i = 0; i < this.size(); i++){
            temp[i] = data[i];
        }
        data = temp;
    }

}
