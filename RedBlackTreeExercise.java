//A redblack tree is a self balancing BST
//1. Every node is either red or black
//2. Root is always black
//3. Every leaf which is nil is black
//4. If node is red then it's children are black
//5. Every path from a node to any of it's descendant NIL node has
//   same number of Black nodes

public class RedBlackTreeExercise<T extends Comparable<T>> {
    public class Node {
        T data; // holds the key
        Node parent; // pointer to the parent
        Node left; // pointer to the left child
        Node right; // pointer to the right child
        int color; // 1. Red, O. Black
        int size; // size of the current node
    }

    private Node TNULL;
    private Node root;
    private int rankIndex;

    // ctor for initialization
    public RedBlackTreeExercise() {
        TNULL = new Node();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        TNULL.size = 0;
        TNULL.parent = null;
        root = TNULL;
    }

    // Get the root node
    public Node getRootNode() {
        return this.root;
    }

    // Add a new item to the tree
    public void add(T s) {
        // Ordinary Binary Search Insertion
        Node node = new Node();
        node.parent = null;
        node.data = s;
        node.left = TNULL;
        node.right = TNULL;
        node.color = 1; // new node must be red

        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data.compareTo(x.data) < 0) {
                x = x.left;
            } else if (node.data.compareTo(x.data) > 0) {
                x = x.right;
            } else if (node.data.compareTo(x.data) == 0)
                return;
        }

        // y is parent of x
        Node z;
        node.parent = y;
        z = node;
        if (y == null) {
            root = node;
        } else if (node.data.compareTo(y.data) < 0) {
            y.left = node;
            // Update the size
            updateSizeHelper(y.left);
        } else if (node.data.compareTo(y.data) > 0) {
            y.right = node;
            // Update the size;
            updateSizeHelper(y.right);
        }
        // If new node has been inserted update the size
        // if new node is a root node, simply return
        //If tree is empty color the node with black
        if (node.parent == null) {
            node.color = 0;
            return;
        }

        // if the grandparent is null, simply return
        if (node.parent.parent == null) {
            return;
        }

        // Fix the tree
        fixInsert(node);
    }

    // The number of items that have been added to the tree
    public int size() {
        return this.root.size;
    }

    // Find an item by it's rank according to the natural
    // comparable order
    public T get(int rank) {
        Node find;
        int i;
        if ((rank >= 0) && (rank < this.root.size)) {
            i = rank + 1;
            find = select(this.root, i);
            return find.data;
        }
        return null;
    }

    // Search for an item that was previously added to the tree
    public int rank(T s) {
        // search for the node
        Node x = search(s);

        if (x == TNULL)
            return -1; // Doesn't exist
        

        int r = (x.left.size) + 1;
        Node y = x;

        while (y != root) {
            if (y == y.parent.right) {
                r = r + (y.parent.left.size) + 1;
            }
            y = y.parent;
        }
        return r - 1;
    }

    // Update the size of a node helper
    private void updateSizeHelper(Node node) {
        // Calculate the size
        // size(x) = size(left) + size(right) + 1;
        Node x = node;
        int size;
        while (x != null) {
            size = (x.left.size + x.right.size) + 1;
            x.size = size;
            x = x.parent;
        }
    }

    // Inorder helper function
    public void inOrderHelper(Node node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.println(node.data + " ");
            inOrderHelper(node.right);
        }

    }

    // Select helper function
    private Node select(Node node, int i) {
        int r = ((node.left.size) + 1);
        if (i == r)
            return node;
        else if (i < r)
            return select(node.left, i);

        return select(node.right, i - r);

    }

    // Seaarch helper function
    public Node search(T key) {
        Node x = this.root;
        while (x != TNULL) {
            // compare if the key is the one we searching for
            if (key.compareTo(x.data) < 0) {
                // move left
                x = x.left;
            } else if (key.compareTo(x.data) > 0) {
                // move right
                x = x.right;
            } else if (key.compareTo(x.data) == 0) {
                return x;
            }
        }

        return TNULL;
    }

    private void printHelper(Node root, String indent, boolean last) {
        // print the tree structure on the screen
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = root.color == 1 ? "RED" : "BLACK";
            System.out.println(root.data + "(" + sColor + ") (" + root.size + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    // print the tree structure on the screen
    public void prettyPrint() {
        printHelper(this.root, "", true);
    }

    

    // rotate right at node x
    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
        // Update the sizes
        x.size = y.size;
        y.size = (y.left.size + y.right.size) + 1;
    }

    // rotate left at node x
    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
        // Update the sizes
        y.size = x.size;
        x.size = (x.left.size + x.right.size) + 1;
    }

    // fix the red-black tree
    private void fixInsert(Node k) {
        Node u;
        //Check the color of the parent node if it's red
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle 
                // Check if the sibling of the parent color if it's red
                // If it is red then just recolor
                // If it is black then apply suitable rotations
                if (u.color == 1) { 
                    // case 3.1
                    u.color = 0; //Color k's parent sibling black
                    k.parent.color = 0; // Color k's parent black
                    k.parent.parent.color = 1; //Then color the node's grandparent red
                    k = k.parent.parent; //Now jump to the grandparent and repeat the same process
                    //until we encounter a root node which is always black
                } else {
                    //Check if the newly inserted node is a left node
                    if (k == k.parent.left) { 
                        // case 3.2.2
                        k = k.parent;
                        rightRotate(k);
                    }
                    // case 3.2.1
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right; // uncle

                if (u.color == 1) {
                    // mirror case 3.1
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // mirror case 3.2.2
                        k = k.parent;
                        leftRotate(k);
                    }
                    // mirror case 3.2.1
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = 0;
    }

}