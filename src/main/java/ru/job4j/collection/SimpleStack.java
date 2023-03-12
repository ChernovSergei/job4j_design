package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinkedList<T> list = new ForwardLinkedList<>();

    public T poll() {
        return list.deleteFirst();
    }

    public void push(T item) {
        list.addFirst(item);
    }
}
