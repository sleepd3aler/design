package ru.collection.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        if (key.compareTo(node.key) < 0) {
            if (Objects.isNull(node.left)) {
                node.left = new Node(key);
                return true;
            } else {
                return put(node.left, key);
            }
        } else if (key.compareTo(node.key) > 0) {
            if (Objects.isNull(node.right)) {
                node.right = new Node(key);
                return true;
            } else {
                return put(node.right, key);
            }
        } else {
            return false;
        }
    }

    public boolean contains(T key) {

        return find(root, key) != null;
    }

    private Node find(Node node, T key) {
        if (node == null) {
            return null;
        }
        Node current = node;
        if (key.compareTo(current.key) == 0) {
            return current;
        }
        if (key.compareTo(current.key) < 0) {
                current = current.left;
                return find(current, key);
        }
        if (key.compareTo(current.key) > 0) {
                current = current.right;
                return find(current, key);
        }
        return null;
    }

    public boolean remove(T key) {
        /* Метод будет реализован в следующих уроках */
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> res = new ArrayList<>();
        Node node = root;
        return inPreOrder(node, res);
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);

        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> res = new ArrayList<>();
        Node node = root;
        return inPostOrder(node, res);
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        if (node.left != null) {
            return minimum(node.left);
        }
        return node;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        if (node.right != null) {
            return maximum(node.right);
        }
        return node;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }

}
