package util.adts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martim Pereira fc58223
 * @author André Reis fc58192
 *
 * @param <E>
 */

public class ArrayQListWithSelection<E> extends AbsQListWithSelection<E> {

    /**
     * Constructor that creates a AbsQList
     */
    public ArrayQListWithSelection() {
        super();
    }

    //-----testes para stor-----//

    public ArrayQListWithSelection(List<E> list) {
        super();
        for (E e : list) {
            super.add(e);            
        }
        super.next();

    }

    public ArrayQListWithSelection(List<E> list, int selected) {
        super();
        for (E e : list) {
            super.add(e);            
        }
        super.select(selected);
    }

    //-----//-----//



    /**
     * Method that creates a list based in arrays
     * 
     * @return a new ArrayList
     * @ensures \result != null
     */
    @Override
    protected List<E> createList() {
        return new ArrayList<>();
    }


}
