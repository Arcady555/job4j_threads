package ru.job4j.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParSearchIndexInArray<T> extends RecursiveTask<Integer>  {

    private final T[] array;
    private final T element;
    private final int from;
    private final int to;

    public ParSearchIndexInArray(T[] array, T element, int from, int to) {
        this.array = array;
        this.element = element;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return lineSearch();
        } else {
            int mid = (from + to) / 2;
            ParSearchIndexInArray<T> left = new ParSearchIndexInArray<>(array, element, from, mid);
            ParSearchIndexInArray<T> right = new ParSearchIndexInArray<>(array, element, mid + 1, to);
            left.fork();
            right.fork();
            return left.join() != -1 ? left.join() : right.join();
        }
    }

    public static <T> Integer search(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParSearchIndexInArray<>(array, element, 0, array.length - 1));
    }

    private int lineSearch() {
        int rsl = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                rsl = i;
            }
        }
        return rsl;
    }
}