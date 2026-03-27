package ru.tdd.template;

import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GeneratorTest {
    Generator generator;

    @Test
    void whenTemplateDoesntContainsKeyThenExceptionThrown() {
        String temp = "I am ${name}, My age is ${}! ";
        Map<String, String> map = Map.of("name", "Alex", "age", "32");
        assertThatThrownBy(() -> generator.produce(temp, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTemplateContainsIllegalKeysAmountThenExceptionThrown() {
        String temp = "I am ${name}, My age is ${age}, ${I will make ur app crash}! ";
        Map<String, String> map = Map.of("name", "Alex", "age", "32");
        assertThatThrownBy(() -> generator.produce(temp, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTemplateContainsDuplicateKeysThenExceptionThrown() {
        String temp = "I am ${name}, My age is ${name}! ";
        Map<String, String> map = Map.of("name", "Alex", "age", "32");
        assertThatThrownBy(() -> generator.produce(temp, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTemplateIsEmptyThenExceptionThrown() {
        String temp = "I am ${}, My age is ${}! ";
        Map<String, String> map = Map.of("name", "Alex", "age", "32");
        assertThatThrownBy(() -> generator.produce(temp, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMapIsEmptyThenExceptionThrown() {
        String temp = "I am ${name}, My age is ${age}! ";
        Map<String, String> map = Map.of();
        assertThatThrownBy(() -> generator.produce(temp, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTemplateIsCorrectThatExceptedResult() {
        String temp = "I am ${name}, My age is ${age}! ";
        Map<String, String> map = Map.of("name", "Alex", "age", "32");
        String expected = "I am Alex, My age is 32!";
        String res = generator.produce(temp, map);
        assertThat(res).isEqualTo(expected);
    }

    @Test
    void whenTemplateContains$InKeyThenExceptionThrown() {
        String temp = "I am ${$name}, My age is ${age}! ";
        Map<String, String> map = Map.of("name", "Alex", "age", "32");
        assertThatThrownBy(() -> generator.produce(temp, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMapContainsSameValueAsTheKeyThenExceptionThrown() {
        String temp = "I am ${name}, My age is ${age}! ";
        Map<String, String> map = Map.of("name", "name", "age", "age");
        assertThatThrownBy(() -> generator.produce(temp, map))
                .isInstanceOf(IllegalArgumentException.class);
    }
}