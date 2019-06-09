package me.idarkyy.common.io.download;

import java.io.*;
import java.net.URL;

public class Download {
    private Runnable task;

    public Download(InputStream inputStream, File outputFile) {
        createTask(inputStream, outputFile);
    }

    public Download(URL url, File outputFile) throws IOException {
        this(url.openStream(), outputFile);
    }

    public void download() {
        task.run();
    }

    private void createTask(InputStream inputStream, File outputFile) {
        this.task = () -> {
            try (BufferedInputStream in = new BufferedInputStream(inputStream);
                 FileOutputStream out = new FileOutputStream(outputFile)) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    out.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
