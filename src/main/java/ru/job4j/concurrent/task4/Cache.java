package ru.job4j.concurrent.task4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (a, b) -> {
            int v1 = model.getVersion();
            int v2 = memory.get(model.getId()).getVersion();
            if (v1 != v2) {
                throw new RuntimeException("Versions are not equal");
            }
            v1++;
            b = new Base(model.getId(), v1);
            b.setName(model.getName());
            return b;
        }) == null;
    }

    public void delete(Base model) {
        memory.remove(2);
    }

    public Map<Integer, Base> getMemoryForTest() {
        return memory;
    }
}