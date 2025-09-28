package DataStructure.Module1_ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E>, Iterable<E> {
    // nested node class about the position.
    // Private means it cannot be created by other class directly.
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        // Assign the position of the element, its prev element, and its next Element.
        // This node has its own E, but also stores the prev Node and the Next Node;
        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }
        public E getElement() throws IllegalStateException{
            if(next == null) throw new IllegalStateException("Position no longer valid");
            return element;
        }
        public Node<E> getPrev(){
            return prev;
        }
        public Node<E> getNext(){
            return next;
        }
        public void setElement(E e){
            element = e;
        }
        public void setPrev(Node<E> p){
            prev = p;
        }
        public void setNext(Node<E> n){
            next = n;
        }
    }
    private class PositionIterator implements Iterator<Position<E>> {
        // first() returns the header of the linkedPositionalList
        private Position<E> cursor = first();
        private Position<E> recent = null;
        public boolean hasNext(){
            return (cursor != null);
        }
        // next will move cursor to the next position, and return the previous position.
        public Position<E> next() throws NoSuchElementException{
            if(cursor == null) throw new NoSuchElementException("Nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }
        // use the remove method of linkedPositionalList directly.
        public void remove() throws IllegalStateException{
            if(recent == null) throw new IllegalStateException("nothing to remove");
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    }
    // End of the nested PositionalIterator class;
    // Nested PositionIterable class, to iterate LinkedPositionalList
    private class PositionalIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() {return new PositionIterator();}
    }
    // End of nested PositionIterable class;
    public Iterable<Position<E>> positions(){
        return new PositionalIterable();
    }

    // nested elementIterator class.
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();
        public boolean hasNext() {return posIterator.hasNext();}
        public E next(){return posIterator.next().getElement();}
        public void remove() {posIterator.remove();}
    }

    // Returns an iterator of the elements stored in the list.
    public Iterator<E> iterator() {return new ElementIterator();}

    // instance variables of the LinkedPositionalList.
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;
    public LinkedPositionalList(){
        // create header.
        header = new Node<>(null, null, null);
        // trailer is preceded by header.
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    private Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid P"); 
        Node<E> node = (Node<E>)p;
        if(node.getNext() == null){
            throw new IllegalArgumentException("p is no longer in the list");
        }
        return node;
    }

    private Position<E> position(Node<E> node){
        if(node == header || node == trailer){
            return null;
        }
        return node;
    }
    // public methods.
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public Position<E> first(){
        return position(header.getNext());
    }
    public Position<E> last(){
        return position(trailer.getPrev());
    }
    public Position<E> before(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return position(node.getPrev());
    }
    public Position<E> after(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return position(node.getNext());
    } 
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ){
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest); 
        succ.setPrev(newest);
        size++;
        return newest;
    }
    public Position<E> addFirst(E e){
        return addBetween(e, header, header.getNext());  
    }
    public Position<E> addLast(E e){
        return addBetween(e, trailer.getPrev(), trailer);
    }
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }
    public E set(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        Node<E> pred = node.getPrev();
        Node<E> succ = node.getNext();
        pred.setNext(succ);
        succ.setPrev(pred);
        size--;
        E answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }


}
