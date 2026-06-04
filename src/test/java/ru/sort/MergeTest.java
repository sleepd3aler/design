package ru.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MergeTest {
    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenAllDuplicatesThenSameResult() {
        int[] array = {10, 10, 10, 10, 10, 10, 10, 10};
        assertThat(Merge.mergesort(array)).containsExactly(10, 10, 10, 10, 10, 10, 10, 10);
    }

    @Test
    void whenOnlyLastElementIsUnsorted() {
        int[] array = {1, 2, 3, 4, 5, 6, 10, -10};
        assertThat(Merge.mergesort(array)).containsExactly(-10, 1, 2, 3, 4, 5, 6, 10);
    }

    @Test
    void whenFirstAndLastElemsAreUnsortedThenSuccess() {
        int[] array = {10, 1, 2, 3, 4, 5, 6, -10};
        assertThat(Merge.mergesort(array)).containsExactly(-10, 1, 2, 3, 4, 5, 6, 10);
    }

    @Test
    void whenUnsortedMiddleThenExpectedResult() {
        int[] array = {1, 2, 3, -10, 5, 6};
        assertThat(Merge.mergesort(array)).containsExactly(-10, 1, 2, 3, 5, 6);
    }

}