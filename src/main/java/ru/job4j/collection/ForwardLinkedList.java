package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class ForwardLinkedList<E> implements Iterable<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    public <E> ForwardLinkedList() {
    }

    public void add(E value) {
        final Node<E> newNode = new Node<>(value, null);
        if (head != null) {
            Node<E> lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        } else {
            head = newNode;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Node<E> pointer = head;
        int it = 0;
        Objects.checkIndex(index, size);
        while (it != index) {
            pointer = pointer.next;
            it++;
        }
        return pointer.item;
    }

    public E deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<E> firstNode = head.next;
        E removedItem = head.item;
        head.item = null;
        head.next = null;
        head = firstNode;
        return removedItem;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> pointer = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer != null;
            }

            @Override
            public E next() {
                E result;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                result = pointer.item;
                pointer = pointer.next;
                return result;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
