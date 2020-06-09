import java.util.Arrays;

/*
188. Best Time to Buy and Sell Stock IV (hard level)
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/

Say you have an array for which the i-th element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.


Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7

Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.


Solution:
dp[i][j] = maximum profit from at most i transactions using prices[0..j]

A transaction is defined as one buy + sell.

Now on day j, we have two options

1. Do nothing (or buy) which doesn't change the acquired profit : dp[i][j] = dp[i][j-1]

2. Sell the stock;
we must find t:0->j-1 max(prices[j]-prices[t]+dp[i-1][t-1]) this expression in constant time. If you see carefully,

t:0->j-1 max(prices[j]-prices[t]+dp[i-1][t-1]) is same as

prices[j] + t:0->j-1 max(dp[i-1][t-1]-prices[t])

Second part of the above expression maxTemp = t:0->j-1 max(dp[i-1][t-1]-prices[t]) can be included in the dp loop by keeping track of the maximum value till j-1.

Base case:

dp[0][j] = 0; dp[i][0] = 0

DP loop:

for i : 1 -> k
    maxTemp = -prices[0];
    for j : 1 -> n-1
        dp[i][j] = max(dp[i][j-1], prices[j]+maxTemp);
        maxTemp = max(maxTemp, dp[i-1][j-1]-prices[j]);
return dp[k][n-1];



Time complexity: O(kn) where k is k, n is the number of prices
Space complexity: O(kn)
 */

public class BestTimeToBuySellStockIV_Ktransactions {
	
	//DP
	public static int maxProfit(int[] prices, int k) {
		int profit = 0; 
		
        if (k == 0 ) return 0;
        if ( prices == null || prices.length <= 1 ){
            return 0; 
        }
        
        // fast case because there are [0, n/2] continuous increases
        if ( k >= prices.length / 2 ) {  // for case k = 2 > len = 1.5  of arr = {2,4,1}
         
            for (int i = 1; i < prices.length; i++){
                if (prices[i] > prices[i-1] ){
                    profit += prices[i] - prices[i-1];
                }
            }
            return profit; //2
        }
        
        // Each element dp[i][j] means the max profit of at most i transactions until day j
        int[][] dp = new int[k + 1][prices.length];
      //  int localMax = Integer.MIN_VALUE;
        //
        for (int i = 1; i <= k; i++) {
        	int localMax = -prices[0]; //-3
        	
            for (int j = 1; j < prices.length; j++) {
            	
                localMax = Math.max(localMax, dp[i-1][j-1] - prices[j]); //to buy at prices[j], buy the stock if the cost is lower than the last.
                
                dp[i][j] = Math.max(dp[i][j-1], localMax + prices[j]); // to sell at prices[j], sell the stock if the profit is higher than the last..
            }
           
        }
        return dp[k][prices.length-1];
    }
          
	
	public static void main(String[] args) {

	//	int[] prices = {2,4,1};
	//	System.out.println("max profit2: " + maxProfit(prices, 2)); //2
		
		int[] prices2 = {3,2,6,5,0,3};
		System.out.println("max profit2: " + maxProfit(prices2, 2)); //7
         
   }
}