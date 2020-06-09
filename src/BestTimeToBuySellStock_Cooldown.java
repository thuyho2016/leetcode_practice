
/*
309. Best Time to Buy and Sell Stock with Cooldown

https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]


solution:
1.Each day we can have 3 operations : buy, sell or cooldown(do nothing)
2.We can only sell after buy
3.Must be a cooldown after sell


To solve this problem with DP, we can give 2 definition :
1.buy[i], means the max profit we can get if the status end with buy([buy,cooldown,cooldown] also means end with buy) at i-th day(i=0,1,2...)
2.sell[i], means the max profit we can get if the status end with sell([sell,cooldown,cooldown] also means end with sell) at i-th day(i=0,1,2...)
(Understand end with buy(sell) is important)


Obviously, buy[i] >= buy[i-1] >= ...... buy[1] >=buy[0] and sell[i] >= sell[i-1] >= ...... sell[1] >=sell[0]


To calculate buy[i]:
1.If we choose to buy at i-th day, then buy[i] = sell[i-2] - prices[i]. Because the i-1-th day must be cooldown, and we spend prices[i] to buy.
2.If we choose to cooldown at i-th day, then buy[i] = buy[i-1].Notice this also means end with buy.
So buy[i] = max(sell[i-2] - prices[i], buy[i-1])


To calculate sell[i]:
1.If we choose to sell at i-th day, then sell[i] = buy[i-1] + prices[i].
2.If we choose to cooldown at i-th day, then sell[i] = sell[i-1].
So sell[i] = max(buy[i-1]+prices[i], sell[i-1])

Finally, the max profit we can get is sell[len-1] (len = prices.size()). Because sell always after buy.
 */

public class BestTimeToBuySellStock_Cooldown {
	
	/**
	 * At each round we have 3 possible transactions: buy, noBuy, sell
	   We will track the best profit we could get for all three possibilities at the same time.

If i could buy immediately after selling, my best buying cost would be buy = Math.min(buy, prices[i] - Math.max(sell, noBuy));
However if wi sold last round, i can not buy this round because of cooldown, therefore buy = Math.min(buy, prices[i] - noBuy);

We can choose to noBuy this round regardless of whether we sold or not bought last round. 
so we can happily keep the best profit of both cases. noBuy = Math.max(noBuy, sell);

Similarly we can always sell, and when we do, buying low actually helps us profit more. sell = Math.max(sell, prices[i] - buy);

Time complexity : O(n), Space O(1)

 */
	
	public static int maxProfit(int[] prices) {
	
        if(prices.length == 0 || prices.length == 1){
            return 0;
        }
        
        int buy = -prices[0]; //-1
        int sell = 0;
        int cool = 0; //noBuy
        
        for(int i=1; i < prices.length; i++){
        	
            buy = Math.max(buy, cool - prices[i]);  // -1 or buy = Math.min(buy, p[i] - cool);
            
            cool = Math.max(cool, sell);   //noBuy
            
            sell = Math.max(sell, buy + prices[i]); //1
        }
        return sell;
    }
    
	public static int maxProfit2(int[] p) {
        
	    int buy=Integer.MAX_VALUE;
		int noBuy=0;
	    int sell=0;
	    
	    for (int i=0; i<p.length; i++) {
	        buy = Math.min(buy, p[i] - noBuy);
	        noBuy = Math.max(noBuy, sell);
	        sell = Math.max(sell, p[i] - buy);
	    }

	    return Math.max(sell,noBuy);
	}
	
	public static void main(String[] args) {
		int[] prices = {1,2,3,0,2};
		
		System.out.println("max profit: " + maxProfit(prices)); //3
		
		System.out.println("max profit 2: " + maxProfit2(prices)); //3
        
        
   }
}