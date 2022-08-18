package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {

    private final AtomicInteger count = new AtomicInteger();

    public CASCount() {
        count.set(0);
    }

    public CASCount(int setStart) {
        count.set(setStart);
    }

    public void increment() {
        int ref;
        do {
            ref = count.get();
        } while (!count.compareAndSet(ref, ref + 1));
    }

    public int get() {
        return count.get();
    }
}