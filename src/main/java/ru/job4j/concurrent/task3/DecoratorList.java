package ru.job4j.concurrent.task3;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DecoratorList<T> implements List<T>, Cloneable {

    protected List<T> list;

    public DecoratorList() {
        this.list = (List<T>) Stream.of(new Object[]{}).collect(Collectors.toList());
    }

    public DecoratorList(List<T> list) {
        this.list = list;
    }

    @Override
    public DecoratorList<T> clone() {
        try {
            return (DecoratorList<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        List.super.sort(c);
    }

    @Override
    public Spliterator<T> spliterator() {
        return List.super.spliterator();
    }

    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        return List.super.toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return List.super.removeIf(filter);
    }

    @Override
    public Stream<T> stream() {
        return List.super.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return List.super.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        List.super.forEach(action);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return list.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private List copy(List list) {
        List newList = Collections.emptyList();
        Collections.copy(newList, list);
        return newList;
    }
}