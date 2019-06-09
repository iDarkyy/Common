package me.idarkyy.common.fileeditor;

import java.io.*;
import java.util.List;

public class SimpleFileEditor {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    private final StringBuilder contents = new StringBuilder();
    private File file;

    private SimpleFileEditor(File file) {
        this.file = file;

        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist");
        }
    }

    public static SimpleFileEditor open(File file) {
        return new SimpleFileEditor(file);
    }

    public Writer writer() {
        return new Writer(this);
    }

    public SimpleFileEditor write(String text) {
        contents.append(text);
        return this;
    }

    public SimpleFileEditor write(List<String> lines, boolean useLineSeparator) {
        for (String line : lines) {
            write(line);

            if (useLineSeparator) {
                write(LINE_SEPARATOR);
            }
        }

        return this;
    }

    public void finish(boolean replace) throws IOException {
        BufferedWriter writer = openBufferedWriter();

        StringBuilder builder = new StringBuilder();

        if (!replace) {
            builder.append(read());
        }

        builder.append(contents);

        writer.write(builder.toString());
        writer.flush();

        System.out.println(builder.toString());

        writer.close();
    }

    public void clearFile() throws IOException {
        BufferedWriter writer = openBufferedWriter();
        writer.write("");
        writer.flush();

        writer.close();

    }

    public StringBuilder read() throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(LINE_SEPARATOR);
        }

        return sb;
    }

    private BufferedWriter openBufferedWriter() throws IOException {
        return new BufferedWriter(new FileWriter(file));
    }

    public static class Writer {
        private final StringBuilder stringBuilder = new StringBuilder();
        private SimpleFileEditor instance;

        private Writer(SimpleFileEditor instance) {
            this.instance = instance;
        }

        public Writer append(String text) {
            stringBuilder.append(text);
            return this;
        }

        public Writer newLine() {
            return append(LINE_SEPARATOR);
        }

        public SimpleFileEditor write() throws IOException {
            return instance.write(stringBuilder.toString());
        }
    }
}
