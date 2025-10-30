package DataStructure.Module2_StackQueueDeqeue;

public class StackWithQueue<E> implements Stack<E>{
    ArrayQueue<E> queue1;
    ArrayQueue<E> queue2;
    public StackWithQueue(){
        queue1 = new ArrayQueue<>();
        queue2 = new ArrayQueue<>(); 
    }
    public void push(E e){
        queue1.offer(e);
    }
    public E pop(){
        while(queue1.size() > 1){
            queue2.offer(queue1.poll());
        }
        E temp = queue1.poll();
        while(!queue2.isEmpty()){
            queue1.offer(queue2.poll());
        }
        return temp;
    }
    public E peek(){
        while(queue1.size() > 1){
            queue2.offer(queue1.poll());
        }
        E temp = queue1.poll();
        while(!queue2.isEmpty()){
            queue1.offer(queue2.poll());
        }
        queue1.offer(temp);
        return temp;
    }
    public int size(){
        return queue1.size();
    }
    public boolean isEmpty(){
        return queue1.isEmpty();
    }

}
