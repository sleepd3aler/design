package ru.algo.greedy;

public class MaxProfitII {
    public static int maxProfit(int[] prices, int fee) {
        int cash = 0;
        int hold = -prices[0];
        for (int price : prices) {
            int prevCash = cash;
            int prevHold = hold;

            cash = Math.max(prevCash, prevHold + price - fee);
            hold = Math.max(prevHold, prevCash - price);
        }
        return cash;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        //3 - 1 - 2 = 0
        //
    }

}
