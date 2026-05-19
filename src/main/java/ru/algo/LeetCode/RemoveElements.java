package ru.algo.LeetCode;

public class RemoveElements {
    public static int removeElement(int[] nums, int value) {
        //0,1,2,2,3,0,4,2], val = 2
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != value) {
                nums[start] = nums[i];
                start++;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int res = removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2);
        System.out.println(res);
    }
}
