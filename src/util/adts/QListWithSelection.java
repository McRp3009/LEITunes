package util.adts;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 * @param <E>
 */
public interface QListWithSelection<E> extends QList<E> {

    void select(int i);

    /**
	 * Adds an element at the end of the list
	 *
	 * @param e the element to be added
	 */
    @Override
    void add(E e);

    /**
     * Checks to see if theres a selected element
     *
     * @return true if there is a selected element,
     *         false otherwise
     * @ensures \return == true || \return == false
     */
    boolean someSelected();

    /**
     * Returns the index of the selected element
     *
     * @requires someSelected()
     * @return index of the selected element or -1 if
     *         there is no element selected
     * @ensures  0 <= \return < size()
     */
    int getIndexSelected();

    /**
     * Selects the element after getSelected() if possible
     *
     * @ensures \new == null || \new == get(indexof(\old + 1) + 1)
     */
    void next();

    /**
     * Selects the element before getSelected() if possible
     *
     * @ensures \new == null || \new == get(indexof(\old + 1) - 1)
     */
    void previous();

    /**
     * Removes the selected element if someSelected()
     *
     * @ensures getSelected() == null
     */
    void remove();

    /**
     * Returns the selected element
     *
     * @requires someSelected()
     * @return the selected element
     * @ensures \return != null
     */
    E getSelected();

}
