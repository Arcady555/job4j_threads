package ru.job4j.concurrent.task1;

import java.io.*;
import java.util.function.Predicate;

public class StrategyGetContent {

    public String content(Predicate<Integer> filter, File file) throws IOException {
        try (BufferedInputStream bIS = new BufferedInputStream(new FileInputStream(file))) {
            String output = "";
            int data;
            while ((data = bIS.read()) > 0) {
                if (filter.test(data)) {
                    output += (char) data;
                }
            }
            return output;
        }
    }
}
