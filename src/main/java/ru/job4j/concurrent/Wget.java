package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {

    private static final int BUFFER_SIZE = 1024;
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("test1.txt")) {
            byte[] dataBuffer = new byte[BUFFER_SIZE];
            long start = System.nanoTime();
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                long finish = System.nanoTime();
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                int currentSpeed = (int) (1024 / (finish - start) * 1000000);
                if (speed < currentSpeed) {
                    Thread.sleep(currentSpeed - speed);
                }
                start = System.nanoTime();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e1) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
