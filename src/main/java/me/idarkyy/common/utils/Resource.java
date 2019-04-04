package me.idarkyy.common.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class Resource {
    private Resource() {

    }

    public static void save(InputStream input, File to) throws IOException {
        Objects.requireNonNull(input);

        if(to.isDirectory()) {
            throw new IllegalArgumentException("File must not be a directory");
        }

        OutputStream out = new FileOutputStream(to);

        byte[] buf = new byte[1024];

        int len;
        while ((len = input.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        out.close();
        input.close();
    }

    public static InputStream getResource(String resourcePath, ClassLoader loader) throws IOException {
        Objects.requireNonNull(resourcePath);

        resourcePath = resourcePath.replace('\\', '/');

        try {
            URL url = loader.getResource(resourcePath);

            if (url == null) {
                return null;
            }

            URLConnection connection = url.openConnection();

            connection.setUseCaches(false);

            return connection.getInputStream();
        } catch (IOException ex) {
            return null;
        }
    }
}
