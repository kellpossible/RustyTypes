package org.kellpossible.rustytypes;

public interface ResultHandler<V, E> {
    void handleValue(V value);
    void handleError(E error);
}
