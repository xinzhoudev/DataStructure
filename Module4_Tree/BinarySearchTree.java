package DataStructure.Module4_Tree;

import java.util.Comparator;
import java.util.Iterator;

import DataStructure.Module1_ArrayList.LinkedPositionalList;
import DataStructure.Module2_StackQueueDeqeue.ArrayQueue;
import DataStructure.Module2_StackQueueDeqeue.Queue;

public class BinarySearchTree<E> extends AbstractBinaryTree<E> {
    // A nested binary search tree node.
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        public Node(E e){
            element = e;
            parent = null;
            left = null;
            right = null;
        }
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild){
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild; 
        }
        // accessor methods.
        public E getElement(){return element;}
        public Node<E> getParent(){return parent;}
        public Node<E> getLeft(){return left;}
        public Node<E> getRight(){return right;}
        // update methods.
        public void setElement(E e){element = e;}
        public void setParent(Node<E> parentNode){parent = parentNode;}
        public void setLeft(Node<E> leftChild){left = leftChild;}
        public void setRight(Node<E> rightChild){right = rightChild;}
    }
    // define the specific comparator for the BinarySearchTree.
    private Comparator<E> comp;
    private Node<E> root;
    private int size = 0;
    public BinarySearchTree(Comparator<E> comp){
        this.comp = comp;
    }

    public int compare(E a, E b){
        return comp.compare(a, b);
    }

    // store the root node.
    public Node<E> root(){
        return root;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){return size == 0;}
    // insert elements.
    public void insert(E element){
        root = insertBST(root, element);
    }
    // define the insert function, insert the E element into the Node<E> node.
    private Node<E> insertBST(Node<E> node, E element){
        if(node == null){
            size++;
            return new Node<>(element);
        }
        // if the new element's value < node's value: insert into the left, else insert into the right.
        if(compare(node.getElement(), element) > 0){
            node.setLeft(insertBST(node.getLeft(), element));
        }else if(compare(node.getElement(), element) < 0){
            node.setRight(insertBST(node.getRight(), element));
        }
        return node;
    }

    public boolean contains(E element){
        return containsBST(root, element);
    }

    private boolean containsBST(Node<E> node, E element){
        if(node == null) return false;
        // search the BST according to the comparing results.
        if(compare(node.getElement(), element) < 0){
            return containsBST(node.getLeft(), element);
        }else if(compare(node.getElement(), element) > 0){
            return containsBST(node.getRight(), element);
        }else return true;
    }

    public boolean search(E element){
        return searchBST(root, element);
    }

    private boolean searchBST(Node<E> node, E element){
        if(node == null) return false;
        if(compare(node.getElement(), element) < 0){
            return searchBST(node.getLeft(), element);
        }else if(compare(node.getElement(), element) > 0){
            return searchBST(node.getRight(), element);
        }else return true;
    }


    protected Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof Node)){
            throw new IllegalArgumentException("Not valid position type");
        }
        Node<E> node = (Node<E>) p;
        if(node.getParent() == node){
            throw new IllegalArgumentException();
        }
        return node;
    }

    public Position<E> parent(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getParent();
    }

    public Position<E> left(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getLeft();
    }

    public Position<E> right(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getRight();
    }

    private void preorderSubtree(Position<E> p, LinkedPositionalList<Position<E>> snapshot){
        snapshot.add(p);
        if(left(p) != null){
            preorderSubtree(left(p), snapshot);
        }
        if(right(p) != null){
            preorderSubtree(right(p), snapshot);
        }
    }

    public Iterable<Position<E>> preorder(){
        LinkedPositionalList<Position<E>> snapshot = new LinkedPositionalList<>();
        if(!isEmpty()){
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    private void inorderSubtree(Position<E> p, LinkedPositionalList<Position<E>> snapshot){
        if(left(p) != null){
            inorderSubtree(left(p), snapshot);
        }
        snapshot.add(p);
        if(right(p) != null){
            inorderSubtree(right(p), snapshot);
        }
    }
    
    public Iterable<Position<E>> inorder(){
        LinkedPositionalList<Position<E>> snapshot = new LinkedPositionalList<>();
        if(!isEmpty()){
            inorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    private void postorderSubtree(Position<E> p, LinkedPositionalList<Position<E>> snapshot){
        if(left(p) != null){
            postorderSubtree(left(p), snapshot);
        }
        if(right(p) != null){
            postorderSubtree(right(p), snapshot);
        }
        snapshot.add(p);
    }

    public Iterable<Position<E>> postorder(){
        LinkedPositionalList<Position<E>> snapshot = new LinkedPositionalList<>();
        if(!isEmpty()){
            postorderSubtree(root(), snapshot);
        }
        return snapshot;
    }



    // positions can get the final result of snapshot.
    public Iterable<Position<E>> positions(){
        // return inorder();
        return inorder();
    } 

    private class ElementIterator implements Iterator<E>{
        Iterator<Position<E>> it = positions().iterator();
        public boolean hasNext(){
            return it.hasNext();
        }
        public E next(){
            return it.next().getElement();
        }
        public void remove(){
            it.remove();
        }

        public void delete(){
            it.remove();
        }
    }

    public Iterator<E> iterator(){
        return new ElementIterator();
    }
    // BFS traverse.
    public Iterable<Position<E>> BFS(){
        LinkedPositionalList<Position<E>> snapshot = new LinkedPositionalList<>();
        if(!isEmpty()){
            Queue<Position<E>> fringe = new ArrayQueue<>();
            fringe.offer(root());
            while(!fringe.isEmpty()){
                Position<E> p = fringe.poll();
                snapshot.add(p);
                for(Position<E> c:children(p)){
                    fringe.offer(c);
                }
            }
        }
        return snapshot;
    }

    // preorder traversal.
    public LinkedPositionalList<E> preOrderTraversal(){
        LinkedPositionalList<E> res = new LinkedPositionalList<>();
        Iterable<Position<E>> it = preorder();
        for(Position<E> p:it){
            res.add(p.getElement());
        }
        return res;
    }

    public LinkedPositionalList<E> inOrderTraversal(){
        LinkedPositionalList<E> res = new LinkedPositionalList<>();
        Iterable<Position<E>> it = inorder();
        for(Position<E> p:it){
            res.add(p.getElement());
        }
        return res;
    }

    public LinkedPositionalList<E> postOrderTraversal(){
        LinkedPositionalList<E> res = new LinkedPositionalList<>();
        Iterable<Position<E>> it = postorder();
        for(Position<E> p:it){
            res.add(p.getElement());
        }
        return res;
    }

    public LinkedPositionalList<E> levelOrderTraversal(){
        LinkedPositionalList<E> res = new LinkedPositionalList<>();
        Iterable<Position<E>> it = BFS();
        for(Position<E> p:it){
            res.add(p.getElement());
        }
        return res;
    }

    public void remove(E element){
        root = removeBST(root, element);
    }

    private Node<E> removeBST(Node<E> node, E element){
        if(node == null) return null;
        int cmp = compare(element, node.getElement());
        if(cmp < 0){
            node.setLeft(removeBST(node.getLeft(), element));
        }else if(cmp > 0){
            node.setRight(removeBST(node.getRight(), element));
        }else{
            size--;
            // no node -> return null, one node: return that only one node.
            if(node.getLeft() == null) return node.getRight();
            else if(node.getRight() == null) return node.getLeft();
            // two nodes->return the right node.
            // find the min node in the right subtree.
            // replace the node's data with the min node.
            // 
            Node<E> successor = min(node.getRight());
            node.setElement(successor.getElement());
            node.setRight(removeBST(node.getRight(), successor.getElement()));
        }
        return node;
    }

    private Node<E> min(Node<E> node){
        while(node.getLeft() != null){
            node = node.getLeft();
        }
        return node;
    }

}



