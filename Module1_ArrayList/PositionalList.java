package DataStructure.Module1_ArrayList;

public interface PositionalList<E> {
    int size();
    Position<E> first();
    Position<E> last();
    // Return the position immediately before Position P.
    Position<E> before(Position<E> p) throws IllegalArgumentException;
    // Return the position immediately after Position P.
    Position<E> after(Position<E> p) throws IllegalArgumentException;
    Position<E> addFirst(E e);
    Position<E> addLast(E e);
    // add element before position p
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
    // add element after position p
    Position<E> addAfter(Position<E> p, E e);
    // set element at P as e;
    E set(Position<E> p, E e) throws IllegalArgumentException;
    E remove(Position<E> p) throws IllegalArgumentException;
}
