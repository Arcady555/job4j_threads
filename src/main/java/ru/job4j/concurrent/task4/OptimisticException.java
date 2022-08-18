package ru.job4j.concurrent.task4;

public class OptimisticException extends RuntimeException {

    public OptimisticException(String message) {
        super(message);
    }
}