package DataStructure.Module5_PriorityQueue;

import java.util.Comparator;

public class BST {
    // A nested binary search tree node.
    protected static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
            left = null;
            right = null;
        }
        public Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
        // accessor methods.
        public int get(){return data;}
        public Node getLeft(){return left;}
        public Node getRight(){return right;}
        // update methods.
        public void setData(int data){this.data = data;}
        public void setLeft(Node leftChild){left = leftChild;}
        public void setRight(Node rightChild){right = rightChild;}
    }
    // define the specific comparator for the BinarySearchTree.
    private Comparator<Integer> comp;
    private Node root;
    private int size = 0;
    public BST(Comparator<Integer> comp){
        this.comp = comp;
    }

    public int compare(int a, int b){
        return comp.compare(a, b);
    }
    public Node root(){
        return root;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){return size == 0;}
    // insert elements.
    public void insert(int data){
        root = insertBST(root, data);
    }
    // define the insert function
    private Node insertBST(Node node, int data){
        if(node == null){
            size++;
            return new Node(data);
        }
        if(compare(node.data, data) > 0){
            node.left = insertBST(node.left, data);
        }else if(compare(node.data, data) < 0){
            node.right = insertBST(node.right, data);
        }
        return node;
    }

}
