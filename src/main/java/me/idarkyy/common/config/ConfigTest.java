package me.idarkyy.common.config;

import me.idarkyy.common.jarfile.JarfileLoader;

import java.io.File;
import java.io.IOException;

public class ConfigTest extends JarfileLoader {
    public static void main(String[] args) throws IOException {
        File file = new ConfigTest().getJarFileLocation();

        Config config = new Config();

        long current = System.currentTimeMillis();
        config.load(new File(file, "config.txt"));
        System.out.println("Took " + (System.currentTimeMillis() - current) + "ms");
    }
}
