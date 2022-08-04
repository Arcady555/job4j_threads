package ru.job4j.concurrent.task1;

import java.io.*;
import java.util.function.Predicate;

public class StrategyGetContent {

    public String content(Predicate<Integer> filter, File file) throws IOException {
        try (BufferedInputStream bIS = new BufferedInputStream(new FileInputStream(file))) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = bIS.read()) != -1) {
                if (filter.test(data)) {
                    output.append((char) data);
                }
            }
            return output.toString();
        }
    }
}
