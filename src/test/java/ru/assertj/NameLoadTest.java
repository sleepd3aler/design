package ru.assertj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    private NameLoad nameLoad;

    @BeforeEach
    void setUp() {
        nameLoad = new NameLoad();
    }

    @Test
    void whenParseWithEmptyStringThenExceptionIsThrown() {
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void whenParseWithInvalidSymbolThenExceptionIsThrown() {
        String key = "key";
        String value = "value";
        assertThatThrownBy(() -> nameLoad.parse(key, value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(key)
                .hasMessageMatching(".*=.*");
    }

    @Test
    void whenParseWithInvalidNameThenExceptionIsThrown() {
        String key = "=key";
        String value = "value";
        assertThatThrownBy(() -> nameLoad.parse(key, value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(key, "does not contain")
                .hasMessageMatching(".*=.*");
    }

    @Test
    void whenParseWithInvalidValueThenExceptionIsThrown() {
        String key = "key=";
        assertThatThrownBy(() -> nameLoad.parse(key))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("value")
                .message()
                .isNotEmpty()
                .isEqualTo("this name: %s does not contain a value".formatted(key));
    }

    @Test
    void whenNameLoaderIsEmptyThenExceptionIsThrown() {
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .message()
                .isNotEmpty()
                .contains("contains no data");

    }

}