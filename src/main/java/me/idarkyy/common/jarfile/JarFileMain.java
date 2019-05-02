package me.idarkyy.common.jarfile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public abstract class JarFileMain {
    private File jarFile;

    /**
     * Returns the directory the jar file is stored in
     *
     * @return jar file location
     * @see JarFileMain#getJarFile()
     */
    public File getJarFileLocation() {
        File file = getJarFile();

        return file.isDirectory() ? file : file.getParentFile();
    }

    /**
     * If the jarFile is virtual, this will return the directory of the running jar file,
     * otherwise it'll return the jar file itself
     *
     * @return jar file location
     * @see JarFileMain#isVirtual()
     */
    public File getJarFile() {
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
     * If the jar file is virtual (if the class is loaded from a non-file location),
     * this will return true, otherwise, if the jar file is an actual file, this will return false
     *
     * @return is the jarfile virtual
     */
    public boolean isVirtual() {
        return jarFile.isDirectory();
    }
}
