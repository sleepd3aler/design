package ru.algo.greedy;

public class BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        //[7,1,5,3,6,4]
        int maxProfit = 0;
        int startPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            startPrice = Math.min(prices[i], startPrice);
            int currentProfit = prices[i] - startPrice;
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
