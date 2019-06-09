package me.idarkyy.common.jarfile;

import me.idarkyy.common.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public abstract class JarfileLoader {
    private File jarFile;

    /**
     * Returns the directory the jar file is stored in
     *
     * @return jar file location
     * @see JarfileLoader#getJarFile()
     */
    protected File getJarFileLocation() {
        File file = getJarFile();

        return file.isDirectory() ? file : file.getParentFile();
    }

    /**
     * If the jarFile is virtual, this will return the directory of the running jar file,
     * otherwise it'll return the jar file itself
     *
     * @return jar file location
     * @see JarfileLoader#isVirtual()
     */
    protected File getJarFile() {
        if (jarFile == null) {
            String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            try {
                jarFile = new File(URLDecoder.decode(path, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                // won't happen, but just in case, let's print it out
            }
        }

        return jarFile;
    }

    /**
     * Gets a resource packed in the jarfile
     *
     * @param path Path to the resource
     * @return The resource input stream
     */
    protected InputStream getResourceAsStream(String path) throws IOException {
        return Resource.getResource(path, this.getClass().getClassLoader());
    }

    /**
     * Copies the specified resource from the jarfile to the specified output file
     *
     * @param inputStream The resource
     * @param output      The output file
     * @throws IOException If the file is not found or the file couldn't be written to
     */
    protected void saveResource(InputStream inputStream, File output) throws IOException {
        Resource.copy(inputStream, output);
    }

    /**
     * Copies the specified resource from the jarfile to the specified output file
     *
     * @param path   The resource path
     * @param output The output file
     * @throws IOException              If the file is not found or the file couldn't be written to
     * @throws IllegalArgumentException If the resource does not exist
     */
    public void saveResource(String path, File output) throws IOException {
        InputStream inputStream = getResourceAsStream(path);

        if (inputStream == null) {
            throw new IllegalArgumentException("Resource '" + path + "' not found");
        }

        saveResource(inputStream, output);
    }

    /**
     * If the jar file is virtual (if the class is loaded from a non-file location),
     * this will return true, otherwise, if the jar file is an actual file, this will return false
     *
     * @return is the jarfile virtual
     */
    protected boolean isVirtual() {
        return jarFile.isDirectory();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }
}
