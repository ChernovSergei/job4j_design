package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int numberOfPlates = 0;
    private boolean directionSwitch = true;

    public T poll() {
        if (!directionSwitch) {
            for (int i = 0; i < numberOfPlates; i++) {
                out.push(in.poll());
            }
        }
        numberOfPlates--;
        directionSwitch = true;
        return out.poll();
    }

    public void push(T value) {
        if (directionSwitch) {
            for (int i = 0; i < numberOfPlates; i++) {
                in.push(out.poll());
            }
        }
        in.push(value);
        directionSwitch = false;
        numberOfPlates++;
    }
}
