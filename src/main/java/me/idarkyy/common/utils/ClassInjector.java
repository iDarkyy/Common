package me.idarkyy.common.utils;

import me.idarkyy.common.annotation.Unstable;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

@Unstable
public class ClassInjector {
    private static ClassLoader loader;
    private static Method addUrlMethod;

    static {
        try {
            loader = ClassLoader.getSystemClassLoader();
            addUrlMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addUrlMethod.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ClassInjector() {

    }

    public static void injectToRuntime(File jarFile) {
        injectToRuntime(jarFile.toURI());
    }

    public static void injectToRuntime(URI uri) {
        try {
            injectToRuntime(uri.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void injectToRuntime(URL url) {
        addURL(url);
    }

    private static void addURL(URL url) {
        try {
            addUrlMethod.invoke(loader, url);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void loadClass(String path) throws ClassNotFoundException {
        loader.loadClass(path);
    }
}
