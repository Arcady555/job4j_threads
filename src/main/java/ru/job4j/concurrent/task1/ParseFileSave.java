package ru.job4j.concurrent.task1;

import java.io.*;

public class ParseFileSave {

    private final File file;

    public ParseFileSave(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public void saveContent(String content) throws IOException {
        try (BufferedOutputStream bOS = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                bOS.write(content.charAt(i));
            }
        }
    }
}
