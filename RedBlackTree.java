public class RedBlackTree {
    // Constants to represent node colors
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // Inner class to represent a node in the Red-Black Tree
    private class Node {
        // Data stored in the node
        int data; 

        // Left child, right child, and parent references
        Node left, right, parent; 

        // Node color: RED or BLACK
        boolean color; 

        // Constructor to create a new node with RED color by default
        Node(int data) {
            this.data = data;
            this.color = RED;
        }
    }


    // Root of the Red-Black Tree
    private Node root; 


    // Public method to insert a new value into the tree
    public void insert(int data) {
        // Create a new node with the given data
        Node newNode = new Node(data); 

        // Insert the node in the correct position
        root = insert(root, newNode); 

        // Fix any Red-Black Tree property violations
        fixViolation(newNode); 
    }

    // Recursive helper method to insert a node into the tree
    private Node insert(Node root, Node newNode) {
        // If the current root is null, place the new node here
        if (root == null) return newNode;

        // Determine the position of the new node based on its value
        if (newNode.data < root.data) {
            // Insert into the left subtree
            root.left = insert(root.left, newNode); 
            // Set the parent reference
            root.left.parent = root; 
        } else if (newNode.data > root.data) {
            // Insert into the right subtree
            root.right = insert(root.right, newNode); 
            // Set the parent reference
            root.right.parent = root; 
        }
        // Return the updated root
        return root; 
    }

    // Fix violations of Red-Black Tree properties after insertion
    private void fixViolation(Node node) {
        // Loop to fix any violations caused by inserting a RED node
        while (node != root && node.parent.color == RED) {
            // Get the parent node
            Node parent = node.parent; 
            // Get the grandparent node
            Node grandparent = parent.parent; 

            // Case: Parent is the left child of the grandparent
            if (parent == grandparent.left) {
                // Get the uncle node
                Node uncle = grandparent.right; 

                // Case 1: Uncle is RED (Recoloring)
                if (uncle != null && uncle.color == RED) {
                    grandparent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    
                    // Move up the tree
                    node = grandparent; 
                } else {
                    // Case 2: Node is a right child (Left rotation)
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    // Case 3: Node is a left child (Right rotation)
                    rotateRight(grandparent);
                    parent.color = BLACK;
                    grandparent.color = RED;
                    // Adjust node reference
                    node = parent; 
                }
            // Symmetrical case: Parent is the right child of the grandparent
            } else { 
                // Get the uncle node
                Node uncle = grandparent.left; 

                // Case 1: Uncle is RED (Recoloring)
                if (uncle != null && uncle.color == RED) {
                    grandparent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    // Move up the tree
                    node = grandparent; 
                } else {
                    // Case 2: Node is a left child (Right rotation)
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    // Case 3: Node is a right child (Left rotation)
                    rotateLeft(grandparent);
                    parent.color = BLACK;
                    grandparent.color = RED;
                    // Adjust node reference
                    node = parent; 
                }
            }
        }
        // Ensure the root is always BLACK
        root.color = BLACK; 
    }

    // Perform a left rotation on a given node
    private void rotateLeft(Node node) {
        // Get the right child
        Node temp = node.right; 

        // Move the left subtree of temp to node's right
        node.right = temp.left; 

        // Update parent reference
        if (temp.left != null) temp.left.parent = node; 

        // Update temp's parent reference
        temp.parent = node.parent; 

        // Update the parent's reference to point to temp
        if (node.parent == null) root = temp;
        else if (node == node.parent.left) node.parent.left = temp;
        else node.parent.right = temp;

        temp.left = node;    // Make node the left child of temp
        node.parent = temp;  // Update node's parent
    }

    // Perform a right rotation on a given node
    private void rotateRight(Node node) {
        // Get the left child
        Node temp = node.left; 

        // Move the right subtree of temp to node's left
        node.left = temp.right; 

        // Update parent reference
        if (temp.right != null) temp.right.parent = node; 

        // Update temp's parent reference
        temp.parent = node.parent; 

        // Update the parent's reference to point to temp
        if (node.parent == null) root = temp;
        else if (node == node.parent.right) node.parent.right = temp;
        else node.parent.left = temp;

        temp.right = node;   // Make node the right child of temp
        node.parent = temp;  // Update node's parent
    }
}
