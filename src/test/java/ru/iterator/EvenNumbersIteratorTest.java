package ru.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EvenNumbersIteratorTest {
    private Iterator<Integer> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new EvenNumbersIterator(new int[]{1, -3, 2, 3, 5, 5, -4, 5, 6, 7});
    }

    @Test
    void shouldReturnEvenNumbersSequentially() {
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(-4);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(6);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(-4);
        assertThat(iterator.next()).isEqualTo(6);
    }

    @Test
    void shouldReturnFalseIfNoAnyEvenNumbers() {
        iterator = new EvenNumbersIterator(new int[]{1});
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void shouldReturnFalseIfNoAnyNumbers() {
        iterator = new EvenNumbersIterator(new int[]{});
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void allNumbersAreEven() {
        iterator = new EvenNumbersIterator(new int[]{2, 4, 6, 8});
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(6);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(8);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void allNumbersAreOdd() {
        iterator = new EvenNumbersIterator(new int[]{1, 3, 5, 7});
        assertThat(iterator.hasNext()).isFalse();
        assertThat(iterator.hasNext()).isFalse();
    }
}
