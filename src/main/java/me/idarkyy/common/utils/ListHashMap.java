package me.idarkyy.common.utils;

import java.util.HashMap;
import java.util.List;

public class ListHashMap<K, LT> extends HashMap<K, List<LT>> {

    public void addToList(K key, LT value) {
        if(containsKey(key)) {
            get(key).add(value);
        }
    }

    public void removeFromList(K key, LT value) {
        if(containsKey(key)) {
            get(key).remove(value);
        }
    }

    public void clearList(K key) {
        if(containsKey(key)) {
            get(key).clear();
        }
    }
}
