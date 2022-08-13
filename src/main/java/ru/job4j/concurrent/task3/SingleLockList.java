package ru.job4j.concurrent.task3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList() {
        this.list = new ArrayList<>();
    }

    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    public synchronized boolean add(T value) {
        return list.add(value);
    }

    public synchronized T get(int index) {
        return (T) copy(list).get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(list).iterator();
    }

    private List<T> copy(List list) {
        return new ArrayList<>(list);
    }
}