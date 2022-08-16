package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private int intCount;

    private final AtomicReference<Integer> count = new AtomicReference<>(intCount);

    public CASCount(int intCount) {
        this.intCount = intCount;
    }

    public void increment() {
        int ref;
        do {
            ref = count.get();
            if (!count.get().getClass().equals(Integer.class)) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
            intCount++;
        } while (!count.compareAndSet(ref, intCount));
    }

    public int get() {
        int ref;
        do {
            ref = count.get();
            if (!count.get().getClass().equals(Integer.class)) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
        } while (!count.compareAndSet(ref, intCount));
        return ref;
    }

    public Integer getIntCountForTest() {
        return intCount;
    }
}
