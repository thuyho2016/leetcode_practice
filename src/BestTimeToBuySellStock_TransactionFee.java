/*
714. Best Time to Buy and Sell Stock with Transaction Fee ( hard level)
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 
Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:

Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8

Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8

Buying at prices[4] = 4
Selling at prices[5] = 9

The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
   
analysis:

buy[i]: To make a decision whether to buy at i, we either take a rest, by just using the old decision at i - 1, or sell at/before i - 2, then buy at i.
sell[i]: To make a decision whether to sell at i, we either take a rest, by just using the old decision at i - 1, or buy at/before i - 1, then sell at i.



Dynamic Programming:
2 Variables:
cash:  the maximum profit we could have if we did not have a share of stock, and 
hold: the maximum profit we could have if we owned a share of stock.


To transition from the i-th day to the i+1-th day, we either sell our stock cash = max(cash, hold + prices[i] - fee) or buy a stock hold = max(hold, cash - prices[i]). 
At the end, we want to return cash

If I am holding a share after today, then either I am just continuing holding the share I had yesterday, or that I held no share yesterday, but bought in one share today: 
  hold = max(hold, cash - prices[i])

If I am not holding a share after today, then either I did not hold a share yesterday, or that I held a share yesterday but I decided to sell it out today: 
  cash = max(cash, hold + prices[i] - fee).
  
  

Time Complexity: O(N), where N is the number of prices.

Space Complexity: O(1), the space used by cash and hold.
                    
 */

public class BestTimeToBuySellStock_TransactionFee {
	
	// sell the stock before you buy again
	//Goal: find the max profit at day n-1 with at most k transactions
	public static int maxProfit(int[] prices, int fee) {
		if(prices.length == 0 || prices == null)
	            return 0;
		 
		int cash = 0, hold = -prices[0];
		
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);  // buy a stock
        }
        return cash;
        
    }
	
	public static void main(String[] args) {
		int[] prices = {1, 3, 2, 8, 4, 9};
		
		System.out.println("max profit1: " + maxProfit(prices, 2)); //8	
       
         
   }
}