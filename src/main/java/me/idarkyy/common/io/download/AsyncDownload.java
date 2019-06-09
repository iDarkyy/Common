package me.idarkyy.common.io.download;

import java.io.*;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class AsyncDownload {
    private final File outputFile;

    private int readBytes;

    public AsyncDownload(File outputFile) {
        this.outputFile = outputFile;
    }

    public Future start(InputStream inputStream, Consumer<File> finish, Consumer<IOException> error) {
        return Executors.newSingleThreadExecutor().submit(() -> {
            try (BufferedInputStream in = new BufferedInputStream(inputStream);
                 FileOutputStream out = new FileOutputStream(outputFile)) {
                byte[] dataBuffer = new byte[1024];

                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    out.write(dataBuffer, 0, bytesRead);
                    readBytes += bytesRead;
                }

                finish.accept(outputFile);
            } catch (IOException e) {
                error.accept(e);
            }
        });
    }

    public Future start(URL url, Consumer<File> finish, Consumer<IOException> error) throws IOException {
        return start(url.openStream(), finish, error);
    }

    public int getReadBytes() {
        return readBytes;
    }

    public File getOutputFile() {
        return outputFile;
    }
}
