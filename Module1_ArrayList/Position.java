package DataStructure.Module1_ArrayList;



// To provide a general abstraction for the location of an element within a structure, we define a simple position abstract data type.
// A position acts as a marker or token within a boarder positional list. 
public interface Position <E> {
    E getElement() throws IllegalStateException;
}
