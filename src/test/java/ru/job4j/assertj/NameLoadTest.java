package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void doesntContainEqualMark() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"2=Rose", "3:Camomile", "1=Dahlia"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .hasMessageContaining("symbol \"=\"")
                .hasMessageContaining(names[1])
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void doesntContainAKey() {
         NameLoad nameLoad = new NameLoad();
        String[] names = {"=Rose", "3=Camomile", "1=Dahlia"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .hasMessageContaining("key")
                .hasMessageContaining(names[0])
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void doesntContainAValue() {
         NameLoad nameLoad = new NameLoad();
        String[] names = {"2=Rose", "3=Camomile", "1="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .hasMessageStartingWith("this")
                .hasMessageContaining(names[2])
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .endsWith("value")
                .isNotEmpty();
    }
}