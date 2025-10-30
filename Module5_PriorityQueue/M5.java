package DataStructure.Module5_PriorityQueue;

import java.util.Comparator;
import DataStructure.Module1_ArrayList.ArrayList;
import DataStructure.Module1_ArrayList.List;

public class M5 {
    
    public static void main(String[] args){
        Comparator<Integer> cmp = (a,b)->{
            return a-b;
        };
        ArrayList<Integer> list = new ArrayList<>();
        list.add(20);
        list.add(-220);
        list.add(6000);
        list.add(40);
        list.add(-10);
        list.add(-11);
        list.add(-12);
        list.add(3600);
        list.add(-1000);
        list.add(30);
        list.add(455);
        // generate minHeap and maxHeap from the list.
        HeapBuilder hb = new HeapBuilder(list);
        while(hb.minHeap.min() != null){
            Entry<Integer,Node> entry = hb.minHeap.removeMin();
            System.out.println(entry.getKey() + " " + entry.getValue().data);
        }
        while(hb.maxHeap.min() != null){
            Entry<Integer,Node> entry = hb.maxHeap.removeMin();
            System.out.println(entry.getKey() + " " + entry.getValue().data);
        }
        System.out.println("The root data is " + hb.bst.root().data);
        BSTToHeapTransformer bstToHeap = new BSTToHeapTransformer(hb.bst);
        bstToHeap.BSTToMaxHeap();
        bstToHeap.BSTToMinHeap();
        System.out.println(bstToHeap.maxHeap.size() + " " + bstToHeap.minHeap.size());
        for(BST.Node node: bstToHeap.res){
            System.out.println("The result node is: " + node.data);
        }
        System.out.println("Get all of the contents of minHeap: ");
        System.out.println(bstToHeap.minHeap.min().getValue().data);
        System.out.println(bstToHeap.maxHeap.min().getValue().data);
        System.out.println("MaxHeap order (deleteMin or deleteMax):");
        while(!bstToHeap.minHeap.isEmpty()){
            System.out.println("The minHeap of the bst is: " + bstToHeap.minHeap.removeMin().getKey());
        }
        while(!bstToHeap.maxHeap.isEmpty()){
            System.out.println("The maxHeap of the bst is: " + bstToHeap.maxHeap.removeMin().getKey());
        }
    }     

}
class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
    Node(int data, Node left, Node right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

// Task-1: Given a list of integers, write a class HeapBuilder that takes the integer list;
// create a heap, and returns that heap as a binary tree. You have to implement both Max-Heap and Min-Heap.
class HeapBuilder{
    Comparator<Integer> cmp = (a,b)->{
        return b-a;
    };
    HeapPriorityQueue<Integer,Node> minHeap = new HeapPriorityQueue<>();
    HeapPriorityQueue<Integer,Node> maxHeap = new HeapPriorityQueue<>(cmp);
    BST bst;
    public HeapBuilder(List<Integer> list){
        bst = new BST((a,b)->{return a-b;});
        createMinHeap(list);
        createMaxHeap(list);
    }
    // return the root of the binary tree.
    void createMinHeap(List<Integer> values){
        // normal binary tree.
        for(int i:values){
            minHeap.insert(i,new Node(i));
            bst.insert(i);
        }
    }

    void createMaxHeap(List<Integer> values){
        for(int i:values){
            maxHeap.insert(i, new Node(i));
            // bst.insert(i);
        }
    }
}

class BSTToHeapTransformer {
    Comparator<Integer> cmp = (a,b)->{return Integer.compare(b,a);};
    HeapPriorityQueue<Integer,BST.Node> minHeap = new HeapPriorityQueue<>();
    HeapPriorityQueue<Integer,BST.Node> maxHeap = new HeapPriorityQueue<>(cmp);
    List<BST.Node> res;

    public BSTToHeapTransformer(BST bst){
        res = new ArrayList<>();
        getAllNodes(bst.root(), res);
    }

    // inorder
    void getAllNodes(BST.Node node, List<BST.Node> res){
        if(node == null) return;
        getAllNodes(node.left, res);
        res.add(node);
        getAllNodes(node.right, res);
    }

    void BSTToMinHeap(){
        for(BST.Node n:res){
            minHeap.insert(n.data, n);
        }
    }

    void BSTToMaxHeap(){
        for(BST.Node n:res){
            maxHeap.insert(n.data, n);
        }
    }

}

