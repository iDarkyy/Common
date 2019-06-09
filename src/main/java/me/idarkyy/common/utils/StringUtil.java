package me.idarkyy.common.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StringUtil {
    private static final String STRING_ENTRIES = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";

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

    public static String join(String[] strings, String delimiter) {
        return join(Arrays.asList(strings), delimiter);
    }

    public static String join(String[] strings, String delimiter, String lastDelimiter) {
        return join(Arrays.asList(strings), delimiter, lastDelimiter);
    }

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    public static String createRandomString(int length, String entries) {
        Random random = new Random();

        StringBuilder string = new StringBuilder();
        for (int i = 0; i < length; i++) {
            string.append(entries.charAt(random.nextInt(entries.length())));
        }

        return string.toString();
    }

    public static String createRandomString(int length) {
        return createRandomString(length, STRING_ENTRIES);
    }

    public static String parse(String text, String... optArguments) {
        for (int i = 0; i < optArguments.length; i++) {
            text = text.replace("$" + i, optArguments[i]);
        }

        return text;
    }
}
