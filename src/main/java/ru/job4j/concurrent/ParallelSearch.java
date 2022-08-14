package ru.job4j.concurrent;

public class ParallelSearch {
    public static void main(String[] args) {
        int limit = 3;
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(limit);
        final Thread consumer = new Thread(
                () -> {
                    for (int index = 0; index != limit; index++) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != limit; index++) {
                        try {
                            queue.offer(index);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
}
