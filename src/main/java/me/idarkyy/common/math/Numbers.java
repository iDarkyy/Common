package me.idarkyy.common.math;

import java.util.HashMap;

public class Numbers {
    private static String FIRST_ORDINAL_NUMBER = "st";
    private static String SECOND_ORDINAL_NUMBER = "nd";
    private static String THIRD_ORDINAL_NUMBER = "rd";
    private static String OTHER_ORDINAL_NUMBERS = "th";

    private static String HUNDRED = "hundred";
    private static String THOUSAND = "thousand";
    private static String MILLION = "million";
    private static String TRILLION = "trillion";
    // soon more

    private static HashMap<Integer, String> numberTexts = new HashMap<>();

    static {
        numberTexts.put(0, ""/*"zero"*/);
        numberTexts.put(1, "one");
        numberTexts.put(2, "two");
        numberTexts.put(3, "three");
        numberTexts.put(4, "four");
        numberTexts.put(5, "five");
        numberTexts.put(6, "six");
        numberTexts.put(7, "seven");
        numberTexts.put(8, "eight");
        numberTexts.put(9, "nine");

        numberTexts.put(11, "eleven");
        numberTexts.put(12, "twelve");
        numberTexts.put(13, "thirteen");
        numberTexts.put(14, "fourteen");
        numberTexts.put(15, "fifteen");
        numberTexts.put(16, "sixteen");
        numberTexts.put(17, "seventeen");
        numberTexts.put(18, "eighteen");
        numberTexts.put(19, "nineteen");

        numberTexts.put(10, "ten");
        numberTexts.put(20, "twenty");
        numberTexts.put(30, "thirty");
        numberTexts.put(40, "forty");
        numberTexts.put(50, "fifty");
        numberTexts.put(60, "sixty");
        numberTexts.put(70, "seventy");
        numberTexts.put(80, "eighty");
        numberTexts.put(90, "ninety");
    }

    private Numbers() {

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
}
