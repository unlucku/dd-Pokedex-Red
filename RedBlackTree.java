// --== CS400 File Header Information ==--
// Name: Brendan Boyle
// Email: bwboyle@wisc.edu
// Team: DD
// TA: Dan
// Lecturer: Gary

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild; 
        public Node<T> rightChild; 
        public boolean isBlack; 
        public Node(T data) { 
            this.data = data; 
            this.isBlack = false; // all nodes are red by default
        }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }

        /**
         * This method performs a level order traversal of the tree rooted
         * at the current node. The string representations of each data value
         * within this tree are assembled into a comma separated string within
         * brackets (similar to many implementations of java.util.Collection).
         * Note that the Node's implementation of toString generates a level
         * order traversal. The toString of the RedBlackTree class below
         * produces an inorder traversal of the nodes / values of the tree.
         * This method will be helpful as a helper for the debugging and testing
         * of your rotation implementation.
         * @return string containing the values of this tree in level order
         */
        @Override
        public String toString() {
            String output = "[";
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if (next.isBlack) output += " (Black)";
                else output += " (Red)";
                if(!q.isEmpty()) output += ", ";
            }
            return output + "]";
        }
    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    @Override
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        
        if(root == null) { 
            // add first node to an empty tree
            root = newNode; 
            root.isBlack = true; // ensures root node is black
            size++; 
            return true; 
        } 
        else{
            boolean returnValue = insertHelper(newNode,root); // recursively insert into subtree
            if (returnValue) size++;
	    else throw new IllegalArgumentException(
	    	"This RedBlackTree already contains that value.");
            root.isBlack = true; // ensures root node is black
            return returnValue;
        }
    }

    /** 
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) return false;

        // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);// enforces RBT properties

                return true;
            // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else { 
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode); // enforces RBT properties
                return true;
            // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }
    
    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {

        if (parent.leftChild != null && parent.leftChild.equals(child)) rightRotation(child, parent);

        else if (parent.rightChild.equals(child)) leftRotation(child, parent);
        
        else throw new IllegalArgumentException("Nodes are unrelated");
    }

    /**
     * Performs a left rotation on the given nodes
     * 
     * @param child the child node in the rotation
     * @param parent the parent node in the rotation
     */
    private void leftRotation(Node<T> child, Node<T> parent) {
	    // Parent's right subtree becomes childs left subtree
	    parent.rightChild = child.leftChild;
	
	    // If child node has a left subtree
	    if (child.leftChild != null) {
		    // Child node's left subtree's parent becomes parent node
		    child.leftChild.parent = parent;
	    }

	    // Child node's parent set to grandparent
	    child.parent = parent.parent;

	    // If parent node is the tree's root
	    if (parent.parent == null) { 
		    // Root becomes child node
		    root = child;
	    }
	    // If parent node is a left child
	    else if (parent.isLeftChild()) {
		    // Grandparent's left child becomes child node
		    parent.parent.leftChild = child;
	    }
	    // If the parent is a right child
	    else { 
		    // Grandparent's right child becomes child node
		    parent.parent.rightChild = child;
	    }
	    // Child nodes left child becomes parent
	    child.leftChild = parent;
	    // grandparent becomes child
	    parent.parent = child;
    }

    /**
     * Performs a right rotation on the given nodes
     * 
     * @param child the child node in the rotation
     * @param parent the parent node in the rotation
     */
   private void rightRotation(Node<T> child, Node<T> parent) {
	    // Parent's right subtree becomes childs left subtree
	    parent.leftChild = child.rightChild;
	
	    // If child node has a left subtree
	    if (child.rightChild != null) {
		    // Child node's left subtree's parent becomes parent node
		    child.rightChild.parent = parent;
	    }

	    // Child node's parent set to grandparent
	    child.parent = parent.parent;

	    // If parent node is the tree's root
	    if (parent.parent == null) { 
		    // Root becomes child node
		    this.root = child;
	    }
	    // If parent node is a left child
	    else if (!parent.isLeftChild()) {
		    // Grandparent's left child becomes child node
		    parent.parent.rightChild = child;
	    }
	    // If the parent is a right child
	    else { 
		    // Grandparent's right child becomes child node
		    parent.parent.leftChild = child;
	    }

	    // Child nodes left child becomes parent
	    child.rightChild = parent;
	    // grandparent becomes child
	    parent.parent = child;

    }

    /**
     * Ensures that the Red Black Tree Properties stay intact after an item is added to the tree.
     * 
     * @param newNode the node that was most recently added to the tree
     */
    private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {

        // Only runs when:
        // 1. newNode is not the root
        // 2. newNode is red
        // 3. newNode parent is black
        while (newNode != root && !newNode.isBlack && !newNode.parent.isBlack) {

            // Parent node
            Node<T> parent = newNode.parent; 
            // Grandparent node
            Node<T> grandparent = parent.parent; 

            // Uncle node based on parent's relationship to grandparent
            Node<T> uncle = parent.isLeftChild() ? grandparent.rightChild : grandparent.leftChild;

            // Uncle is red
            if (uncle != null && !uncle.isBlack) {
                // Grandparent becomes red
                grandparent.isBlack = false; 
                // Parent and uncle both become black
                parent.isBlack = true; 
                uncle.isBlack = true;
                // Checks for problems further up the tree
                newNode = grandparent;
            } 
            
            // Uncle is black or uncle is null
            else {

                // If newNode and parent have a left right relationship
                if (newNode.isLeftChild() && !parent.isLeftChild()) {
                    // Rotate newNode and parent
                    rotate(newNode, parent);

                    // Prepares tree for next rotation
                    newNode = parent;
                    parent = newNode.parent;
                    grandparent = newNode.parent.parent;
                }

                // If newNode and parent have a right left relationship
                if (!newNode.isLeftChild() && parent.isLeftChild()) {
                    // Rotate newNode and parent
                    rotate(newNode, parent);

                    // Prepare tree for next rotation
                    newNode = parent;
                    parent = newNode.parent;
                    grandparent = newNode.parent.parent;
                }

                // Rotate parent and grandparent nodes
                rotate(parent, grandparent);

                // Swap colors of parent and grandparent nodes
                boolean temp = parent.isBlack;
                parent.isBlack = grandparent.isBlack;
                grandparent.isBlack = temp;


            }

        }
    
    }

    /**
     * Removes a node from the tree, if said node matches the given data
     * 
     * @param data the data of the node to be removed
     * @throws NoSuchElementException if the data is not in the tree
     * @return the data of the removed node
     */
    public T remove(T data) throws NoSuchElementException {
        
        // Searches for node to remove, starting at the root
        Node<T> currentNode = root;
        while (currentNode != null) {
            // Compares current node's data with data to be removed
            int compare = currentNode.data.compareTo(data);

            // If the data is equal --> current node will be removed, exit loop
            if (compare == 0) break;

            // Current node's data is less than data specified --> search right subtree
            if (compare == -1) 
                currentNode = currentNode.rightChild;

            // Current node's data is greater than data specified --> search left subtree
            else 
                currentNode = currentNode.leftChild;
        }

        // Return if node to remove is not found
        if (currentNode == null) throw new NoSuchElementException(data + " was not found");

        // Helper method call if node to remove is found
        removeHelper(currentNode);

        return data;
    }

       /**
     * Helper method for the remove() method above. This method determines if the given node is a 
     * leaf node, has one child, or two children. From there, it will take the steps necessary to 
     * remove that node
     * 
     * @param nodeToBeDeleted the node to be removed from the tree
     */
    private void removeHelper(Node<T> z) {

        // Node with two children
        if (z.leftChild != null && z.rightChild != null) {

            Node<T> pNode = z.leftChild;

            while (pNode.rightChild != null) pNode = pNode.rightChild;

            z.data = pNode.data;

            removeHelper(pNode);

        }

        // Leaf node
        if (z.leftChild == null && z.rightChild == null) {

            if (z == root) {
                root = null;
                return;
            }

            enforceRBTreePropertiesAfterRemove(z);

            if (z.isLeftChild())
                z.parent.leftChild = null;
            else
                z.parent.rightChild = null;

            

        }

        // One child
        else {

            // If the single child is a left child
            if (z.leftChild != null) {

                // If the parent of the removed node is the root
                if (z.parent == null) {
                    // Single child becomes the root
                    root = z.leftChild;
                    z.leftChild.isBlack = true; // root is always black
                    return;
                }

                // Parent of node to remove becomes parent of the single child node
                z.leftChild.parent = z.parent;

                if (z.isLeftChild())
                    z.parent.leftChild = z.leftChild;
                else 
                    z.parent.rightChild = z.leftChild;

                //z.leftChild.isBlack = z.isBlack;

                enforceRBTreePropertiesAfterRemove(z.leftChild);

            }

            // If the single child is a right child
            else if (z.rightChild != null) {

                if (z.parent == null) {
                    root = z.rightChild;
                    z.rightChild.isBlack = true;
                    return;
                }

                z.rightChild.parent = z.parent;

                if (z.isLeftChild())
                    z.parent.leftChild = z.rightChild;
                else 
                    z.parent.rightChild = z.rightChild;


                //z.rightChild.isBlack = z.isBlack;

                enforceRBTreePropertiesAfterRemove(z.rightChild);

            }
        }


    }

    /**
     * Ensures Red Black Tree Properties remain intact after a node is removed from the tree
     * 
     * @param node
     */
    private void enforceRBTreePropertiesAfterRemove(Node<T> node) {

        Node<T> current = node;
        Node<T> sibling;

        while (current != root && current.isBlack) {

            if (current.isLeftChild()) {
                // Since node to be deleted is a left child, its sibling is a right child
                sibling = current.parent.rightChild;

                // Case 1: Red sibling
                if (sibling != null && !sibling.isBlack) {
                    // Sibling color changed to black
                    sibling.isBlack = true;
                    // Parent color changed to red
                    current.parent.isBlack = false;
                    // Rotate sibling and parent
                    rotate(sibling, current.parent);
                    sibling = current.parent.rightChild;
                }

                // Case 2: Sibling has two black children (NOTE: null children are counted as black)
                if (sibling.leftChild == null && sibling.rightChild == null) {
                    // Sibling becomes red
                    sibling.isBlack = false;
                    // current node becomes parent
                    current = current.parent;
                } 
                
                else {
                    // Case 3: Sibling node's right child is black
                    if (sibling.rightChild == null || sibling.rightChild.isBlack) {
                        // Sibling's left child becomes black
                        sibling.leftChild.isBlack = true;
                        // Sibling becomes red
                        sibling.isBlack = false;
                        // Rotate sibling and its left child
                        rotate(sibling.leftChild, sibling);
                        // Sibling is now parent's right child
                        sibling = current.parent.rightChild;
                    }

                    // Case 4: Do this everytime
                    sibling.isBlack = sibling.parent.isBlack; // Sibling's color becomes its parents
                    // Parent becomes black
                    sibling.parent.isBlack = true;

                    // Sibling's right child becomes black
                    sibling.rightChild.isBlack = true;
                    // Rotate sibling and parent
                    rotate(sibling, current.parent);

                    // Current node becomes root
                    current = root;

                }

            }

            // Node to be removed is a right child
            else {
                sibling = current.parent.leftChild;

                // Case 1: Red sibling
                if (sibling != null && !sibling.isBlack) {
                    sibling.isBlack = true;
                    current.parent.isBlack = false;
                    rotate(sibling, current.parent);
                    sibling = current.parent.leftChild;
                }

                // Case 2: Sibling has two black children (NOTE: null children are counted as black)
                if (sibling.rightChild == null && sibling.rightChild == null) {
                    sibling.isBlack = false;
                    current = current.parent;
                } 
                
                else {
                    /// Case 3: Sibling node's left child is black 
                    if (sibling.leftChild == null || sibling.leftChild.isBlack) {
                        sibling.rightChild.isBlack = true;
                        sibling.isBlack = false;
                        rotate(sibling.rightChild, sibling);
                        sibling = current.parent.leftChild;
                    }

                    // Case 4: Do this everytime
                    sibling.isBlack = sibling.parent.isBlack;
                    sibling.parent.isBlack = true;

                    if (sibling.leftChild != null) {
                        sibling.leftChild.isBlack = true;
                        rotate(sibling, current.parent);
                    }
                    current = root;
                }

                
            }
            
        }

        current.isBlack = true;  

    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    @Override
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }

    /**
     * Returns an iterator over the values in in-order (sorted) order.
     * @return iterator object that traverses the tree in in-order sequence
     */
    @Override
    public Iterator<T> iterator() {
        // use an anonymous class here that implements the Iterator interface
        // we create a new on-off object of this class everytime the iterator
        // method is called
        return new Iterator<T>() {
            // a stack and current reference store the progress of the traversal
            // so that we can return one value at a time with the Iterator
            Stack<Node<T>> stack = null;
            Node<T> current = root;

            /**
             * The next method is called for each value in the traversal sequence.
             * It returns one value at a time.
             * @return next value in the sequence of the traversal
             * @throws NoSuchElementException if there is no more elements in the sequence
             */
            public T next() {
                // if stack == null, we need to initialize the stack and current element
                if (stack == null) {
                    stack = new Stack<Node<T>>();
                    current = root;
                }
                // go left as far as possible in the sub tree we are in until we hit a null
                // leaf (current is null), pushing all the nodes we fund on our way onto the
                // stack to process later
                while (current != null) {
                    stack.push(current);
                    current = current.leftChild;
                }
                // as long as the stack is not empty, we haven't finished the traversal yet;
                // take the next element from the stack and return it, then start to step down
                // its right subtree (set its right sub tree to current)
                if (!stack.isEmpty()) {
                    Node<T> processedNode = stack.pop();
                    current = processedNode.rightChild;
                    return processedNode.data;
                } else {
                    // if the stack is empty, we are done with our traversal
                    throw new NoSuchElementException("There are no more elements in the tree");
                }

            }

            /**
             * Returns a boolean that indicates if the iterator has more elements (true),
             * or if the traversal has finished (false)
             * @return boolean indicating whether there are more elements / steps for the traversal
             */
            public boolean hasNext() {
                // return true if we either still have a current reference, or the stack
                // is not empty yet
                return !(current == null && (stack == null || stack.isEmpty()) );
            }
            
        };
    }

    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    @Override
    public String toString() {
        // use the inorder Iterator that we get by calling the iterator method above
        // to generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        Iterator<T> treeNodeIterator = this.iterator();
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (treeNodeIterator.hasNext())
            sb.append(treeNodeIterator.next());
        while (treeNodeIterator.hasNext()) {
            T data = treeNodeIterator.next();
            sb.append(", ");
            sb.append(data.toString());
        }
        sb.append(" ]");
        return sb.toString();
    }

    // public static void main(String[] args) {
    //     RedBlackTree<Integer> tree = new RedBlackTree<>();

    //     for (int i = 1; i < 10; i++) {
    //         tree.insert(i);
    //     }

    //     tree.remove(1);
    //     tree.remove(2);
    //     tree.remove(3);
    //     tree.remove(4);
    //     tree.remove(5);
    //     tree.remove(6);
    //     tree.remove(7);
    //     tree.remove(8);
    //     tree.remove(9);

    //     System.out.println(tree.toString());
    // }

}
