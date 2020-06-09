

/*
123. Best Time to Buy and Sell Stock III ( hard level)
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time 
(i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
 
Time Complexity: O(N), where N is the number of prices.

Space Complexity: O(1)


 */

public class BestTimeToBuySellStockIII_2transactions {
	
	// sell the stock before you buy again
	//Goal: find the max profit at day n-1 with at most k transactions
	
	public static int maxProfit(int[] prices) {
		int buyOne = Integer.MAX_VALUE;
        int ProfitOne = 0;
        int buyTwo = Integer.MAX_VALUE;
        int ProfitTwo = 0;
        
        for(int p : prices) {
            buyOne = Math.min(buyOne, p);
            ProfitOne = Math.max(ProfitOne, p - buyOne);
           
            buyTwo = Math.min(buyTwo, p - ProfitOne);     // If I made $100 profit in 1st trans'. Then the 2nd Stock(maybe $300) I buy is actually $300 - $100 = $200 for me. 
            ProfitTwo = Math.max(ProfitTwo, p - buyTwo);   // And finally The maximum profit I make with the 2nd trans' is my actuall profit with 2 transaction. 
        }
        return ProfitTwo;
        
    }
	
	public static void main(String[] args) {
		int[] prices = {3,3,5,0,0,3,1,4};
		
		System.out.println("max profit1: " + maxProfit(prices)); //6		
        
        int[] prices2 = {1,2,3,4,5};
		System.out.println("max profit2: " + maxProfit(prices2)); //4
	
         
   }
}