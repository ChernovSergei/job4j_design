package ru.job4j.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
    }

    @Test
    void whenPairWithSeverealEqual() {
        String path = "./data/pair_with_several_equal.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("password")).isEqualTo("dj4=sadl=dal=df");
    }

    @Test ()
    void whenNoData() {
        String path = "./data/pair_without_pair.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("password")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test ()
    void whenOnlyComments() {
        String path = "./data/pair_only_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("password")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test ()
    void whenOnlyKeys() {
        String path = "./data/pair_with_only_keys.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("password")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test ()
    void whenOnlyValues() {
        String path = "./data/pair_with_only_values.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("password")).isInstanceOf(IllegalArgumentException.class);
    }
}
