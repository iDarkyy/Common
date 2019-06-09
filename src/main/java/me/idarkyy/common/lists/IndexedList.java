package me.idarkyy.common.lists;

import java.util.*;

public class IndexedList<T> extends ArrayList<T> {
    private ListIterator<T> iterator;

    public static <T> IndexedList<T> ofCollection(Collection<T> collection) {
        IndexedList<T> list = new IndexedList<>();
        list.addAll(collection);

        return list;
    }

    public static <T> IndexedList<T> ofArray(T[] array) {
        return IndexedList.ofCollection(Arrays.asList(array));
    }

    public static <T> IndexedList<T> of(T... array) {
        return IndexedList.ofCollection(Arrays.asList(array));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "there", "blah", "1", "3", "6", "dwkkdwa", "oofo", "dddd");

        IndexedList<String> indexed = IndexedList.ofCollection(list);

        while (indexed.hasNext()) {
            System.out.println("I : " + indexed.next());
        }
    }

    public T next() {
        if (iterator == null) {
            resetIterator();
        }

        if (hasNext()) {
            return iterator.next();
        }

        return null;
    }

    public List<T> next(int amount) {
        if (iterator == null) {
            resetIterator();
        }

        List<T> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (!hasNext()) {
                break;
            }

            list.add(iterator.next());
        }

        return list;
    }

    public T previous() {
        if (iterator == null) {
            resetIterator();
        }

        if (hasPrevious()) {
            return iterator.previous();
        }

        return null;
    }

    public List<T> previous(int amount) {
        if (iterator == null) {
            resetIterator();
        }

        List<T> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (!hasPrevious()) {
                break;
            }

            list.add(iterator.previous());
        }

        return list;
    }

    public boolean hasNext() {
        if (iterator == null) {
            resetIterator();
        }

        return iterator.hasNext();
    }

    public boolean hasPrevious() {
        if (iterator == null) {
            return false;
        }

        return iterator.hasPrevious();
    }

    public List<T> getRemaining() {
        List<T> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);

        return list;
    }

    public void resetIterator() {
        iterator = listIterator();
    }
}
