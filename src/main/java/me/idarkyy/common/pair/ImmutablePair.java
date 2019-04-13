package me.idarkyy.common.pair;

public class ImmutablePair<F, S> extends Pair<F, S> {
    public ImmutablePair() {
        super();
    }

    public ImmutablePair(F first, S second) {
        super();
        set(first, second);
    }

    @Override
    public void set(F first, S second) {
        throwException();
    }

    @Override
    public void setFirst(F first) {
        throwException();
    }

    @Override
    public void setSecond(S second) {
        throwException();
    }

    private void throwException() {
        throw new IllegalStateException("Pair is immutable");
    }
}
