package ru.job4j.concurrent.task3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList() {
        this.list = copy(Stream.of(new Object[]{}).collect(Collectors.toList()));
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
        return (List<T>) list.stream().collect(Collectors.toList());
    }
}