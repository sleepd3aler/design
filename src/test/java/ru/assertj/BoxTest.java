package ru.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void whenVertexIs4ThenExpectedResult() {
        Box box = new Box(4, 3);
        String expected = "Tetrahedron";
        String result = box.whatsThis();
        assertThat(result).isEqualTo(expected)
                .isNotEmpty()
                .isPrintable()
                .contains("ron")
                .startsWith("Tet");
    }

    @Test
    void whenVertexIs5ThenDefaultResult() {
        Box box = new Box(5, 3);
        String result = box.whatsThis();
        assertThat(result).isNotEqualTo("Tetrahedron")
                .isEqualTo("Unknown object")
                .isNotEmpty();
    }

    @Test
    void whenEdgeIs4ThenExpectedResult() {
        Box box = new Box(4, 3);
        int expected = 4;
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(expected)
                .isGreaterThan(1)
                .isBetween(3, 5)
                .isEven();
    }

    @Test
    void whenEdgeIs0ThenResultIsNegative() {
        Box box = new Box(4, 0);
        int expected = -1;
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(expected)
                .isLessThan(0)
                .isNegative();
    }

    @Test
    void whenEdgeIs1ThenResultExist() {
        Box box = new Box(4, 1);
        boolean expected = true;
        boolean result = box.isExist();
        assertThat(result).isEqualTo(expected)
                .isTrue();
    }

    @Test
    void whenEdgeIs0ThenResultNotExist() {
        Box box = new Box(4, 0);
        boolean expected = false;
        boolean result = box.isExist();
        assertThat(result).isEqualTo(expected)
                .isFalse();
    }

    @Test
    void whenEdgeIs1ThenExpectedResult() {
        Box box = new Box(4, 1);
        double expected = 1.74;
        double result = box.getArea();
        assertThat(result).isEqualTo(expected, withPrecision(0.1d))
                .isCloseTo(2, withPrecision(0.27d))
                .isGreaterThan(0)
                .isBetween(1.0, 1.8)
                .isNotZero();
    }

    @Test
    void whenVertexIs5ThenAreaIsZero() {
        Box box = new Box(5, 5);
        double expected = 0;
        double result = box.getArea();
        assertThat(result).isEqualTo(expected)
                .isZero();
    }
}