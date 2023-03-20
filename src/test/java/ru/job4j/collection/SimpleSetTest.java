package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenAddNothingSetContainsIsFalse() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.contains(2)).isFalse();
    }

    @Test
    void whenAddElementHasNextIsTrue() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void whenAddTwoStrings() {
        Set<String> set = new SimpleSet<>();
        assertThat(set.add("2")).isTrue();
        assertThat(set.contains("2")).isTrue();
    }
}
