package me.idarkyy.common.math;

import java.util.*;

public class Numbers {
    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    private static String FIRST_ORDINAL_NUMBER = "st";
    private static String SECOND_ORDINAL_NUMBER = "nd";
    private static String THIRD_ORDINAL_NUMBER = "rd";
    private static String OTHER_ORDINAL_NUMBERS = "th";

    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    private Numbers() {

    }

    public static double calculateAverageNumber(List<Double> list) {
        return addUp(list) / list.size();
    }

    public static <T> HashMap<T, Double> calculatePercentages(HashMap<T, Double> hashMap) {
        double total = 0;

        for (double d : hashMap.values()) {
            total += d;
        }

        HashMap<T, Double> newMap = new HashMap<>();

        for (T key : hashMap.keySet()) {
            newMap.put(key, 100 * hashMap.get(key) / total);
        }

        return newMap;
    }

    public static double addUp(List<Double> list) {
        int n = 0;

        for (double i : list) {
            n += i;
        }

        return n;
    }

    public static String toOrdinalNumber(int integer) {
        int abs = java.lang.Math.abs(integer);

        if (abs == 1) {
            return integer + FIRST_ORDINAL_NUMBER;
        } else if (abs == 2) {
            return integer + SECOND_ORDINAL_NUMBER;
        } else if (abs == 3) {
            return integer + THIRD_ORDINAL_NUMBER;
        } else if (abs > 3) {
            return integer + OTHER_ORDINAL_NUMBERS;
        }

        return String.valueOf(integer);
    }

    public static boolean isInteger(Object object) {
        if (object instanceof String) {
            try {
                Integer.parseInt((String) object);

                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return object instanceof Integer;
    }

    public static boolean isDouble(Object object) {
        if (object instanceof String) {
            try {
                Double.parseDouble((String) object);

                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return object instanceof Double;
    }

    public static Integer asInteger(Object object) {
        if (object instanceof String) {
            try {
                return Integer.parseInt((String) object);
            } catch (NumberFormatException e) {
                // ignored
            }
        } else if (object instanceof Integer) {
            return (Integer) object;
        }

        return null;
    }

    public static Double asDouble(Object object) {
        if (object instanceof String) {
            try {
                return Double.parseDouble((String) object);
            } catch (NumberFormatException e) {
                // ignored
            }
        } else if (object instanceof Double) {
            return (Double) object;
        }

        return null;
    }

    /**
     * Formats the number with extensions (e.g. K as thousand, M as million, B as billion)
     *
     * @param number number to format
     * @return Formatted number
     */
    public static String formatWithExtension(Number number) {
        long value = number.longValue();

        if (value == Long.MIN_VALUE) {
            return formatWithExtension(Long.MIN_VALUE + 1);
        }
        if (value < 0) {
            return "-" + formatWithExtension(-value);
        }
        if (value < 1000) {
            return Long.toString(value);
        }

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10);
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);

        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    public static double getDecimalAmount(Number number) {
        return Math.log10(number.doubleValue());
    }

    public static boolean isBetween(Number number, int bound1, int bound2) {
        return number.longValue() <= java.lang.Math.max(bound1, bound2) && number.longValue() >= java.lang.Math.min(bound1, bound2);
    }
}
