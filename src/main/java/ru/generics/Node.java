package ru.generics;

public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> next;

    public Node(T value, Node<T> next) {
        this.data = value;
        this.next = next;
    }

    public T getData() {
        return data;
    }
}
