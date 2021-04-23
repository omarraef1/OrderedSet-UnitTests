
// Omar R. Gebril 	SID 23323978 	CSC 210

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Test;

public class OdereredSetTest {

	// You must add many, many, many more @Tests and assertions!
	// This @Test is here to ensure insert, subset, and toStringInorder work
	@Test
	public void testSubSet() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		assertEquals(0, bst.size());
		assertEquals(null, bst.max());
		assertTrue(bst.insert(50));
		assertTrue(bst.contains(50));
		assertFalse(bst.contains(25));
		assertFalse(bst.insert(50));
		bst.insert(25);
		assertEquals(2, bst.size());
		assertFalse(bst.insert(25));
		bst.insert(12);
		bst.insert(75);
		assertTrue(bst.contains(50));
		bst.insert(65);
		bst.insert(90);
		assertTrue(bst.contains(75));
		assertFalse(bst.contains(26));
		assertEquals("12 25 50 65 75 90", bst.toStringInorder());
		assertEquals(Integer.valueOf(90), bst.max());
		assertEquals(6, bst.size());
		assertEquals("12 25", bst.subset(1, 49).toStringInorder());
		assertEquals("25 50 65", bst.subset(25, 75).toStringInorder());
		assertEquals("", bst.subset(12, 12).toStringInorder()); // 12 < 12 is false
	}

	@Test
	public void testNodesAtLevel() {
		OrderedSet<Integer> tytbst = new OrderedSet<Integer>();
		tytbst.insert(4);
		tytbst.insert(3);
		tytbst.insert(9);
		tytbst.insert(1);
		tytbst.insert(5);
		tytbst.insert(11);
		tytbst.insert(20);
		// System.out.print(tytbst.nodesAtLevel(3));
		assertEquals(1, tytbst.nodesAtLevel(0));
		assertEquals(2, tytbst.nodesAtLevel(1));
		assertEquals(3, tytbst.nodesAtLevel(2));
		assertEquals(1, tytbst.nodesAtLevel(3));
	}

	@Test
	public void testSameStructure() {
		OrderedSet<Integer> tree1 = new OrderedSet<Integer>();
		tree1.insert(50);
		tree1.insert(60);
		tree1.insert(70);
		tree1.insert(80);
		tree1.insert(90);

		OrderedSet<Integer> tree2 = new OrderedSet<Integer>();
		tree2.insert(55);
		tree2.insert(65);
		tree2.insert(75);
		tree2.insert(85);
		tree2.insert(95);

		OrderedSet<Integer> tree3 = new OrderedSet<Integer>();
		tree3.insert(55);
		tree3.insert(65);
		tree3.insert(75);
		tree3.insert(85);
		tree3.insert(89);
		tree3.insert(95);

		OrderedSet<Integer> tree4 = new OrderedSet<Integer>();
		tree4.insert(55);
		tree4.insert(45);
		tree4.insert(65);
		tree4.insert(75);
		tree4.insert(85);
		tree4.insert(95);

		assertTrue(tree1.sameStructure(tree2));
		assertFalse(tree2.sameStructure(tree3));
		assertFalse(tree3.sameStructure(tree1));
		assertFalse(tree3.sameStructure(tree2));
		assertFalse(tree4.sameStructure(tree3));
	}

	@Test
	public void testRemove() {
		OrderedSet<Integer> tree6 = new OrderedSet<Integer>();
		tree6.insert(50);
		tree6.insert(40);
		tree6.insert(60);
		tree6.insert(70);
		tree6.insert(80);
		tree6.insert(30);
		tree6.insert(90);
		System.out.println(tree6.toStringInorder());
		assertTrue(tree6.remove(50));
		System.out.println(tree6.toStringInorder());
		assertTrue(tree6.remove(90));
		System.out.println(tree6.toStringInorder());
		assertTrue(tree6.remove(30));
		System.out.println(tree6.toStringInorder());
		assertTrue(tree6.remove(80));
		assertFalse(tree6.remove(80));
		System.out.println(tree6.toStringInorder());
		assertTrue(tree6.remove(60));
		System.out.println(tree6.toStringInorder());
		// assertTrue(tree6.remove(70));
		// System.out.println(tree6.toStringInorder());
		// assertTrue(tree6.remove(50));
		// System.out.println(tree6.toStringInorder());
		// assertTrue(tree6.remove(40));
		// System.out.println(tree6.toStringInorder());

	}

	@Test
	public void testIntersectionAndUnion() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(25);
		bst.insert(12);
		bst.insert(75);
		bst.insert(65);
		bst.insert(90);
		bst.insert(109);
		assertEquals("12 25 50 65 75 90 109", bst.toStringInorder());

		OrderedSet<Integer> nbst = new OrderedSet<Integer>();
		nbst.insert(50);
		nbst.insert(25);
		nbst.insert(90);
		nbst.insert(60);
		assertEquals("25 50 60 90", nbst.toStringInorder());

		OrderedSet<Integer> ubst = bst.union(nbst);
		assertEquals("12 25 50 60 65 75 90 109", ubst.toStringInorder());

		OrderedSet<Integer> kbst = nbst.intersection(bst);
		assertEquals("25 50 90", kbst.toStringInorder());

	}
}