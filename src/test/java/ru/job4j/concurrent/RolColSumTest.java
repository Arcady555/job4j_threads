package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;
import ru.job4j.concurrent.task6.RolColSum;
import ru.job4j.concurrent.task6.Sums;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class RolColSumTest {

    @Test
    public void sum() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[3][3];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = i + j;
            }
        }
        Sums[] expected = new Sums[3];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new Sums();
        }
        expected[0].setRowSum(3);
        expected[0].setColSum(3);
        expected[1].setRowSum(6);
        expected[1].setColSum(6);
        expected[2].setRowSum(9);
        expected[2].setColSum(9);
        assertEquals(Arrays.stream(expected).toList(), Arrays.stream(RolColSum.sum(matrix)).toList());
        assertEquals(Arrays.stream(expected).toList(), Arrays.stream(RolColSum.asyncSum(matrix)).toList());
    }
}