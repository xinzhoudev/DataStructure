package DataStructure.Module4_Tree;

import java.util.Iterator;

public interface Tree<E> extends Iterable<E> {
    // return the root node the tree.
    Position<E> root();
    Position<E> parent(Position<E> p) throws IllegalArgumentException;
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;
    int numChildren(Position<E> p) throws IllegalArgumentException;
    boolean isInternal(Position<E> p) throws IllegalArgumentException;
    boolean isExternal(Position<E> p) throws IllegalArgumentException;
    boolean isRoot(Position<E> p) throws IllegalArgumentException;
    int size();
    boolean isEmpty();
    Iterator<E> iterator();
    // Traverse the tree to get all of the nodes.
    Iterable<Position<E>> positions();
}
