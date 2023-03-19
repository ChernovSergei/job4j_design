package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveFirstValue() {
        ListUtils.removeIf(input, T -> T == 1);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenReplaceSecondValueWithFive() {
        ListUtils.replaceIf(input, T -> T == 3, 5);
        assertThat(input).hasSize(2).containsSequence(1, 5);
    }

    @Test
    void whenReplaceSeveralElemenents() {
        List<Integer> original = new ArrayList<>(Arrays.asList(9, 3, 2, 1, 4, 1, 3, 6));
        ListUtils.removeAll(original, input);
        assertThat(original).hasSize(4).containsSequence(9, 2, 4, 6);
    }
}
