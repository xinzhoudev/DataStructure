package DataStructure.Module4_Tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    // Nested Node class;
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
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

    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
        return new Node<E>(e, parent, left, right);
    }

    // LinkedBinaryTree instance variables.
    protected Node<E> root = null;
    private int size = 0;
    public LinkedBinaryTree(){}
    // non-public utility.
    // validate the position and returns it as a node.
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof Node)){
            throw new IllegalArgumentException("Not valid position type");
        }
        // transfer from Positon<E> to Node<E>
        Node<E> node = (Node<E>) p;
        if(node.getParent() == node){
            throw new IllegalArgumentException();
        }
        return node;
    } 
    // return the number of nodes in the tree.
    public int size(){
        return size;
    }
    // return the root position of the tree.(or null if tree is empty);
    public Position<E> root(){
        return root;
    }
    public Position<E> parent(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getParent();
    }
    // return the Position of p's left child(or null if tree is empty);
    public Position<E> left(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getLeft();
    }
    // return the Position of p's right child(or null if tree is empty);
    public Position<E> right(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getRight();
    }
    public Position<E> addRoot(E e) throws IllegalStateException{
        if(!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }
    // create a new left child of Position p storing element e, returns the Position of the new Node.
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> parent = validate(p);
        if(parent.getLeft() != null){
            throw new IllegalArgumentException("p already has a left child.");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }
    // Create a new right child of Position p storing element e, returns its position.
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> parent = validate(p);
        if(parent.getRight() != null){
            throw new IllegalArgumentException("p is already has a right child.");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }
    // Replace the element at Position p with e and returns the replaced element.
    public E set(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }
    // Attached trees t1 and t2 as left and right subtrees of external p.
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2){
        Node<E> node = validate(p);
        if(isInternal(p)) throw new IllegalArgumentException("p must be a leaf.");
        size += t1.size() + t2.size();
        if(!t1.isEmpty()){
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if(!t2.isEmpty()){
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }
    // removes the node at Position p and replace it with its child. if any.
    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        if(numChildren(p) == 2){
            throw new IllegalArgumentException();
        }
        Node<E> child = (node.getLeft() != null ? node.getLeft():node.getRight());
        if(child != null){
            child.setParent(node.getParent());
        }
        if(node == root){
            root = child;
        }else{
            Node<E> parent = node.getParent();
            if(node == parent.getLeft()){
                parent.setLeft(child);
            }else{
                parent.setRight(child);
            }
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;

    }

    // nested elementIterator class
    private class ElementIterator implements Iterator<E>{
        Iterator<Position<E>> posIterator = positions().iterator();
        public boolean hasNext() {return posIterator.hasNext();}
        public E next() {return posIterator.next().getElement();}
        public void remove() {posIterator.remove();}
    }
    // returns an iterator of the elements stored in the tree.
    public Iterator<E> iterator(){
        return new ElementIterator();
    }
    // adds the positions of the subtree rooted at Position p to the given snapshot.
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot){
        snapshot.add(p);
        for(Position<E> c:children(p)){
            preorderSubtree(c, snapshot);
        }
    }
    // returns an iterable collection of positions of the tree. reported in preorder.
    public Iterable<Position<E>> preorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()){
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }
    // bfs traversal.
    public Iterable<Position<E>> breadthfirst(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()){
            Queue<Position<E>> fringe = new LinkedList<>();
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

    // this inorder traverse method is only for the binary tree.
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot){
        if(left(p) != null){
            inorderSubtree(left(p), snapshot);
        } 
        snapshot.add(p);
        if(right(p) != null){
            inorderSubtree(right(p), snapshot);
        }
    }

    // returns an iterable collection of positions of the tree. reported in inorder.
    public Iterable<Position<E>> inorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()){
            inorderSubtree(root(), snapshot);
        }
        return snapshot;
    }
    // Overrides positions to make inorder the default order for binary trees.
    public Iterable<Position<E>> positions(){
        return inorder();
    }


}
