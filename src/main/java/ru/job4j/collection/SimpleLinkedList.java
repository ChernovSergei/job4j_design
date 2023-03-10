package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;
    private Node<E> node;

    public <E> SimpleLinkedList() {
    }

    @Override
    public void add(E value) {
        final Node<E> lastNode = node;
        final Node<E> newNode = new Node<>(value, lastNode);
        node = newNode;
        if (lastNode == null) {
            head = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Node<E> pointer = head;
        int it = 0;
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        while (it != index) {
            pointer = pointer.next;
            it++;
        }
        return pointer.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int position = 0;
            private Node<E> pointer = null;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (pointer == null) {
                    pointer = head;
                } else {
                    pointer = pointer.next;
                }
                position++;
                return pointer.item;
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
