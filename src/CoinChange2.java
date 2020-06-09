import java.util.Arrays;
import java.util.List;

/*
 * 518. Coin Change 2
 * https://leetcode.com/problems/coin-change-2/

You are given coins of different denominations and a total amount of money. 
Write a function to compute the number of combinations that make up that amount.
You may assume that you have infinite number of each kind of coin.

 
Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4

Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1


Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:

Input: amount = 10, coins = [10] 
Output: 1

Solution:

		0  1  2  3  4  5   ( amount)
[1]     1  1  1  1  1  1    # unique ways of = 1
[1,2]   1  1  2  2  3  3   If coins = 4, # of unique way = 3 because 4 -2 coins = 2 coins that has 2 ways  +  1 above of without using 2 coins. Similarly, if coins = 4, # of way =3 
[1,2,5] 1  1  2  2  3  4   If coins = 5, # of unique way = 4 because 5 -5 coins = 0 that has 1 way  +  3 above of without using 5 coins    
 
 Time complexity: O(N×amount), where N is a length of coins array.

Space complexity: O(amount) to keep dp array.
 */

public class CoinChange2 {
	
	 //Prefer this solution
	 public static int change(int amount, int[] coins) {
	
		int[] dp = new int[amount + 1]; //size = amount + 1 = 6
		dp[0] = 1;  //[1, 0, 0, 0, 0, 0]
		
		for (int coin : coins) {  //[1, 2, 5]
			for (int i = coin; i <= amount; i++) { // i starts from 1 to 5 
				dp[i] += dp[i - coin];  //coin = 1, i = 1, dp = [1, 1, 0, 0, 0, 0];[1, 1, 1, 0, 0, 0]; [1, 1, 1, 1, 0, 0];[1, 1, 1, 1, 1, 0];[1, 1, 1, 1, 1, 1]
			    //coin = 2, i =coin = 2, dp =[1, 1, 2, 1, 1, 1]; i =3, dp = [1, 1, 2, 2, 1, 1]; i = 4, dp = [1, 1, 2, 2, 3, 1]; i = 5, dp = [1, 1, 2, 2, 3, 3]
				//coin = 5, i =coin = 5, dp = [1, 1, 2, 2, 3, 4]
			}
		}
		return dp[amount];
	 } 

	 public static int change2(int amount, int[] coins) {
			
			int[] dp = new int[amount + 1]; //size of array = amount + 1 = 6
			dp[amount] = 1;  //0, 0, 0, 0, 0, 1]
			
			for (int coin : coins) {  //[1, 2, 5]
				for (int i = amount; i >= 0; i--) { // i starts from 5 to 0
					int j = i + coin; //5 + 1 = 6, 4 + 1 = 5, 3 + 1 = 4
					if ( j <= amount) // coin = 25 is too big, don't go inside if, coin = 1, 5,10  < amount 
						dp[i] += dp[j];  //[0, 0, 0, 0, 1, 1], [0, 0, 0, 1, 1, 1], [0, 0, 1, 1, 1, 1], [0, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1]
				}
			}
			return dp[0]; //number of combinations starting with 0, [4, 3, 2, 2, 1, 1]
		 } 
	
	public static void main(String[] args) {
	/*	int[] coins = {1, 2,5};
		int amount = 5;
		System.out.println(change(amount, coins)); //4
	*/	
		int[] coins2 = {1,5,10,25};
		int amount = 10;
		System.out.println(change2(amount, coins2)); //4
		
	}
}
