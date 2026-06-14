package ru.algo.greedy;

public class MaxProfit {
    public static int maxProfit(int[] prices) {
        // 2 1 2 0 1
        int maxProfit = 0;
        int startPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            startPrice = Math.min(startPrice, prices[i]);
            if (prices[i] > startPrice) {
                maxProfit += prices[i] - startPrice;
                startPrice = prices[i];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] {2, 1, 2, 0, 1}));
    }
}
