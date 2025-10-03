package DataStructure.Module4_Tree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E>{
    public Position<E> sibling(Position<E> p){
        Position<E> parent = parent(p);
        if(parent == null) return null;
        if(p == left(parent)){
            return right(parent);
        }else if(p == right(parent)){
            return left(parent);
        }
        return null;
    }
    // returns the number of children of Position p;
    public int numChildren(Position<E> p){
        int count = 0;
        if(left(p) != null){
            count++;
        }
        if(right(p) != null){
            count++;
        }
        return count;
    }
    // Returns an iterable collection of the Positions representing p's children.
    public Iterable<Position<E>> children(Position<E> p){
        List<Position<E>> snapshot = new ArrayList<>(2);
        if(left(p) != null){
            snapshot.add(left(p));
        }
        if(right(p) != null){
            snapshot.add(right(p));
        }
        return snapshot;
    }
}
