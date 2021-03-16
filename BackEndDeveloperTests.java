import org.junit.Test;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;

public class BackEndDeveloperTests {

	@Test
	public void testRedBlackTreeInsertion() {
		// fail("Backend not yet implemented");

		RedBlackTree<Pokemon> tree = new RedBlackTree<Pokemon>();

		// Insert on empty tree
		tree.insert(new Pokemon("root"));
		assertEquals("[root ]", tree.toString());

		// Insert left child
		tree.insert(new Pokemon("left 1"));	
		assertEquals("[root, left 1 ]", tree.toString());

		// Insert right child
		tree.insert(new Pokemon("right 1"));
		assertEquals("[root, left 1, right 1 ]", tree.toString());

	}

	@Test
	public void testRedBlackTreeRotations() {
		// fail("Backend not yet implemented");

		RedBlackTree<Pokemon> tree = new RedBlackTree<Pokemon>();
	
		// Left rotation
		tree.insert(new Pokemon("root"));
		tree.insert(new Pokemon("right 1"));
		tree.insert(new Pokemon("right 2"));
		// Checks that left rotation occured
		assertEquals("[right 1, root, right 2 ]", tree.toString());

		// Right rotation
		tree.insert(new Pokemon("root"));
		tree.insert(new Pokemon("left 1"));
		tree.insert(new Pokemon("left 2"));
		// Checks that right rotation occured
		assertEquals("[left 1, left 2, root ]", tree.toString());
	}
	
	@Test
	public void testRedBlackTreeRemove() {
		// fail("Backend not yet implemented");

		RedBlackTree<Pokemon> tree = new RedBlackTree<Pokemon>();

		// Remove om empty tree
		try {
			tree.remove("root");
			fail("Exception not thrown.");
		} catch (NoSuchElementException e) {
			// Test passes. Nothing happens. 
		}

		// Remove node on tree with size of 1
		tree.insert(new Pokemon("root"));
		tree.remove("root");
		asssertEquals(true, tree.isEmpty());

		// Remove leaf node
		tree.insert(new Pokemon("root"));
		tree.insert(new Pokemon("left 1"));
		tree.remove("left 1");
		assertEquals("[root ]", tree.toString());

		// Remove non-leaf node
		tree.insert(new Pokemon("left 1"));
		tree.remove("root");
		assertEquals("[left 1 ]", tree.toString());


	}

	@Test
	public void testRedBlackTreeGet() {
		// fail("Backend not yet implemented");

		RedBlackTree<Pokemon> tree = new RedBlackTree<Pokemon>();

		// Empty tree
		try {
			tree.get("root");
			fail("Exception not thrown");
		} catch (NoSuchElementException e) {
			// Test passes. Nothing happens
		}

		// Get root node
		tree.insert(new Pokemon("root"));
		assertEquals(tree.get("root"), "root");
		
		// Get leaf node
		tree.insert(new Pokemon("left 1"));
		assertEquals(tree.get("left 1"), "left 1");

	}

	@Test
	public void testRedBlackTreeContains() {
		// fail("Backend not yet implemented");

		RedBlackTree<Pokemon> tree = new RedBlackTree<Pokemon>();

		// Empty tree
		tree.insert(new Pokemon("root"));
		assertEquals(tree.contains("root"), false);

		// Get root node
		tree.insert(new Pokemon("root"));
		assertEquals(tree.contains("root"), true);
		
		// Get leaf node
		tree.insert(new Pokemon("left 1"));
		assertEquals(tree.contains("left 1"), true);

	}


}
