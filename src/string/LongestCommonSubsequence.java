package string;

/*
 * 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 * 
 * Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order 
of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

If there is no common subsequence, return 0.

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.

Similar, Longest common substring
https://www.youtube.com/watch?v=BXCEFAzhxGY
https://leetcode.com/problems/maximum-length-of-repeated-subarray/discuss/109039/Concise-Java-DP%3A-Same-idea-of-Longest-Common-Substring

*/

public class LongestCommonSubsequence {

	/** 
	  TopDown
	  This is what the DP table looks like filled out for the 2 strings
	  
	  "AGGTAB" and "GXTXAYB".
	     ""  A  G  G  T  A  B
	  ""  0  0  0  0  0  0  0
	  G   0  0  1  1  1  1  1
	  X   0  0  1  1  1  1  1
	  T   0  0  1  1  2  2  2
	  X   0  0  1  1  2  2  2
	  A   0  1  1  1  2  3  3
	  Y   0  1  1  1  2  3  3
	  B   0  1  1  1  2  3  4
	  
	  https://github.com/bephrem1/backtobackswe/blob/master/Dynamic%20Programming%2C%20Recursion%2C%20%26%20Backtracking/longestCommonSubsequence.java

   "AAB" and "AZB".
	     ""  A  Z  B  
	  ""  0  0  0  0 
	  A   0  1  1  1 
	  A   0  2  1  1 
	  B   0  2  1  2
*/
	//singe array
	public static int longestCommonSubsequence(String word1, String word2) {
      int m = word1.length();
      int n = word2.length();
      
      int[] dp = new int[n + 1];   // single array = [0, 0, 0, 0]
      
      for (int i = 1; i <= m; i++) {
          int prev = 0;
          for (int j = 1; j <= n; j++) {
        	  
              int temp = dp[j];
              
              if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                  dp[j] = prev + 1;  //dp[1]=[0, 1, 0, 0]
              } else {
                  dp[j] = Math.max(dp[j], dp[j - 1]); //dp[2]=[0, 1, 1, 0], dp[3]= [0, 1, 1, 1]
              }
              prev = temp;
          }
      }
      return dp[n]; //[0, 1, 1, 2] -->dp[3] = 2
  }
	
	//Double array - 100% Top Down with Memoization
	public static int longestCommonSubsequence2(String text1, String text2) {
		 int m = text1.length(); 
		 int n = text2.length();
		 
		 int[][] dp = new int[m + 1][n + 1];
		 
		 for (int i = 0; i < m; i++) {
			 for (int j = 0; j < n; j++) {
				 if (text1.charAt(i) == text2.charAt(j)) {
					 dp[i+1][j+1] = dp[i][j] + 1;
				 } else {
					 dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j + 1]);	
				 }
			 }
		 }
		 
	     return dp[m][n];
	 }
	
	public static void main(String[] args) 
	{ 
	    LongestCommonSubsequence lcs = new LongestCommonSubsequence(); 	   
	    
	    String s1 = "aab"; 
	    String s2 = "azb"; 
	  
	    System.out.println("Length of LCS is " + longestCommonSubsequence(s1, s2));//2
/*	    System.out.println("Length of LCS is " + longestCommonSubsequence2(s1, s2));
	    
	    String s5 = "abcde"; 
	    String s6 = "ace"; 
	  
	    System.out.println("Length of LCS is " + longestCommonSubsequence(s5, s6));
	    System.out.println("Length of LCS is " + longestCommonSubsequence2(s5, s6)); //3
	    
	    String s7 = "abc"; 
	    String s8 = "def";
	    System.out.println("Length of LCS is " + longestCommonSubsequence(s7, s8));
	    System.out.println("Length of LCS is " + longestCommonSubsequence2(s7, s8)); //0
	    
	    String s3 = "AGGTAB"; 
	    String s4 = "GXTXAYB"; 
	  
	    System.out.println("Length of LCS is " + longestCommonSubsequence(s3, s4)); //4
	    System.out.println("Length of LCS is " + longestCommonSubsequence2(s3, s4));
	  
	    String s9 = "pmjghexybyrgzczy";
	    String s10 = "hafcdqbgncrcbihkd"; 
	    System.out.println("Length of LCS is " + longestCommonSubsequence(s9, s10));
	    System.out.println("Length of LCS is " + longestCommonSubsequence2(s9, s10));
*/	 } 
	  
}
