package ru.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public final V get(K key) {
        SoftReference<V> reference = cache.get(key);
        V result = reference != null ? reference.get() : null;
            if (result == null) {
                result = load(key);
                put(key, result);
        }
        return result;
    }

    protected abstract V load(K key);
}