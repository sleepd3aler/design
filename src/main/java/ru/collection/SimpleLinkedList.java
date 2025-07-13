package ru.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {
    private int size;
    private int modCount;
    private Node<E> head;
    private Node<E> tail;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        tail = head;
        for (int i = 0; i < index; i++) {
            tail = tail.next;
        }
        return tail.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> current = head;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E result = current.item;
                    current = current.next;
                    return result;
                }
                return null;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

}
