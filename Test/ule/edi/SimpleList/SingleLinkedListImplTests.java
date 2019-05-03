package ule.edi.SimpleList;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SingleLinkedListImplTests {

	private SingleLinkedListImpl<String> lS;
	private SingleLinkedListImpl<String> lSABC;

	@Before
	public void setUp() {
		this.lS = new SingleLinkedListImpl<String>();

		this.lSABC = new SingleLinkedListImpl<String>("A", "B", "C");
	}

	@Test
	public void constructorElemens() {
		assertEquals("[]", lS.toString());
		lS = new SingleLinkedListImpl<String>("A", "B", "C", "D");
		assertEquals("[A, B, C, D]", lS.toString());
	}
	
	@Test
	public void testForwardIt() {
		lS = new SingleLinkedListImpl<String>("A", "B", "C", "D");
		Iterator<String> i = lS.iterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertEquals("[A, B, C, D]", lS.toString());
}
	
	@Test(expected = NoSuchElementException.class)
	public void testForwardItException() {
		Iterator<String> i = lS.iterator();
		Assert.assertFalse(i.hasNext());
		i.next();
}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testForwardItException2() {
		Iterator<String> i = lS.iterator();
		i.remove();;
}

	@Test
	public void testAddAtPos() {

		lS.addAtPos("A", 1);
		assertEquals("[A]", lS.toString());
		lS.addAtPos("C", 1);
		assertEquals("[C, A]", lS.toString());
		lS.addAtPos("B", 2);
		assertEquals("[C, B, A]", lS.toString());
		lS.addAtPos("H", 2);
		assertEquals("[C, H, B, A]", lS.toString());
		lS.addAtPos("K", 3);
		assertEquals("[C, H, K, B, A]", lS.toString());
		lS.addAtPos("D", 8);
		assertEquals("[C, H, K, B, A, D]", lS.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddAtPosException() {
		lS.addAtPos("A", 0);
	}

	@Test
	public void addNTimes() {

		lS.addNTimes("A", 3);
		assertEquals("[A, A, A]", lS.toString());
		lS.addNTimes("B", 2);
		assertEquals("[A, A, A, B, B]", lS.toString());
		assertEquals("[A, B, C]", lSABC.toString());
		lSABC.addNTimes("H", 4);
		assertEquals("[A, B, C, H, H, H, H]", lSABC.toString());

	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, lS.isEmpty());
		assertEquals(false, lSABC.isEmpty());
	}

	@Test
	public void testAddFirst() {

		lS.addFirst("A");
		lS.addFirst("B");
		lS.addFirst("C");
		lS.addFirst("D");
		assertEquals("[D, C, B, A]", lS.toString());
	}

	@Test
	public void testSize() {
		assertEquals(4, lSABC.size());
	}

	@Test
	public void testRemoveLast() throws EmptyCollectionException {

		assertEquals(lSABC.toString(), "[A, B, C]");
		lSABC.removeLast();
		assertEquals(lSABC.toString(), "[A, B]");
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveLastException() throws EmptyCollectionException {
		lS.removeLast();
	}

	@Test
	public void testRemoveLastElem() throws EmptyCollectionException {

		assertEquals(lSABC.toString(), "[A, B, C]");
		lSABC.removeLast("C");
		assertEquals(lSABC.toString(), "[A, B]");

		lS.addLast("A");
		lS.addLast("A");
		lS.addLast("A");
		lS.addLast("B");
		lS.addLast("C");
		lS.addLast("D");
		lS.addLast("A");
		lS.addLast("A");
		lS.addFirst("K");
		assertEquals(lS.toString(), "[K, A, A, A, B, C, D, A, A]");

		assertEquals(lS.removeLast("K"), "K");
		assertEquals(lS.toString(), "[A, A, A, B, C, D, A, A]");

		assertEquals(lS.removeLast("A"), "A");
		assertEquals(lS.toString(), "[A, A, A, B, C, D, A]");
		assertEquals(lS.removeLast("A"), "A");
		assertEquals(lS.toString(), "[A, A, A, B, C, D]");
		assertEquals(lS.removeLast("A"), "A");
		assertEquals(lS.toString(), "[A, A, B, C, D]");
		assertEquals(lS.removeLast("C"), "C");
		assertEquals(lS.toString(), "[A, A, B, D]");
		assertEquals(lS.removeLast("A"), "A");
		assertEquals(lS.toString(), "[A, B, D]");
		assertEquals(lS.removeLast("A"), "A");
		assertEquals(lS.toString(), "[B, D]");
		assertEquals(lS.removeLast("D"), "D");
		assertEquals(lS.toString(), "[B]");
		assertEquals(lS.removeLast("B"), "B");
		assertEquals(lS.toString(), "[]");

	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveLastElemException() throws EmptyCollectionException {
		lS.removeLast("K");
	}

	@Test
	public void testReverse() {

		assertEquals(lSABC.reverse().toString(), "[C, B, A]");
	}

// TEST DE SUBLIST
	@Test
	public void testSubListEnListaVacia() {

		Assert.assertEquals(-1, lS.isSubList(lSABC));
	}

	@Test
	public void tesSubListConSubListaVacia() {
		Assert.assertEquals(1, lSABC.isSubList(lS));
	}

	@Test
	public void subListVarios() {
		lS = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E");
		Assert.assertEquals(1, lS.isSubList(lSABC));
		lS = new SingleLinkedListImpl<String>("Z", "X", "A", "B", "C", "D", "E");
		Assert.assertEquals(3, lS.isSubList(lSABC));
		lS = new SingleLinkedListImpl<String>("Z", "X", "A", "B", "C");
		Assert.assertEquals(3, lS.isSubList(lSABC));
		lS = new SingleLinkedListImpl<String>("A", "B", "C");
		Assert.assertEquals(1, lS.isSubList(lSABC));
	}
	
	@Test
	public void tesSubListConSublistaNoEncontrada() {
		lS = new SingleLinkedListImpl<String>("a","h");
		Assert.assertEquals(-1, lSABC.isSubList(lS));
	}
	
	@Test
	public void testIndexOf() {
		assertEquals(1, lSABC.indexOf("A"));
		assertEquals(2, lSABC.indexOf("B"));
		assertEquals(3, lSABC.indexOf("C"));
	}
	@Test(expected = NoSuchElementException.class)
	public void testIndexOfException() {
		assertEquals(1, lSABC.indexOf("h"));
		
	}

}
