package ru.newcoll.tree;

import java.util.ArrayList;
import java.util.List;
import ru.collection.SimpleQueue;

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

    private void validateRoot(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
    }
}
