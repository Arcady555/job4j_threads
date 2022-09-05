package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParSearchIndexInArrayTest {

    @Test
    public void whenString() {
        String[] array =
                new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"};
        int rsl = ParSearchIndexInArray.search(array, "a");
        assertEquals(rsl, 0);
    }

    @Test
    public void whenInt() {
        Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int rsl = ParSearchIndexInArray.search(array, 2);
        assertEquals(rsl, 1);
    }

    @Test
    public void whenBefore10() {
        Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8};
        int rsl = ParSearchIndexInArray.search(array, 2);
        assertEquals(rsl, 1);
    }

    @Test
    public void whenNotFound() {
        Integer[] array = new Integer[] {1, 2, 3, 4, 2, 6, 7, 8, 9, 2, 11, 12, 2, 14, 7, 2};
        int rsl = ParSearchIndexInArray.search(array, 22);
        assertEquals(rsl, -1);
    }

    @Test
    public void whenFoundLast() {
        Integer[] array = new Integer[] {1, 2, 3, 4, 2, 6, 7, 8, 9, 2, 11, 12, 2, 14, 7, 16};
        int rsl = ParSearchIndexInArray.search(array, 16);
        assertEquals(rsl, 15);
    }
}