package ru.isp;

import java.util.Iterator;

public interface Traversable<T> {
    Iterator<T> preOrder();

    Iterator<T> inOrder();

    Iterator<T> postOrder();

    Iterator<T> bfs();

    Iterator<T> dfs();

    interface TreeTraversable<T> {
        Iterator<T> preOrder();

        Iterator<T> inOrder();

        Iterator<T> postOrder();
    }

    interface GraphTraversable<T> {
        Iterator<T> bfs();

        Iterator<T> dfs();
    }

}
