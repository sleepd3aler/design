package ru.algo.LeetCode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.algo.LeetCode.TwoSum.twoSum;

class TwoSumTest {

    @Test
    void whenFirstTwoNumsAreEqualsTargetThenExpectedRes() {
        int[] nums = {2, 7, 11, 15};
        int[] expected = {0, 1};
        assertThat(twoSum(nums, 9)).isEqualTo(expected);
    }

    @Test
    void whenLastTwoNumsAreEqualsTargetThenExpectedRes() {
        int[] nums = {3, 2, 4};
        int[] expected = {1, 2};
        assertThat(twoSum(nums, 6)).isEqualTo(expected);
    }

    @Test
    void whenNumsContainsOnly2NumsAndTheyEqualsTargetThenExpectedRes() {
        int[] nums = {3, 3};
        int[] expected = {0, 1};
        assertThat(TwoSum.twoSum(nums, 6)).isEqualTo(expected);
    }

    @Test
    void whenTargetIs3andSecondIs1andFourthIs2ThenExpectedResult() {
        int[] nums = {3, 1, 5, 2, 5};
        int[] expected = {1, 3};
        assertThat(twoSum(nums, 3)).isEqualTo(expected);
    }

    @Test
    void whenTargetIs6andFirst1Second5ThenExpectedResult() {
        int[] nums = {1, 2, 3, 7, 5};
        int[] expected = {0, 4};
        assertThat(twoSum(nums, 6)).isEqualTo(expected);
    }

    @Test
    void whenTargetIs3First2Second1ThenExpectedResult() {
        int[] nums = {3, 2, 5, 1, 5};
        int[] expected = {1, 3};
        assertThat(twoSum(nums, 3)).isEqualTo(expected);
    }

    @Test
    void whenTargetNotFoundThenResultIsNull() {
        int[] nums = {4, 2, 5, 1, 3};
        assertThat(twoSum(nums, 2)).isEqualTo(null);
    }

}