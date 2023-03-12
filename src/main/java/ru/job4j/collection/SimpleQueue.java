package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int numberOfPlates = 0;
    private boolean directionSwitch = true;

    private void platesMover(SimpleStack<T> first, SimpleStack<T> second) {
        for (int i = 0; i < numberOfPlates; i++) {
            first.push(second.poll());
        }
    }

    public T poll() {
        if (numberOfPlates == 0) {
            throw new NoSuchElementException();
        }
        if (!directionSwitch) {
            platesMover(out, in);
        }
        numberOfPlates--;
        directionSwitch = true;
        return out.poll();
    }

    public void push(T value) {
        if (directionSwitch) {
            platesMover(in, out);
        }
        in.push(value);
        directionSwitch = false;
        numberOfPlates++;
    }
}
