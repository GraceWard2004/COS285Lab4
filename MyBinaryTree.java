

import java.util.*;

/** Creates a MyBinaryTree object that stores unsorted nodes in a binary tree.
 * @param <E> the data type of the elements in the tree
 * @author Abby Pitcairn
 * @version October 18, 2025
 */
public class MyBinaryTree<E extends Comparable<E>> {
    
    /** Root Node of the tree */
    protected Node<E> root;
    
    /** Nested class for a Node object
     * @param <E> the data type of the element in the node
     */
    protected static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;
        
        Node(E data) {
            this.data = data;
        }
    }
    
    /** Recursively searches for the next available insertion spot
     * and inserts a new node with the given value to that space
     * in the tree.
     * @param value - the value to insert into the tree.
     */
    public void insert(E value) {
        root = insertRecursively(root, value);
    }

    /** Recursive helper for insertion.
     * @param current - the current Node being evaluated.
     * @param value - the value to insert into the tree.
     * @return the (potentially new) current Node after insertion.
     */
    private Node<E> insertRecursively(Node<E> current, E value) {
        if (current == null) {
            return new Node<>(value);
        }
        if (value.compareTo(current.data) < 0) {
            current.left = insertRecursively(current.left, value);
        } else if (value.compareTo(current.data) > 0) {
            current.right = insertRecursively(current.right, value);
        }
        return current;
    }
    
    /** Helper function to quickly build a tree from a List of elements.
     * @param elements - a List of data type E to be added to the tree.
     */
    public void buildTree(List<E> elements) {
        for (E element : elements) {
            insert(element);
        }
    }

    /** Performs a breadth-first traversal of the tree and prints each node's data
     * in the order they are encountered.
     */
    public void printBreadthFirst() {
        if (root == null) {
            return;  // if the tree is empty, nothing to print
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> current = queue.remove();
            System.out.print(current.data + " ");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        System.out.println(); // move to a new line after printing all nodes
    }

    /** Recursively searches the tree for a target value.
     * @param target - the value to search for in the tree.
     * @return true if the target exists in the tree, false otherwise.
     */
    public boolean search(E target) {
        return searchRecursively(root, target);
    }

    /** Recursive helper for searching the tree.
     * @param current - the current Node being evaluated.
     * @param target - the value to search for.
     * @return true if the target is found in this subtree, false otherwise.
     */
    private boolean searchRecursively(Node<E> current, E target) {
        if (current == null) {
            return false;  // reached a leaf with no match
        }
        // Compare target with current node's data (using compareTo for generic type E)
        int cmp = target.compareTo(current.data);
        if (cmp == 0) {
            return true;                  // target found
        } else if (cmp < 0) {
            return searchRecursively(current.left, target);   // search in the left subtree
        } else {
            return searchRecursively(current.right, target);  // search in the right subtree
        }
    }
}
