package util.adts;

import java.util.Iterator;

/**
 * @author malopes
 *
 * @param <E>
 * 
 * A basic list of elements E that allows new elements to be added only
 * at the end of the list.
 * 
 */
public interface QList<E> extends Iterable<E> {

	/**
	 * Returns the number of elements in the list 
	 * 
	 * @return the number of elements in the list
	 */
	int size();
	
	/**
	 * Adds an element at the end of the list
	 * 	
	 * @param e the element to be added
	 */
	void add(E e);
	
	/**
	 * Returns the element at position i
	 * 
	 * @param i the position of the element to return
	 * @requires 0 <= i < size()
	 * @return the element at position i
	 */
	E get(int i);
	
	/**
	 * Returns an iterator over the elements in the list
	 * 
	 * @return  an iterator over the elements in this list in proper sequence.
	 */
	@Override
	Iterator<E> iterator(); 
}
