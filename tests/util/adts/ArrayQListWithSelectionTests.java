package util.adts;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Tests for the ArrayQListWithSelection
 *
 */
public class ArrayQListWithSelectionTests {

	private ArrayQListWithSelection<Integer> array;
	private Integer e1;
	private Integer e2;

	/**
	 * Method that sets up the ArrayQListWithSelection object to test before each test
	 */
	@BeforeEach
	void setUp() {
		this.array = new ArrayQListWithSelection<>();

		this.e1 = 1;
		this.e2 = 2;

		array.add(e1);
		array.add(e2);
	}

	/**
	 * Tests if the ArrayQListWithSelection constructor is working properly
	 */
	@Test
	public void constructorTest() {
		assertNotNull(array);
	}

	/**
	 * Tests if the method select is working properly
	 */
	@Test
	public void selectTest() {
		array.select(0);
		assertEquals(array.getSelected(), e1);
		array.select(1);
		assertEquals(array.getSelected(), e2);
	}

	/**
	 * Tests if the method add is working properly
	 */
	@Test
	public void addTest() {
		int oldSize = array.size();
		array.add(3);
		assertTrue(oldSize < array.size());
		assertEquals(array.get(array.size() - 1), 3);
		assertEquals(array.getSelected(), 3);
	}

	/**
	 * Tests if the method someSelected is working properly
	 */
	@Test
	public void someSelectedTest() {
		array.select(0);
		assertTrue(array.someSelected());
		array.remove();
		assertFalse(array.someSelected());

	}

	/**
	 * Tests if the method getIndexSelected is working properly
	 */
	@Test
	public void getIndexSelectedTest() {
		array.select(0);
		assertEquals(0, array.getIndexSelected());
	}

	/**
	 * Tests if the method next is working properly
	 */
	@Test
	public void nextTest() {
		array.select(0);
		array.next();
		assertEquals(e2, array.getSelected());
		array.next();
		assertFalse(array.someSelected());

	}

	/**
	 * Tests if the method previous is working properly
	 */
	@Test
	public void previousTest() {
		array.select(1);
		array.previous();
		assertEquals(e1, array.getSelected());
		array.previous();
		assertFalse(array.someSelected());
	}

	/**
	 * Tests if the method remove is working properly
	 */
	@Test
	public void removeTest() {
		int oldSize = array.size();
		array.select(0);
		array.remove();
		assertTrue(oldSize > array.size());
		assertNull(array.getSelected());

	}

	/**
	 * Tests if the method getSelected is working properly
	 */
	@Test
	public void getSelectedTest() {
		array.select(0);
		assertEquals(e1, array.getSelected());
		array.select(1);
		assertEquals(e2, array.getSelected());
	}

	/**
	 * Tests if the method iterator is working properly
	 */
	@Test
	public void iteratorTest() {

		Iterator<Integer> it = array.iterator();

		if (it.hasNext()) {
			assertEquals(it.next(), e1);
		}

		if (it.hasNext()) {
			assertEquals(it.next(), e2);
		}

	}

	/**
	 * Test for the method size()
	 */
	@Test
	public void sizeTest() {
		int oldSize = array.size();
		assertEquals(oldSize, 2);
	}
	
	/**
	 * Test for the method get()
	 */
	@Test
	public void getTest() {
		assertEquals(array.get(0), e1);
		assertEquals(array.get(1), e2);
	}

	/**
	 * Test for the method toString()
	 */
	@Test
	public void toStringTest() {
		array.select(0);
		String s = "\n0 1 ->\n1 2";
		assertEquals(s, array.toString());

		array.select(1);
		String s2 = "\n0 1\n1 2 ->";
		assertEquals(s2, array.toString());
	}

	/**
	 * Test for the method getList()
	 */
	@Test
	public void getListTest() {
		assertNotNull(array.getList());
	}

	/**
	 * Test for the method equals
	 */
	@Test
	public void equalsTest() {
		List<Integer> a = List.of(1,2,3);
		ArrayQListWithSelection<Integer> array2 = new ArrayQListWithSelection<>();
		array2.add(1);
		array2.add(2);
		assertTrue(array.equals(array));
		assertFalse(array.equals(null));
		assertFalse(array.equals(a));
	    assertTrue(array.equals(array2));
		assertTrue(array2.equals(array));
		array2.remove();
		assertFalse(array.equals(array2));
		assertFalse(array2.equals(array));

	}

}
