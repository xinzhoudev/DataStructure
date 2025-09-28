package DataStructure.Module2_StackQueueDeqeue;

public class StackWithTwoQueue<E> {
    Queue<E> queueOne;
    Queue<E> queueTwo;
    public StackWithTwoQueue(){
        queueOne = new ArrayQueue<>();
        queueTwo = new ArrayQueue<>();
    }

    // All stack methods;
    public void push(E e){
        queueOne.enQueue(e);
    }

    // if you want to ge the top of the stack, you have to deQueue all irrelevant E in queueOne;
    public E pop(){
        int size1 = queueOne.size()-1;
        while(size1 > 0){
            queueTwo.enQueue(queueOne.deQueue());
            size1--;
        }
        E res = queueOne.deQueue();
        while(!queueTwo.isEmpty()){
            queueOne.enQueue(queueTwo.deQueue());
        }
        return res;
    }

    // Similar to pop(), and you can definitely use it to get the peek() and return it back to the queueOne.
    public E peek(){
        int size1 = queueOne.size()-1;
        while(size1 > 0){
            queueTwo.enQueue(queueOne.deQueue());
            size1--;
        }
        E res = queueOne.deQueue();
        queueTwo.enQueue(res);
        while(!queueTwo.isEmpty()){
            queueOne.enQueue(queueTwo.deQueue());
        }
        return res;
    }

    public int size(){
        return queueOne.size();
    }


}
