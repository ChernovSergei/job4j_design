package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int numberOfOutPlates = 0;
    private int numberOfInputPlates = 0;

    public T poll() {
        if (numberOfInputPlates + numberOfOutPlates == 0) {
            throw new NoSuchElementException();
        }
        if (numberOfOutPlates == 0) {
            for(int i = 0; i < numberOfInputPlates; i++) {
                out.push(in.poll());
                numberOfOutPlates++;
            }
            numberOfInputPlates = 0;
        }
        numberOfOutPlates--;
        return out.poll();
    }

    public void push(T value) {
        numberOfInputPlates++;
        in.push(value);
    }
}
