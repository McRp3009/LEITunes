package util.adts;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class EvalArrayQListWithSelectionTest {

	private ArrayQListWithSelection<Integer> empty;
	private ArrayQListWithSelection<Integer> oneElementNoneSelected;
	private ArrayQListWithSelection<Integer> fiveElementsNoneSelected;
	private ArrayQListWithSelection<Integer> oneElementFirstSelected;
	private ArrayQListWithSelection<Integer> fiveElementsFirstSelected;
	private ArrayQListWithSelection<Integer> fiveElementsOtherSelected;
	private ArrayQListWithSelection<Integer> fiveElementsLastSelected;

	@BeforeEach @Before
	 public void setUp() {
		empty = new ArrayQListWithSelection<>();	
		
		oneElementNoneSelected = new ArrayQListWithSelection<>(Arrays.asList(1));
		fiveElementsNoneSelected = new ArrayQListWithSelection<>(Arrays.asList(1, 2, 3, 4, 5));
		
		oneElementFirstSelected = new ArrayQListWithSelection<>(Arrays.asList(1),0);
		fiveElementsFirstSelected = new ArrayQListWithSelection<>(Arrays.asList(1, 2, 3, 4, 5),0);
		fiveElementsLastSelected = new ArrayQListWithSelection<>(Arrays.asList(1, 2, 3, 4, 5),4);
		fiveElementsOtherSelected = new ArrayQListWithSelection<>(Arrays.asList(1, 2, 3, 4, 5),2);

	}

	@Test
	public void testSize() {
		assertEquals(0, empty.size());
		
		assertEquals(1, oneElementNoneSelected.size());
		assertEquals(5, fiveElementsNoneSelected.size());
		
		assertEquals(1, oneElementFirstSelected.size());
		assertEquals(5, fiveElementsFirstSelected.size());
		assertEquals(5, fiveElementsLastSelected.size());
		assertEquals(5, fiveElementsOtherSelected.size());
	}
	

	@Test
	public void testSomeSelected() {
		assertFalse(empty.someSelected());	
		
		assertFalse(oneElementNoneSelected.someSelected());
		assertFalse(fiveElementsNoneSelected.someSelected());
		
		assertTrue(oneElementFirstSelected.someSelected());
		assertTrue(fiveElementsFirstSelected.someSelected());
		assertTrue(fiveElementsLastSelected.someSelected());
		assertTrue(fiveElementsOtherSelected.someSelected());
	}

	@Test
	public void testGetIndex() {
		assertEquals(0,oneElementFirstSelected.getIndexSelected());
		assertEquals(0,fiveElementsFirstSelected.getIndexSelected());
		assertEquals(4,fiveElementsLastSelected.getIndexSelected());
		assertEquals(2,fiveElementsOtherSelected.getIndexSelected());
	}

	@Test
	public void testGetSelected() {
		assertEquals((Integer) 1, oneElementFirstSelected.getSelected());
		assertEquals((Integer)1, fiveElementsFirstSelected.getSelected());
		assertEquals((Integer)1,fiveElementsFirstSelected.getSelected());
		assertEquals((Integer)5,fiveElementsLastSelected.getSelected());
		assertEquals((Integer)3,fiveElementsOtherSelected.getSelected());
	}

	@Test
	public void testIterator() {
		assertIterableEquals(Arrays.asList(), empty);
		
		assertIterableEquals(Arrays.asList(1), oneElementNoneSelected);
		assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5), fiveElementsNoneSelected);

		assertIterableEquals(Arrays.asList(1), oneElementFirstSelected);	
		assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5), fiveElementsFirstSelected);
		assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5), fiveElementsLastSelected);
		assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5), fiveElementsOtherSelected);
	}

	@Test
	public void testGet() {
		assertEquals((Integer)1,oneElementNoneSelected.get(0));
		
		assertEquals((Integer)1,fiveElementsNoneSelected.get(0));
		assertEquals((Integer)3,fiveElementsNoneSelected.get(2));
		assertEquals((Integer)5,fiveElementsNoneSelected.get(4));
		
		assertEquals((Integer)1,fiveElementsLastSelected.get(0));
		assertEquals((Integer)3,fiveElementsLastSelected.get(2));
		assertEquals((Integer)5,fiveElementsLastSelected.get(4));

		assertEquals((Integer)1,fiveElementsOtherSelected.get(0));
		assertEquals((Integer)3,fiveElementsOtherSelected.get(2));
		assertEquals((Integer)5,fiveElementsOtherSelected.get(4));
	}

	@Test
	public void testRemove() {
		oneElementFirstSelected.remove();
		assertIterableEquals(oneElementFirstSelected, Arrays.asList());
		assertFalse(oneElementFirstSelected.someSelected());

		fiveElementsLastSelected.remove();
		assertIterableEquals(fiveElementsLastSelected, Arrays.asList(1, 2, 3, 4));
		assertFalse(fiveElementsLastSelected.someSelected());	

		fiveElementsFirstSelected.remove();
		assertIterableEquals(fiveElementsFirstSelected, Arrays.asList(2, 3, 4, 5));
		assertFalse(fiveElementsFirstSelected.someSelected());	

		fiveElementsOtherSelected.remove();
		assertIterableEquals(fiveElementsOtherSelected, Arrays.asList(1, 2, 4, 5));
		assertFalse(fiveElementsOtherSelected.someSelected());	
		
		//alternativa seria usar equals, que devia estar implementado...
	}


	@Test
	public void testAddLastSelected() {
		empty.add(10);
		assertIterableEquals(Arrays.asList(10), empty);
		assertTrue(empty.someSelected());
		assertEquals(0,empty.getIndexSelected());
		assertEquals((Integer)10,empty.getSelected());
		
		oneElementNoneSelected.add(10);
		assertIterableEquals(Arrays.asList(1, 10), oneElementNoneSelected);
		assertTrue(oneElementNoneSelected.someSelected());
		assertEquals(1,oneElementNoneSelected.getIndexSelected());
		assertEquals((Integer) 10,oneElementNoneSelected.getSelected());

		fiveElementsNoneSelected.add(10);
		assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5, 10), fiveElementsNoneSelected);
		assertTrue(fiveElementsNoneSelected.someSelected());
		assertEquals(5,fiveElementsNoneSelected.getIndexSelected());
		assertEquals((Integer) 10,fiveElementsNoneSelected.getSelected());
	
		fiveElementsOtherSelected.add(10);
		assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5, 10), fiveElementsOtherSelected);
		assertTrue(fiveElementsOtherSelected.someSelected());
		assertEquals(5,fiveElementsOtherSelected.getIndexSelected());
		assertEquals((Integer) 10,fiveElementsOtherSelected.getSelected());
		
		//alternativa seria usar equals, que devia estar implementado...
	}
	
}
	
	