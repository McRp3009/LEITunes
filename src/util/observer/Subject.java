package util.observer;

/**
 * @author antonialopes
 *
 * @param <E>
 * 
 * Represents objects that are observed by listeners of events of type E
 * 
 */

public interface Subject<E extends Event> {

	/**
	 * Emits a given event to the listeners
	 * 
	 * @param e event that occurred
	 */
	void emitEvent(E e);

	/**
	 * Registers the given listener
	 * 
	 * @param obs listener to be added 
	 */
	void registerListener(Listener<E> obs);

	/**
	 * Removes the registry of the given listener
	 * 
	 * @param obs listener to be removed
	 */
	void unregisterListener(Listener<E> obs);

}