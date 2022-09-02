package ru.job4j.concurrent.task5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = "Notification " + user.userMame() + " to email " + user.email();
            String body = "Add a new event to " + user.userMame();
            send(subject, body, user.email());
        });
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }
}
