package me.idarkyy.common.math;

public class Chance {
    private Chance() {

    }

    public static boolean of(Number number, boolean usePercentages) {
        return Math.random() < (usePercentages ? number.doubleValue() / 100 : number.doubleValue());
    }

    public static boolean of(Number number) {
        return of(number, true);
    }
}
