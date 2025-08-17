package ru.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        if (table[currentIndex(key)] == null) {
            table[currentIndex(key)] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        K k = null;
        if (table[currentIndex(key)] != null) {
            k = table[currentIndex(key)].key;
        }
        if (keyHashCode(k) == keyHashCode(key) && Objects.equals(k, key)) {
            return table[currentIndex(key)].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (table[currentIndex(key)] != null) {
            table[currentIndex(key)] = null;
            modCount++;
            count--;
            return true;
        }
        return false;
    }

    private int currentIndex(K key) {
        return indexFor(hash(keyHashCode(key)));
    }

    private int hash(int hashcode) {
        return hashcode == 0 ? 0 : hashcode ^ (hashcode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int keyHashCode(K key) {
        return Objects.hashCode(key);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entryToMove : table) {
            if (entryToMove != null) {
                newTable[currentIndex(entryToMove.key)] = entryToMove;
            }
        }
        table = newTable;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int index = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = index; i < capacity; i++) {
                    if (table[i] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                while (hasNext()) {
                    MapEntry<K, V> entry = table[index];
                    index++;
                    if (entry != null) {
                        return entry.key;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
