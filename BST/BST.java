public class BST {
    private Node root;
    private int comparisons = 0;
    private String dir = "left";

    static class Node {
        int value;
        Node left;
        Node right;
        int counter;

        Node(int value) {
            this.value = value;
            right = null;
            left = null;
            this.counter = 0;
        }
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            current.counter++;
            return current;
        }

        return current;
    }

    void insert(int value) {
        root = addRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null)
            return false;
        comparisons++;
        if (value == current.value)
            return true;
        comparisons++;
        return value < current.value ? containsNodeRecursive(current.left, value) : containsNodeRecursive(current.right, value);
    }

    boolean find(int value) {
        return containsNodeRecursive(root, value);
    }

    private Node deleteRecurs(Node current, int value) {
        comparisons++;
        if (current == null) {
            return null;
        }

        comparisons++;
        if (value == current.value) {

            if (current.counter == 0) {
                if (current.left == null && current.right == null) {
                    return null;
                }
                if (current.right == null) {
                    return current.left;
                }
                if (current.left == null) {
                    return current.right;
                }

                if (dir.equals("left")) {
                    int maxValue = findMaxValue(current.left);
                    current.value = maxValue;
                    dir = "right";
                    current.left = deleteRecurs(current.left, maxValue);
                    return current;
                } else if (dir.equals("right")){
                    int minValue = findMinValue(current.right);
                    current.value = minValue;
                    dir = "left";
                    current.right = deleteRecurs(current.right, minValue);
                    return current;
                }
            } else {
                current.counter--;
            }
        }

        comparisons++;
        if (value < current.value) {
            current.left = deleteRecurs(current.left, value);
            return current;
        }

        current.right = deleteRecurs(current.right, value);
        return current;
    }

    private int findMinValue(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    private int findMaxValue(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }

    void delete(int value) {
        root = deleteRecurs(root, value);
    }

    void printInorder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.println(node.value + " - counter: " + node.counter);
//            System.out.println(node.value);
            traverseInOrder(node.right);
        }
    }

    void printPreorder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node node) {
        if (node != null) {
            System.out.println(node.value + " - counter: " + node.counter);
//            System.out.println(node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    void printPostorder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
//            System.out.println(node.value);
            System.out.println(node.value + " - counter: " + node.counter);
        }
    }

    void printNodeComparisons() {
        System.out.println(comparisons);
    }


    public static void main(String[] args) {
        BST b = new BST();

        b.insert(19);
        b.printNodeComparisons();
        System.out.println("--");

        b.insert(11); b.insert(23); b.insert(31); b.insert(42); b.insert(29);
        System.out.println( b.find(29) );
        System.out.println("--");

        b.insert(23); b.insert(29); b.delete(31);
        System.out.println( b.find(31) );
        System.out.println( b.find(23) );
        System.out.println("--");
        b.printInorder();
        System.out.println("--");

        b.printNodeComparisons();
    }
}
