package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int pointer = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        pointer = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return pointer >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[pointer--];
    }
}
