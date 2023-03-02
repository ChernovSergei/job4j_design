package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount = 0;
    private int pointer = 0;
    private Iterator<T> it = iterator();

    private T[] arrayExpansion() {
        int newCapacity = (container.length == 0) ? 1 : container.length * 2;
        container = Arrays.copyOf(container, newCapacity);
        return container;
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size  == container.length) {
            arrayExpansion();
        }

        container[pointer] = value;
        size++;
        it.next();
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);

        T oldValue = container[index];
        container[index] = newValue;

        if (size  == container.length) {
            arrayExpansion();
        }
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);

        T removedElement = get(index);

        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );

        pointer = index;
        size--;
        it.next();

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
            private int subPointer = 0;
            private int subSize = 0;
            private int subModCount = modCount;

            @Override
            public boolean hasNext() {
                if (subModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return subPointer < container.length && size > 0;
            }

            @Override
            public T next() {
                if (subSize > size) {
                    subPointer = pointer;
                }
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }

                subSize = size;
                modCount = ++subModCount;
                pointer = subPointer++;

                return container[pointer++];
            }
        };
    }
}
