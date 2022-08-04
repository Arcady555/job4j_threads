package ru.job4j.concurrent.task1;

import java.io.*;

public final class ParseFileGet {
    private final File file;

    StrategyGetContent sGC = new StrategyGetContent();

    public ParseFileGet(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() throws IOException {
        return sGC.content(s -> true, file);
    }

    public String getContentWithoutUnicode() throws IOException {
        return sGC.content(s -> s < 0x80, file);
    }
}