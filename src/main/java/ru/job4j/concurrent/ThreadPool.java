package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public void work(Runnable job) throws InterruptedException {
        AtomicBoolean flag = new AtomicBoolean(false);
        notifyAll();
        tasks.offer(job);
        Thread thread = new Thread(
                () -> {
                    flag.set(true);
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    flag.set(false);
                }
        );
        if (threads.size() < size) {
            doThread(thread, flag);
            threads.add(thread);
        } else {
            for (Thread element : threads) {
                if (element.getState() == Thread.State.WAITING) {
                    element = thread;
                    doThread(element, flag);
                    break;
                }
            }
        }
    }

    public void shutdown() {
        for (Thread thread : threads) {
            if (thread.getState() == Thread.State.WAITING) {
                thread.interrupt();
            }
        }
    }

    private void doThread(Thread thread, AtomicBoolean flag) throws InterruptedException {
        thread.start();
        while (!flag.get()) {
            thread.wait();
        }
    }
}
