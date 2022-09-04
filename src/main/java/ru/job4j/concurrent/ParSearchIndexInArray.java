package ru.job4j.concurrent;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParSearchIndexInArray<T> extends RecursiveTask<ArrayList<Integer>>  {

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
    protected ArrayList<Integer> compute() {
        ArrayList<Integer> rsl = new ArrayList<>();
        if (to - from <= 10) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(element)) {
                    rsl.add(i);
                }
            }
        } else {
            int mid = (from + to) / 2;
            ParSearchIndexInArray<T> left = new ParSearchIndexInArray<>(array, element, from, mid);
            left.fork();
            rsl.addAll(left.join());
        }
        return rsl;
    }

    public static <T> ArrayList<Integer> search(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParSearchIndexInArray<>(array, element, 0, array.length - 1));
    }
}
