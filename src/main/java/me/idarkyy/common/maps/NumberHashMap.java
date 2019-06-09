package me.idarkyy.common.maps;

import java.util.HashMap;
import java.util.function.Function;

public class NumberHashMap<K> extends HashMap<K, Number> {
    public Number plus(K key, int amount) {
        return put(key, get(key).intValue() + amount);
    }

    public Number plus(K key, double amount) {
        return put(key, get(key).doubleValue() + amount);
    }

    public Number plus(K key, long amount) {
        return put(key, get(key).longValue() + amount);
    }

    public Number plus(K key, float amount) {
        return put(key, get(key).floatValue() + amount);
    }

    public Number plus(K key, short amount) {
        return put(key, get(key).shortValue() + amount);
    }

    public Number plus(K key, byte amount) {
        return put(key, get(key).byteValue() + amount);
    }

    public Number minus(K key, int amount) {
        return put(key, get(key).intValue() - amount);
    }

    public Number minus(K key, double amount) {
        return put(key, get(key).doubleValue() - amount);
    }

    public Number minus(K key, long amount) {
        return put(key, get(key).longValue() - amount);
    }

    public Number minus(K key, float amount) {
        return put(key, get(key).floatValue() - amount);
    }

    public Number minus(K key, short amount) {
        return put(key, get(key).shortValue() - amount);
    }

    public Number minus(K key, byte amount) {
        return put(key, get(key).byteValue() - amount);
    }

    public Number withExpression(K key, Function<Number, Number> function) {
        return put(key, function.apply(get(key).doubleValue()));
    }
}
