package ru.assertj;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("first", Index.atIndex(0))
                .contains("second")
                .doesNotContain("six", "Hello World")
                .filteredOn(e -> e.length() == 6)
                .first().isEqualTo("second");
    }

    @Test
    void checkToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> test = simpleConvert.toSet("first", "first", "three", "four", "five", "five");
        assertThat(test).hasSize(4)
                .contains("first")
                .doesNotContainSequence("first", "three", "four", "five");
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> test = simpleConvert.toMap(
                "first", "first", "second", "three", "four", "five", "five"
        );
        assertThat(test).hasSize(5)
                .containsEntry("first", 0)
                .containsEntry("second", 2)
                .containsEntry("three", 3)
                .doesNotContainValue(1);

    }
}