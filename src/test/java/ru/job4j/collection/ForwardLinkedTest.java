package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ForwardLinkedTest {

    private ForwardLinkedList<Integer> list;

    @BeforeEach
    public void initData() {
        list = new ForwardLinkedList<>();
        list.add(1);
        list.add(2);
    }

    @Test
    void checkIteratorSimple() {
        assertThat(list).hasSize(2);
        list.add(3);
        list.add(4);
        assertThat(list).hasSize(4);
    }

    @Test
    void checkAdd() {
        assertThat(list).containsExactly(1, 2);
        list.add(3);
        assertThat(list).containsExactly(1, 2, 3);
    }

    @Test
    void whenAddAndGet() {
        list.add(3);
        list.add(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetFromOutOfBoundThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenGetNegateIndexThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAndDeleteFirstThenOk() {
        assertThat(list).containsExactly(1, 2);
        list.add(3);
        assertThat(list).containsExactly(1, 2, 3);
        list.deleteFirst();
        assertThat(list).containsExactly(2, 3);
        list.deleteFirst();
        assertThat(list).containsExactly(3);
    }

    @Test
    void whenDeleteFirstFromEmptyListThenException() {
        ForwardLinkedList<Integer> list = new ForwardLinkedList<>();
        assertThat(list).isEmpty();
        assertThatThrownBy(list::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenAddIterHasNextTrue() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenHasIteratorAndAddThenHasNextExceptionThrown() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        list.add(3);
        assertThatThrownBy(it::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenHasIteratorAndAddThenNextExceptionThrown() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        list.add(3);
        assertThatThrownBy(it::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenAddIterNextOne() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    void whenEmptyIterHasNextFals() {
        ForwardLinkedList<Integer> list = new ForwardLinkedList<>();
    }
}