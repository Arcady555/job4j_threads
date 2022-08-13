package ru.job4j.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {

    @Test
    public void whenOfferAndPoll() throws InterruptedException {
        int limit = 3;
        SimpleBlockingQueue<Integer> list = new SimpleBlockingQueue<>(limit);
        List<Integer> producer = new ArrayList<>();
        producer.add(1);
        producer.add(2);
        producer.add(3);
        producer.add(4);
        producer.add(5);
        List<Integer> consumer = new ArrayList<>();
        Thread first = new Thread(() -> {
            try {
                int i = 0;
                while (i < limit) {
                    list.offer(producer.get(i));
                    producer.set(i, 0);
                    i++;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread second = new Thread(() -> {
            try {
                int i = 0;
                while (i < limit) {
                    consumer.add(list.poll());
                    i++;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        Assert.assertThat(producer, is(List.of(0, 0, 0, 4, 5)));
        Assert.assertThat(consumer, is(List.of(1, 2, 3)));
    }
}