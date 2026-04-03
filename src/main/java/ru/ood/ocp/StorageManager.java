package ru.ood.ocp;

import java.util.HashMap;
import java.util.Map;

public class StorageManager {
    Storage storage = new Storage();

    public void add(String string) {
        storage.add(string);
    }

    public void remove(int id) {
        storage.remove(id);
    }

    private static class Storage {

        private final Map<Integer, String> storage = new HashMap<>();
        private int ids = 0;

        public String add(String item) {
            return storage.put(ids++, item);
        }

        public boolean remove(int id) {
            return storage.remove(id) != null;
        }
    }
}
