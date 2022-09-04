package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParSearchIndexInArrayTest {

    @Test
    public void whenString() {
        String[] array =
                new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "a", "j", "k", "l", "m", "n", "a", "p"};
        ArrayList<Integer> rsl = ParSearchIndexInArray.search(array, "a");
        assertEquals(rsl, List.of(0, 8, 14));
    }

    @Test
    public void whenInt() {
        Integer[] array = new Integer[] {1, 2, 3, 4, 2, 6, 7, 8, 9, 2, 11, 12, 2, 14, 7, 2};
        ArrayList<Integer> rsl = ParSearchIndexInArray.search(array, 2);
        assertEquals(rsl, List.of(1, 4, 9, 12, 15));
    }

    @Test
    public void whenBefore10() {
        Integer[] array = new Integer[] {1, 2, 3, 4, 2, 6, 7, 8};
        ArrayList<Integer> rsl = ParSearchIndexInArray.search(array, 2);
        assertEquals(rsl, List.of(1, 4));
    }

    @Test
    public void whenNotFound() {
        Integer[] array = new Integer[] {1, 2, 3, 4, 2, 6, 7, 8, 9, 2, 11, 12, 2, 14, 7, 2};
        ArrayList<Integer> rsl = ParSearchIndexInArray.search(array, 22);
        assertEquals(rsl, List.of());
    }
}