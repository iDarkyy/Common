package me.idarkyy.common.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This utility helps select random elements
 *
 * @param <T> randomizer element type
 */
public class Randomizer<T> {
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private List<T> elements = new ArrayList<>();
    private T selected;

    /**
     * Adds a single element
     *
     * @param element the element
     */
    public Randomizer<T> addElement(T element) {
        elements.add(element);
        return this;
    }

    /**
     * Adds a collection of elements
     *
     * @param elements the elements
     */
    public Randomizer<T> addElements(Collection<T> elements) {
        elements.forEach(this::addElement);
        return this;
    }

    /**
     * Adds an array of elements
     *
     * @param elements the elements
     */
    @SafeVarargs
    public final Randomizer<T> addElements(T... elements) {
        return addElements(Arrays.asList(elements));
    }

    /**
     * Removes a single element
     *
     * @param element the element
     */
    public Randomizer<T> removeElement(T element) {
        elements.remove(element);
        return this;
    }

    /**
     * Removes a collection of elements
     *
     * @param elements the elements
     */
    public Randomizer<T> removeElements(Collection<T> elements) {
        elements.forEach(this::removeElement);
        return this;
    }

    /**
     * Removes an array of elements
     *
     * @param elements the elements
     */
    public Randomizer<T> removeElements(T... elements) {
        return removeElements(Arrays.asList(elements));
    }

    /**
     * Clears all of the elements
     */
    public Randomizer<T> clearElements() {
        elements.clear();
        return this;
    }

    /**
     * Gets a random element
     *
     * @return a random element out of the specified elements
     */
    public T select() {
        return elements.isEmpty()
                ? null // returns null if the elements are empty
                : elements.get(random.nextInt(0, elements.size())); // selects a random element
    }

    /**
     * Gets multiple random elements
     *
     * @return a collection random elements out of the specified elements
     */
    public Collection<T> selectMultiple(int amount) {
        List<T> elements = new ArrayList<>();

        while (elements.size() <= amount) {
            elements.add(select());
        }

        return elements;
    }

    /**
     * All of the elements to be chones from
     *
     * @return a collection of the elements
     */
    public Collection<T> getElements() {
        return elements;
    }

    /**
     * Sets all of the elements to the specified collection
     *
     * @param elements the elements
     */
    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
