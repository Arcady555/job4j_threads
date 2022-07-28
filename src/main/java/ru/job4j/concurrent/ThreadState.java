package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.print("");
            /**
             * Может лучше всё-таки так оставить, вместо строки ниже?
             * В задании не требовалось распечатывать RUNNABLE...)))
             *
             * System.out.println(Thread.currentThread().getState());
             *
             */
        }
        System.out.println("Работа завершена");
    }
}