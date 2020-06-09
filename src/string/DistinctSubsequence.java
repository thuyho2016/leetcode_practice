package string;

/*
 * 115. Distinct Subsequences
 * https://leetcode.com/problems/distinct-subsequences/
Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^

Example 2:

Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^

* This problem can be seen as a special match problem (10. Regular Expression Matching, 44. Wildcard Matching, 1023. Camelcase Matching) 
*/

public class DistinctSubsequence {
	
	/**
	 * Double array - 100% Top Down with Memoization

	 j == 0, then there must be one subsequence, i.e., empty string to match it.
	If s[i] == t[j], then sum[i][j] = sum[i - 1][j - 1] + sum[i - 1][j]
	Otherwise, sum[i][j] = sum[i - 1][j].
	  
	*/
	public static int numDistinct(String s, String t) {
		 int m = s.length(); 
		 int n = t.length();
		 int[][] dp = new int[m + 1][n + 1]; //[0, 0, 0, 0, 0, 0, 0],....
		 
		 for(int i = 0; i <= s.length(); i++)
			 dp[i][0] = 1;   //[[1, 0, 0, 0, 0, 0, 0],...
		
		 
		 for (int i = 0; i < m; i++) {
			 for (int j = 0; j < n; j++) {
				 System.out.println(dp[i + 1][j+1] + " and " + dp[i][j+1]);
				 dp[i + 1][j+1] = dp[i][j+1];
				 
				 if (s.charAt(i) == t.charAt(j)) {
					 dp[i+1][j+1] = dp[i+1][j+1] + dp[i][j]; //[[1, 0, 0, 0, 0, 0, 0], [1, 1, 0, 0, 0, 0, 0],
				 } 
			 }
		 }
		 
	     return dp[m][n]; //dp[7]=[1, 1, 1, 3, 3, 3, 3]
	 }
	
	public static void main(String[] args) 
	{ 
	    String s1 = "rabbbit"; 
	    String s2 = "rabbit"; 
	  
	    System.out.println("Distinct is " + numDistinct(s1, s2)); //3
	    
	    String s3 = "babgbag"; 
	    String s4 = "bag"; 
	    System.out.println("Distinct is " + numDistinct(s3, s4));
	  
	 } 
	  
}
