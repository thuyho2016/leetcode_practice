package string;
import java.util.Collections;
import java.util.HashMap;

/*
 * 159. Longest Substring with At Most Two Distinct Characters (hard level)
 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.

Sliding Window with two set pointers left and right serving as the window boundaries	
Basically that's the algorithm : to move sliding window along the string, to keep not more than 2 distinct characters in the window,
and to update max substring length at each step.

 Steps:
- Return N if the string length N is smaller than 3.
- Set both set pointers in the beginning of the string left = 0 and right = 0 and initialize max substring length max_len = 1.
- While right pointer is < less than N:
   If Map contains less than k distinct characters,
       add the current character s.charAt(right) to the map and move right pointer to the right.
       
   If Map contains k distinct characters, 
   		remove the leftmost character from the map and move the left pointer 
   so that sliding window contains again 2 distinct characters only.
   
   Update max_len.(max substring length at each step)
  
 Time complexity is O(N)
 
 */


public class LongestSubStringAtMost2DistinctCharacters{
	
	public static int lengthOfLongestSubstringTwoDistinct(String s) {
       int len = s.length();
       if (len < 3 ) return len;
       
       // sliding window left and right pointers
       int left = 0, right = 0;
       int max_len = 2; //initial length of 2 distinct characters
       
       // create a hashmap,  character as key, number of characters as value. E.g { e =1, c = 1}
       HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
       
       while (right < len) {
    	   // slide window contains less than 3 characters
    	   if(hm.size() < 3) {
    		   hm.put(s.charAt(right), right++);    		  
    	   }
    	   // slide window contains k + 1 = 2 + 1 = 3 characters . Window size = 3, so remove leftmost character
    	   if(hm.size() == 3) {
    		   
    		   // delete the leftmost character. We only keep 2 most characters
    		   int delete_indx = Collections.min( hm.values());
    		   hm.remove(s.charAt(delete_indx)) ; 
    		   
    		  // move left pointer of the slidewindow
    	      left = delete_indx + 1;
    	   }
    	   
           // update max_len ( longest substring)
           max_len = Math.max(max_len, right - left); // window size = end - start + 1
       }
       
       return max_len;
    }
	
	public static void main(String[] args) {
	   String s = "eceba";
	   System.out.println("Length: " + lengthOfLongestSubstringTwoDistinct(s)); //3
	
	   s = "ccaabbb";
	   System.out.println("Length: " + lengthOfLongestSubstringTwoDistinct(s)); //5
	}

}
	
	