package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> testSet = simpleConvert.toSet("First", "Second", "Third", "Fourth", "Fifth", "First");
        assertThat(testSet).hasSize(5)
                .contains("First", "Third")
                .containsAnyOf("Million", "Fourth", "Ten")
                .endsWith("Fifth")
                .anyMatch(s -> s == "Second")
                .first().isEqualTo("Second");
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("A", "B", "C", "D", "E", "F");
        assertThat(list).hasSize(6)
                .containsSequence("A", "B", "C")
                .doesNotContain("Z", "y")
                .startsWith("A")
                .allSatisfy(e -> {
                    assertThat(e).isNotEmpty();
                    assertThat(e).isNotNull();
                });
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("Coke", "Fanta", "Sprite");
        assertThat(map).hasSize(3)
                .containsKey("Fanta")
                .doesNotContainKey("Merinda")
                .containsValue(1)
                .containsEntry("Coke", 0);
    }
}