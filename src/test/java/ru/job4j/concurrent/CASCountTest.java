package ru.job4j.concurrent;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class CASCountTest {

    @Test
    public void whenGetIncrement() throws InterruptedException {
        CASCount casCount = new CASCount(0);
        Thread first = new Thread(
                () -> {
                    casCount.increment();
                    casCount.get();
                    casCount.increment();
                }
                );
        Thread second = new Thread(
                () -> {
                    casCount.increment();
                    casCount.get();
                    casCount.increment();
                    casCount.increment();
                }
        );
        Thread third = new Thread(
                () -> {
                    casCount.increment();
                    casCount.get();
                }
        );
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();
        Assert.assertThat(casCount.getIntCountForTest(), is(6));
    }
}