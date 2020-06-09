package string;

/*
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 * Given a string s, find the longest palindromic substring in s.
 * 

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Input: "cbbd"
Output: "bb"

Approach : Dynamic Programming
time complexity and Space complexity is O(N^2)

It is just exclude the condition dp[i + 1][j - 1] when i and j are within 2 index distance. 
In short,
if i == j, dp[i][j] = s.charAt(i) == s.charAt(j)
i + 1 == j, dp[i][j] = s.charAt(i) == s.charAt(j)
i + 2 ==j , dp[i][j] = s.charAt(i) == s.charAt(j) and since the middle one doesn't matter.

Only when i + 3 == j, dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];


Example: abbagood --> abba is longest palindromic
 
		
Solution DP: 
String is palindrome if it meet 2 conditions

1. First Letter and Last letter should be the same:  s.charAt(i) == s.charAt(j)
2. Inner word (substring) is also palindromic

	boolean[][] isPalindrome;
	
	isPalindrome[i][j] ?
	
	isPalindrome[i + 1][j - 1] should true ?
	
	or check if one single character between index i and index j ?
	
	case 1: single character --> difference i and j is 1 index
	   a b a 
	   i 1 j
	   
	case 2: two characters  --> difference i and j is 2 indexes
	   a b b a 
	   i 1 2 j	   
	
	  so condition to single character between index i and j is j - i <= 2  


Last step: Get longest palindromic substring:  s.substring(i, j + 1)

Time & Space complexity O(n^2) (n square)

*/

public class Palindromic_LongestSubstring_DP  {
				
	// BEST SOLUTION - j index start from first character --> output : bab		
	public static String longestPalindrome(String s) {
		int len = s.length();
	    
		if(s == null || len < 2) return s; 
	        
        boolean[][] isPalindrome= new boolean[len][len]; //[[false, false, false, false, false],...
        
        int left = 0;
        int right = 0;
        
        for(int j = 1; j < len; j++){ 
            for(int i = 0; i < j ; i++ ){ 
            	//boolean isInnerPalindrome = j - i <= 2 || isPalindrome[i + 1][j-1]; 
            	boolean isInnerPalindrome = isPalindrome[i + 1][j-1] || j - i <= 2; //true || one single character
            	
            	if (s.charAt(i) == s.charAt(j) && isInnerPalindrome) {
            		isPalindrome[i][j] = true;  //[[false, false, true, false, false],...
            		
	                if(j - i > right - left ){ // j - i is greater than maxLen = right - left + 1, update left, right
	                    left = i;    //update left = 0 , left = i = 1
	                    right = j;   // right = 2, right = j = 3	                   
	                }
            	}
            }
        }
        
        // case abc: left = right = 0 
        return s.substring(left, right + 1);  // left side is inclusive , right side needs to + 1 to get right index
	}
	
	
	// use maxLen - start from last character --> output : aba
	public static String longestPalindrome2(String s) {
		int len = s.length();
		
		if(s == null || len < 2) return s; 
		
		boolean[][] isPalindrome = new boolean[len][len]; //[[false, false, false, false, false],....
		
		String result = ""; 
		int maxLen = 0;    //to keep longest length   
		    
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = i; j < s.length(); j++) {
				
				 // j - i < 2 || isPalindrome[i + 1][j - 1] ; // check substring palindrome is true || one single character
		
				if ( s.charAt(i) == s.charAt(j) && ( j - i < 2 || isPalindrome[i + 1][j - 1])) {
					isPalindrome[i][j] = true;  //[[false, false, true, false, false],...
					
		        	if ( j - i + 1 > maxLen) { // update maxLen
		        		maxLen = j - i + 1;
						result = s.substring(i, j + 1); // left side is inclusive , right side needs to + 1 to get right index
						
					}
		        }
				
		    }
		}  
		return result;
	}
	
	
	public static void main (String[] args)
    {
	 
		String s1 = "babad";
		System.out.println("Longest palindrome: " + longestPalindrome(s1)); //"bab"
		System.out.println("Longest palindrome: " + longestPalindrome2(s1)); //"aba
		
		String s2 = "abc";
		System.out.println("Longest palindrome: " + longestPalindrome(s2)); //"a"
		System.out.println("Longest palindrome: " + longestPalindrome2(s2)); //"c"
		
    }
	
}