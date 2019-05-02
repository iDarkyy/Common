package me.idarkyy.common.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
    private StringUtil() {

    }

    public static String join(List<String> list, String delimiter) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i));

            if (i != list.size() - 1) {
                builder.append(delimiter);
            }
        }

        return builder.toString();
    }

    public static String join(String[] strings, String delimiter) {
        return join(Arrays.asList(strings), delimiter);
    }

    public static String join(List<String> list, String delimiter, String lastDelimiter) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i));

            if (i != list.size() - 1) {
                builder.append(lastDelimiter);
            } else if (i != list.size() - 2) {
                builder.append(delimiter);
            }
        }

        return builder.toString();
    }

    public static String join(String[] strings, String delimiter, String lastDelimiter) {
        return join(Arrays.asList(strings), delimiter, lastDelimiter);
    }
}
