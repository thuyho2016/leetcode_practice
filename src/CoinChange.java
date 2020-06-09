import java.util.Arrays;
import java.util.List;

/*
 * 322. Coin Change
 * https://leetcode.com/problems/coin-change/
 * 
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins
 that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

 
Time complexity : O(S*n). On each step the algorithm finds the next F(i)in n iterations, where 1 ≤ i ≤ S. 
Therefore in total the iterations are S*n.
Space complexity : O(S)O(S)

      Total
       0    1     2     3     4     5
Coin   
0      1way  0    0     0     0     0  way
1      1     0+1  0+1   0+1   0+1   0+1 way
2      1     1    1+1   1+1   1+2   1+2
3      1     1    2     2+1	  3+1   3+2   ( same row, go to the first col)
4      1     1    1     1     4+1   5+1
5      1	 1	  1     1     5     6+1
  
coins { 0, 1, 2} = 3  
coins {0,1,2} to make total = 5
     --> 2 + 3 = 5, to make total of 3, you need to use coins {0,1,2}
     
       
  Rule: 
  1. r > c, then just copy the value from above cell
  2. if r < c: copy the value above cell + (the value above cell - value of coins). 
               In the same row, go to the first col, get value
  
 
 Rule 2:  
 1. Exclude the new coin
 2. Include the new coin
 3. Add the number of ways 1 + 2 = new coin
 
   
   for(int i = 0; i <= coin; ++i) {
       for(int j = 0; j < total; ++j) {
           if (i ==0 && j==0)
              dp[i][j] = 1;  // base 1 way
           else {
              if ( i > j) {
                 m[i][j] = m[i-1][j]; // Rule 1. copy the above value 
              else       // row > col
                 m[i][j] = m[i-1][j] + m[i][j - i];    
                 //Rule 2. exclude the new coin + include the coin (substract total - coins and get value from the first col).
           }   
       }
 
 */

public class CoinChange {
	
	//Dynamic programming - Bottom up 
	// Iterate over all the amounts to build up the DP solution from small values up to amount.
	public static int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0)
			return -1;

		if (amount <= 0)
			return 0;
		
		int max = amount + 1;             
        int[] dp = new int[amount + 1];  
        Arrays.sort(coins);
        Arrays.fill(dp, max);  
        dp[0] = 0;    //[0, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12]
        
        for (int i = 1; i <= amount; i++) { // i starts from 0 to 11
            for (int j = 0; j < coins.length; j++) {  //j starts 0, 1, 2 as coins has length = 3, coins = [1, 2, 5]
            	
            	int diff = i - coins[j];  //1 - 1 = 0; 1 - 2 = -1; 1 - 5 = -4
                if (diff >= 0 ) {  // current amount is greater than coins, then min.... . Else break
                    dp[i] = Math.min(dp[i],  dp[diff] + 1 ); //[0, 1, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12]
                }
            }
        }
        
        //amount = 11, dp=[0, 1, 1, 2, 2, 1, 2, 2, 3, 3, 2, 3]
        return dp[amount] > amount ? -1 : dp[amount];
	}
	
     // O(n *m) n is amount of 
	 public static int coinChange2( int[] coins, int amount) {
		 if (coins == null || coins.length == 0)
				return -1;

			if (amount <= 0)
				return 0;
			
			int[] dp = new int[amount + 1]; //size = amount + 1 = 6
			Arrays.fill(dp, amount + 1);
			dp[0] = 0;  //[0, 0, 0, 0, 0, 0]
			
		    for (int i = 0; i <= amount; i++) { // i starts from 0 to 11
	            for (int j = 0; j < coins.length; j++) {  //j starts 0, 1, 2 as coins has length = 3, coins = [1, 2, 5]
			       if (coins[j] <= i) {  // if coin is too big than amount, don't go if 
			    	   dp[i] = Math.min(dp[i],  1 + dp[i - coins[j]]);
			       }
	            
	            }
			}
		    
		    return dp[amount] > amount ? -1 : dp[amount];
			
	 }
	
	public static void main(String[] args) {
		int[] coins = {1, 2,5};
		int amount = 11;
		System.out.println(coinChange(coins, amount));
		
		int[] coins2 = {2};
		int amount2 = 3;
		System.out.println(coinChange(coins2, amount2));
		
	}
}
