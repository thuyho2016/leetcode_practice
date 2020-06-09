package string;

/*
 * 647. Palindromic Substrings
 * https://leetcode.com/problems/palindromic-substrings/
 * 
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Solution:
Let N be the length of the string. The middle of the palindrome could be in one of 2N - 1 positions: either at letter or between two letters.

For each center, let's count all the palindromes that have this cente

Time complexity O(N^2)  - N is length
Space: O(1)

*/

public class PalindromicSubstring_Count  {
	
	public static int countSubstrings(String S) {
		
        int len = S.length(), ans = 0;
        
        for (int center = 0; center <= 2*len -1; ++center) {
            int left = center / 2;
            int right = left + center % 2;
            
            while (left >= 0 && right < len && S.charAt(left) == S.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }
	
	public static void main (String[] args)
    {
		String s1 = "aaa";
		System.out.println("Count substrings: " + countSubstrings(s1)); //6
	
    }
	
}