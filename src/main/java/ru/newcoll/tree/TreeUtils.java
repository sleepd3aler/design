package ru.newcoll.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ru.collection.SimpleQueue;
import ru.collection.SimpleStack;

public class TreeUtils<T> {
    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */

    public int countNode(Node<T> root) {
        validateRoot(root);
        int counter = 1;
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            for (Node<T> n : node.getChildren()) {
                queue.push(n);
                counter++;
            }

        }
// добавили ноду в очередь 1(и так до 31 она содержит в итоге)

        return counter;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     *
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */

    public Iterable<T> findAll(Node<T> root) {
        validateRoot(root);
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        List<T> result = new ArrayList<>();
        queue.push(root);
        result.add(root.getValue());
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            for (Node<T> n : node.getChildren()) {
                queue.push(n);
                result.add(n.getValue());
            }

        }

        return result;
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     *
     * @param root   корень дерева
     * @param parent ключ узла-родителя
     * @param child  ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */

    public boolean add(Node<T> root, T parent, T child) {
        validateRoot(root);
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            if (node.getValue().equals(parent)) {
                return node.getChildren().add(new Node<>(child));
            }
            for (Node<T> n : node.getChildren()) {
                stack.push(n);
            }
        }
        return false;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */

    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        validateRoot(root);
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            if (node.getValue().equals(key)) {
                return Optional.of(node);
            }
            for (Node<T> n : node.getChildren()) {
                stack.push(n);
            }
        }
        return Optional.empty();
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */

    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        validateRoot(root);
        if (root.getValue().equals(key)) {
            return Optional.of(root);
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            for (Node<T> n : node.getChildren()) {
                if (n.getValue().equals(key)) {
                    node.getChildren().remove(n);
                    return Optional.of(n);
                }
                stack.push(n);
            }
        }
        return Optional.empty();
    }

    private void validateRoot(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
    }
}
