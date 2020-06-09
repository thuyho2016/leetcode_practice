
/*
121. Best Time to Buy and Sell Stock ( Easy level)
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 
You have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock).
Design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

***** one transaction ****

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

Time complexity : O(n^2) 
Space complexity : O(1). Only two variables(compare and maxProfit) are used 



 122. Best Time to Buy and Sell Stock II
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(i.e., buy one and sell one share of the stock multiple times).  

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.

https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems

 Time complexity : O(n). Only a single pass is needed.

Space complexity : O(1). Only two variables are used.
	  
 */

public class BestTimeToBuySellStockI_II {
	//Best Time to Buy and Sell Stock I
	
	/** Sliding window - Better solution
	  j = 0 , i =  1 -> len = 1
	    
	  Because I need to buy low and sell high Sell,  if prices[j] > prices[i] , then shift j to i. Eg 7 > 1 
	
	  I need to find max(prices[j] - prices[i]) for every i and j
	
	  Time complexity : O(n). Only a single pass is needed.

	  Space complexity : O(1). Only two variables are used.
	  
	*/
	
	// current element should greater than previous element, 2 pointers j = 0, i = 1 - 
	public static int maxProfit(int[] prices) {
		
		if (prices == null || prices.length == 0)
            return 0;
		
		int maxProfit = 0; 
		int j = 0;
		
		for (int i = 1; i < prices.length; i ++) { // [7,1,5,3,6,4]
			//compare current number and previous number
			int profit = prices[i] - prices[j]; //1-7 = -6, 5 - 7 = -2, 
			
			if (profit > maxProfit) { // 5 -1 = 4 > 1, 6 - 1 = 5 > 4
				maxProfit = profit;    //update maxProfit = 4
				System.out.println("find max Profit? " + maxProfit); 
			}
			if( prices[j] > prices[i]) // previous element > current element ( e.g  7 > 1), then shift j pointer to i  
				j = i;   // j = i = 1
		}

		return maxProfit;
        
    }
	
	 
	/**
	 * Best Time to Buy and Sell Stock II
	 * 
	 * Time complexity : O(n). Single pass.
	 * Space complexity: O(1). Constant space needed.
	 * 
	 * Add up profit
	 */
	 public static int maxProfit2(int[] prices) {
		 if (prices == null || prices.length == 0)
	            return 0;
		 
	    int maxprofit = 0;
	    
        for (int i = 1; i < prices.length; i++) { // start from 1
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1]; //buy this day and sell in next day 
        }
        return maxprofit;
     }
	 
	
	public static void main(String[] args) {
		int[] arr = new int[] {};
		System.out.println("max profit1: " + maxProfit(arr));
		
		int[] prices = {7,1,5,3,6,4};
		
		System.out.println("max profit1: " + maxProfit(prices)); //5
		
        int[] prices2 = {7,6,4,3,1};
		System.out.println("max profit1: " + maxProfit(prices2)); //0
		
        
        //Problem 2
        System.out.println("max profit2: " + maxProfit2(prices)); //7 
        
        int[] prices3 = {1,2,3,4,5};
        System.out.println("max profit2: " + maxProfit2(prices3)); //4
            
   }
}