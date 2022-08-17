package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount(int setStart) {
        count.set(setStart);
    }

    public void increment() {
        int ref;
        int temp;
        do {
            ref = count.get();
            temp = ref;
            temp++;
        } while (!count.compareAndSet(ref, temp));
    }

    public int get() {
        return count.get();
    }
}