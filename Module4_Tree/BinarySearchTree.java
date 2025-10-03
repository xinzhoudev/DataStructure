package DataStructure.Module4_Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    // define the insert function
    private Node<E> insertBST(Node<E> node, E element){
        if(node == null){
            size++;
            return new Node<>(element);
        }
        if(compare(node.getElement(), element) < 0){
            node.setLeft(insertBST(node.getLeft(), element));
        }else if(compare(node.getElement(), element) > 0){
            node.setRight(insertBST(node.getRight(), element));
        }
        return node;
    }

    public boolean contains(E element){
        return containsBST(root, element);
    }

    private boolean containsBST(Node<E> node, E element){
        if(node == null) return false;
        if(compare(node.getElement(), element) < 0){
            return containsBST(node.getLeft(), element);
        }else if(compare(node.getElement(), element) > 0){
            return containsBST(node.getRight(), element);
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

    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot){
        snapshot.add(p);
        if(left(p) != null){
            preorderSubtree(left(p), snapshot);
        }
        if(right(p) != null){
            preorderSubtree(right(p), snapshot);
        }
    }

    public Iterable<Position<E>> preorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()){
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot){
        if(left(p) != null){
            inorderSubtree(left(p), snapshot);
        }
        snapshot.add(p);
        if(right(p) != null){
            inorderSubtree(right(p), snapshot);
        }
    }
    
    public Iterable<Position<E>> inorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()){
            inorderSubtree(root(), snapshot);
        }
        return snapshot;
    }



    // positions can get the final result of snapshot.
    public Iterable<Position<E>> positions(){
        // return preorder();
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
    }

    public Iterator<E> iterator(){
        return new ElementIterator();
    }
    // BFS traverse.
    public Iterable<Position<E>> BFS(){
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


}



