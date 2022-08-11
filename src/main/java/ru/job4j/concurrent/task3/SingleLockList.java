package ru.job4j.concurrent.task3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList() {
        this.list = new DecoratorList<T>().clone();
    }

    public SingleLockList(List<T> list) {
        this.list = new DecoratorList<T>(list).clone();
    }

    public synchronized boolean add(T value) {
        return list.add(value);
    }

    public synchronized T get(int index) {
        return (T) list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return list.iterator();
    }
}