package util.adts;

import java.util.Iterator;
import java.util.List;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 */
public abstract class AbsQListWithSelection<E> implements QListWithSelection<E> {

    private List<E> list;
    private E selected;

    /**
     * Constructor that creates a list that can have 0 or 1 selected elements in it
     */
    protected AbsQListWithSelection() {
        this.list = createList();
        this.selected = null;
    }

    /**
     * Method that creates a list
     */
    protected abstract List<E> createList();

	/**
     * Method that returns the list of elements
	 * @return list
     * @ensures \result != null
	 */
	public List<E> getList(){
		return this.list;
	}

    /**
     * Method that selects the element at a given index
     * @param i index of new selected element
     * @requires 0 <= i < size()
     * @ensures this.selected = get(i)
     */
    @Override
    public void select(int i) {
        this.selected = list.get(i);
    }

    /**
	 * Adds an element at the end of the list
     * and selects it
	 *
	 * @param e the element to be added
     * @ensures e == this.selected
	 */
    @Override
    public void add(E e) {
        this.list.add(e);
        this.selected = e;
    }

    /**
     * Checks to see if theres a selected element
     *
     * @return true if there is a selected element,
     *         false otherwise
     * @ensures \return == true || \return == false
     */

    @Override
    public boolean someSelected() {
        return (this.selected != null);
    }

    /**
     * Returns the index of the selected element
     *
     * @requires someSelected()
     * @return index of the selected element
     * @ensures  0 <= \return < size()
     */
    @Override
    public int getIndexSelected() {
        return this.list.indexOf(this.selected);
    }

    /**
     * Selects the element after getSelected() if possible
     *
     * @ensures \new == null || \new == get(indexof(\old + 1) + 1)
     */
    @Override
    public void next() {
        if(someSelected()) {
            int i = getIndexSelected();
            if(i < this.list.size()-1) {
                this.selected = list.get(i+1);
            } else {
                this.selected = null;
            }

        }

    }

    /**
     * Selects the element before getSelected() if possible
     *
     * @ensures \new == null || \new == get(indexof(\old + 1) - 1)
     */
    @Override
    public void previous() {
        if(someSelected()) {
            int i = getIndexSelected();
		    if(i > 0){
                this.selected = list.get(i-1);
		    } else {
			    this.selected = null;
		    }
        }

    }

    /**
     * Removes the selected element if someSelected()
     *
     * @ensures getSelected() == null
     */
    @Override
    public void remove() {
        if(someSelected()) {
            this.list.remove(this.selected);
            this.selected = null;
        }

    }

    /**
     * Returns the selected element
     *
     * @requires someSelected()
     * @return the selected element
     * @ensures \return != null
     */
    @Override
    public E getSelected() {
        return this.selected;
    }

    /**
	 * Returns an iterator over the elements in the list
	 *
	 * @return  an iterator over the elements in this list in proper sequence.
	 */
    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    /**
	 * Returns the number of elements in the list
	 *
	 * @return the number of elements in the list
	 */
    @Override
    public int size() {
        return this.list.size();
    }

    /**
	 * Returns the element at position i
	 *
	 * @param i the position of the element to return
	 * @requires 0 <= i < size()
	 * @return the element at position i
	 */
    @Override
    public E get(int i) {
        return this.list.get(i);
    }

	/**
	 * Returns a String of the Array
	 * @return a String of the Array
	 */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < size(); i++) {
			sb.append("\n" + i + " " + get(i).toString() + (getIndexSelected() == i ? " ->" : ""));
		}

        return sb.toString();

    }

    /**
     * Method that checks if a given object is equal to this one
     * Two AbsQListWithSelection are equal if they have the same
     * List and the same song selected
     *
     * @param other object to check
     * @return if other is equal to this
     * @ensures \result == true || \result == false
     */
    @Override
    public boolean equals(Object other) {
        if(this == other) {
			return true;
		}
		if(other == null) {
			return false;
		}
		if(other.getClass() != this.getClass()) {
			return false;
		}

        AbsQListWithSelection<?> list = (AbsQListWithSelection<?>) other;

        return this.getSelected() == list.getSelected() &&
               this.getList().equals(list.getList());

    }

}
