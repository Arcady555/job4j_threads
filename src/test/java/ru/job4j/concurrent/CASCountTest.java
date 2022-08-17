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
                    for (int i = 0; i < 64; i++) {
                        casCount.increment();
                    }
                }
                );
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 64; i++) {
                        casCount.increment();
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        Assert.assertThat(casCount.get(), is(128));
    }
}