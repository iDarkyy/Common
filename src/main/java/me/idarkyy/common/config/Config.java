package me.idarkyy.common.config;

import me.idarkyy.common.annotation.Unstable;
import me.idarkyy.common.math.Numbers;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Unstable("Probably won't work")
public class Config {
    private static final Pattern sectionPattern = Pattern.compile("([0-9a-zA-Z_\\-.@$]+)[ ]?=[ ]?([\"'].+[\"']|[a-zA-Z0-9 _\\-]+)");
    public static String COMMENT_SEQUENCE = "#";
    private HashMap<String, Object> sections = new HashMap<>();

    public Config load(File file) throws IOException {
        return load(new FileInputStream(file));
    }

    public Config load(InputStream inputStream) throws IOException {
        sections.clear();

        for (String section : read(inputStream)) {
            if (section.startsWith(COMMENT_SEQUENCE)) {
                continue;
            }

            Matcher matcher = sectionPattern.matcher(section);

            if (matcher.find()) {
                put(matcher.group(1), matcher.group(2));
            }
        }

        return this;
    }

    public void save(File file) {

    }

    public Object get(String key, Object def) {
        if (sections.containsKey(key)) {
            return sections.get(key);
        }

        return def;
    }

    public Object get(String key) {
        return get(key, null);
    }

    public String getString(String key, String def) {
        return String.valueOf(get(key, def));
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public Integer getInteger(String key, int def) {
        Object obj = get(key);

        if (Numbers.isInteger(obj)) {
            return Numbers.asInteger(obj);
        }

        return def;
    }

    public Integer getInteger(String key) {
        return getInteger(key, -1);
    }

    public Double getDouble(String key, double def) {
        Object obj = get(key);

        if (Numbers.isDouble(obj)) {
            return Numbers.asDouble(obj);
        }

        return def;
    }

    public Double getDouble(String key) {
        return getDouble(key, -1);
    }

    public Config set(String key, Object value) {
        sections.put(key, value);

        return this;
    }

    private void put(String key, String value) {
        if (value.charAt(1) == '"' && value.charAt(value.length() - 1) == '"') {
            sections.put(key, value.substring(1, value.length() - 1));
            return;
        }


        if (Numbers.isDouble(value)) {
            sections.put(key, Numbers.asDouble(value));
        }
    }

    private List<String> read(InputStream inputStream) throws IOException {
        List<String> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String s;
        while ((s = reader.readLine()) != null) {
            list.add(s);
        }

        return list;
    }
}
