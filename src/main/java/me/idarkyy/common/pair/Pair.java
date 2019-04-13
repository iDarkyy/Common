package me.idarkyy.common.pair;

import java.io.Serializable;

public class Pair<F, S> implements Serializable {
    private F first;
    private S second;

    public Pair() {

    }

    public static <F, S> Pair<F, S> of(F first, S second) {
        Pair<F, S> pair = new Pair<>();
        pair.set(first, second);

        return pair;
    }

    public void set(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public ImmutablePair<F, S> toImmutablePair() {
        return new ImmutablePair<>(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }

        Pair pair = (Pair) obj;

        return (this.first == pair.first) && (this.second == pair.second);
    }

    @Override
    public String toString() {
        return "{1:\"" + first.toString() + "\",2:\"" + second.toString() + "\"}";
    }
}
