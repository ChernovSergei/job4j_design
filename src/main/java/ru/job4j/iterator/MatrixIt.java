package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row + 1 <= data.length && data[row].length == 0) {
            if (column < data[row].length) {
                ++column;
            } else {
                column = 0;
                ++row;
            }
        }
        return !(data.length == 1 && data[data.length - 1].length == 0)
                && row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int result = data[row][column];

        if (column + 1 >= data[row].length) {
            ++row;
            column = 0;
        } else {
            ++column;
        }

        return result;
    }
}
