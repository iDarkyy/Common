package me.idarkyy.common.math;

import java.util.HashMap;
import java.util.List;

public class Math {
    public static double calculateAverageNumber(List<Double> list) {
        return addUp(list) / list.size();
    }

    public static <T> HashMap<T, Double> calculatePercentages(HashMap<T, Double> hashMap) {
        double total = 0;

        for(double d : hashMap.values()) {
            total += d;
        }

        HashMap<T, Double> newMap = new HashMap<>();

        for(T key : hashMap.keySet()) {
            newMap.put(key, 100 * hashMap.get(key) / total);
        }

        return newMap;
    }

    public static double addUp(List<Double> list) {
        int n = 0;

        for(double i : list) {
            n += i;
        }

        return n;
    }
}
