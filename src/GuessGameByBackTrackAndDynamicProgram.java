/*
 * 375. Guess Number Higher or Lower II

We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21
 
*/

public class GuessGameByBackTrackAndDynamicProgram {

   public static void main(String[] args) {

	   System.out.println(getMoneyAmount(10)); //16  
	   System.out.println(getMoneyAmount2(10)); 
   }
   
   //backtrack ( recursive)
   // Base case F(low, high) = 0 if low >= high
   
   // Let F(low, high) be the min cost to win starting with range low ... high
   // F(low, high) = min(k + max(F(low, k-1) + F(k+1, high))), for each k from low  to high
 
   
   public static int getMoneyAmount(int n) {
       return calculate(1, n);
   }
   
   public static int calculate(int low, int high) {
       if (low >= high)
           return 0;
       
       int min = Integer.MAX_VALUE;
       
       //start from mid to high
       for (int i = (low + high) / 2; i <= high; i++) {
    	   
           int max = i + Math.max(calculate(low, i - 1), calculate(i + 1, high)); //recursive
           min = Math.min(max, min);
       }
       return min;
   }
   
   //Dynamic Programming
   //Time: O(n^3); for every range (continuous subarrs) n^2, we traverse starting choices n;
   //Space: O(n^2)
   public static int getMoneyAmount2(int n) {
       int[][] dp = new int[n + 1][n + 1];
       
		/* when end index increase */
       for (int end = 1; end <= n; end++) {
			/* for every possible start index, update dp[start][end] */
    	   
           for (int start = end - 1; start >= 1; start--) {
               int maxCost = -1, minMax = Integer.MAX_VALUE;
               
               for (int k = start + 1; k < end; k++) {
                   maxCost = k + Math.max(dp[start][k - 1], dp[k + 1][end]);  
                   minMax = Math.min(maxCost, minMax);  // smart part
               }
               dp[start][end] = start == end - 1 ? start : minMax;
           }
       }
       return dp[1][n];
   }
   
}