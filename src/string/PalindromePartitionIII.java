package string;

import java.util.HashMap;

/*
 * 1278. Palindrome Partitioning III

https://leetcode.com/problems/palindrome-partitioning-iii/
 
You are given a string s containing lowercase letters and an integer k. You need to :

First, change some characters of s to other lowercase English letters.
Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
Return the minimal number of characters that you need to change to divide the string.
 

Example 1:

Input: s = "abc", k = 2
Output: 1
Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.

Example 2:

Input: s = "aabbc", k = 3
Output: 0
Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.

Example 3:

Input: s = "leetcode", k = 8
Output: 0

 */ 

public class PalindromePartitionIII  {
	//DP
	HashMap<String, Integer> map = new HashMap<>();
    
    public int palindromePartition(String s, int k) {
        String key = s +","+k;
        
        if(map.containsKey(key)) return map.get(key);
        if(k == 1) {
            int c = 0;
            for(int i = 0; i < s.length() / 2; i++) {
                if(s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                    c++;
                }
            }
            return c;
        }
        
        int output = Integer.MAX_VALUE;
        
        for(int kk = 1; kk < k; kk++) {
        	
            for(int i = 1; i <= s.length() - (k - kk); i++) {
                String sub1 = s.substring(0, i);
                String sub2 = s.substring(i);
                
                if(i >= kk) {
                    int left = palindromePartition(sub1, kk);
                    int right = palindromePartition(sub2, k - kk);
                    output = Math.min(output, left + right);
                } else {
                    break;
                }
            }
        }
        map.put(key, output);
        return output;
    }
	
	
	public static void main (String[] args)
    {
		PalindromePartitionIII p = new PalindromePartitionIII();
		String s = "abc";  

		System.out.println(p.palindromePartition(s, 2)); //1
		
	    String s2 = "aabbc";
		System.out.println(p.palindromePartition(s, 3)); //0
		
		
    }
	
}