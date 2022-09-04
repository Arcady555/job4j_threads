package ru.job4j.concurrent;

import java.util.ArrayList;

public class SearchIndexInArray<T> {

    public ArrayList<Integer> search(T[] array, T element) {
        return search(array, element, 0, array.length - 1);
    }

    public ArrayList<Integer> search(T[] array, T element, int from, int to) {
        ArrayList<Integer> rsl = new ArrayList<>();
        if (to - from <= 10) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(element)) {
                    rsl.add(i);
                }
            }
        } else {
            int mid = (from + to) / 2;
            rsl.addAll(search(array, element, from, mid));
        }
        return rsl;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {1, 2, 3, 4, 2, 6, 7, 2, 9, 10, 2, 2, 13, 14, 15, 2};
        SearchIndexInArray<Integer> sIIA = new SearchIndexInArray<>();
        System.out.println(sIIA.search(array, 2));
    }
}