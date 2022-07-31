package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {

    private static final int BUFFER_SIZE = 1024;
    private final String url;
    private final String out;
    private final int speed;

    public Wget(String url, String out, int speed) {
        this.url = url;
        this.speed = speed;
        this.out = out;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(out)) {
            int downloadData = 0;
            byte[] dataBuffer = new byte[BUFFER_SIZE];
            long start = System.currentTimeMillis();
            long finish;
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    finish = System.currentTimeMillis();
                    if (finish - start < 1000) {
                        Thread.sleep(1000 - (finish - start));
                    }
                    start = System.currentTimeMillis();
                    downloadData = 0;
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
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
        String out = args[1];
        int speed = Integer.parseInt(args[2]);
        Thread wget = new Thread(new Wget(url, out, speed));
        wget.start();
        wget.join();
    }
}
