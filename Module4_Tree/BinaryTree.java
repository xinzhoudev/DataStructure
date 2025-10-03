package DataStructure.Module4_Tree;

public interface BinaryTree<E> extends Tree<E>{
    // return position of the left child.
    Position<E> left(Position<E> p) throws IllegalArgumentException;
    // return right child
    Position<E> right(Position<E> p) throws IllegalArgumentException;
    // return all the siblings, null if not exist.
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
    
}
