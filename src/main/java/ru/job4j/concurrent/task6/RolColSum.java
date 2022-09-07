package ru.job4j.concurrent.task6;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
        }
        for (int i = 0; i < matrix.length; i++) {
            sumRowsAndColumns(matrix, sums, i);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
        }
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTask(matrix, sums, i));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, Sums[] sums, int row) {
        return CompletableFuture.supplyAsync(() -> {
            sumRowsAndColumns(matrix, sums, row);
            return sums[row];
        });
    }

    private static void sumRowsAndColumns(int[][] matrix, Sums[] sums, int row) {
        for (int col = 0; col < matrix.length; col++) {
            int a = sums[row].getRowSum();
            sums[row].setRowSum(a + matrix[row][col]);
            int b = sums[col].getColSum();
            sums[col].setColSum(b + matrix[row][col]);
        }
    }
}
