package tictacpack;
//this class represents nodes to make up a linked list
public class LinearNode<E> {

	private LinearNode<E> next; //next linear node in the list
	private E element; //element held in node
	
	//creates an empty node
	public LinearNode()
    {
        next = null;
        element = null;
    }
	
	//creates a non-empty node
	public LinearNode (E elem)
    {
        next = null;
        element = elem;
    }
	
	//returns the next node
	public LinearNode<E> getNext()
    {
        return next;
    }
	//sets the next node
	public void setNext (LinearNode<E> node)
    {
        next = node;
    }
	
	//returns the item held by the node
	public E getElement()
    {
        return element;
    }
	
	
	//sets the element of the current node
	public void setElement (E elem)
    {
        element = elem;
    }
}
