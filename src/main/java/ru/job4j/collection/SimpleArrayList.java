package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size  == container.length) {
            arrayExpansion();
        }
        container[size++] = value;
        modCount++;
    }

    private void arrayExpansion() {
        int newCapacity = (container.length == 0) ? 1 : container.length * 2;
        container = Arrays.copyOf(container, newCapacity);
    }

    @Override
    public T set(int index, T newValue) {
        T originalValue = get(index);
        container[index] = newValue;
        return originalValue;
    }

    @Override
    public T remove(int index) {
        T removedElement = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );
        size--;
        container[size] = null;
        modCount++;
        return removedElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int subModCount = modCount;
            private int pointer = 0;
            @Override
            public boolean hasNext() {
                if (subModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[pointer++];
            }
        };
    }
}
