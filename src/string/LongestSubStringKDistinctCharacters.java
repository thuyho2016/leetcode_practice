package string;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
340. Longest Substring with At Most K Distinct Characters (Hard level)
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

 Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2 
Output: 3
Explanation: T is "ece" which its length is 3.

Example 2:
Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.


Sliding Window with two set pointers left and right serving as the window boundaries	

Basically that's the algorithm : to move sliding window along the string, to keep not more than 2 distinct characters in the window,
and to update max substring length at each step.
 
Apply for https://leetcode.com/articles/longest-substring-with-at-most-two-distinct-charac/
LongestSubStringAtMost2DistinctCharacter.java
 
 Steps:
- Return N if the string length N is smaller than 3.
- Set both set pointers in the beginning of the string left = 0 and right = 0 and initialize max substring length max = 1.
- While right pointer is < less than N:
   If Map contains less than k distinct characters,
        add the current character s.charAt(right) to the map and move right pointer to the right.
       
   If Map contains k distinct characters, 
   		remove the leftmost character from the map and move the left pointer 
        so that sliding window contains again 2 distinct characters only.
   
   Update max (max substring length at each step)
  

Time complexity : O(N) in the best case of k distinct characters in the string 
and O(Nk) in the worst case of N distinct characters in the string.

Space complexity : O(k) since additional space is used only for a hashmap with at most k + 1 elements.
 */

public class LongestSubStringKDistinctCharacters{
	
	/* Approach: sliding Window + HashMap - store rightmost as value
	Keep track of the number of occurrences of each character in [head, tail) using a HashMap

	If which is k + 1, slide forward until it decreases back to k.
	**/
	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		int len = s.length();
		if ( s == null || len == 0) return 0;
		if (len * k == 0) return 0;

       int left = 0, right = 0;
       int max = 0;  // int max = Integer.MIN_VALUE; 
         
       // hashmap character -> its rightmost position 
       // in the sliding window
       HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

       int maxLen = 1;

       while (right < len) {  // when right = 5 > len = 4, then dont go while loop 
         // add new character and move right pointer
         hashmap.put(s.charAt(right), right++);

         // slide window contains 3 characters - map = {b=3, c=1, e=2} , {a=4, b=3, e=2}
         if (hashmap.size() > k) {    //right = 4
           // delete the leftmost character
           int del_idx = Collections.min(hashmap.values()); //1
           hashmap.remove(s.charAt(del_idx));  // after remove c has value 1, map = {b=3, e=2} , {a=4, b=3}
           // move left pointer of the slidewindow
           left++;//1; // 1,2,3
         }

         maxLen = Math.max(maxLen, right - left); // max(3, 4 -2) = max(3,2) = 3
       }
       return maxLen;
        
    }
	
	//use array
	public static int lengthOfLongestSubstringKDistinct2(String s, int k) {
        int[] arry = new int[128];
        
        int i = 0, len = s.length(), distinctNum = 0,res = 0;
        
        for (int j = 0;j < len; j++) {
            char c = s.charAt(j);
            if ( arry[c] == 0){
            	distinctNum++;
            }
            arry[c]++;
            
            while (distinctNum > k) {
                char d = s.charAt(i);
                arry[d]--;
                
                if (arry[d] == 0){
                	distinctNum--;
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
        
    }
 	
	public static void main(String[] args) {
		String s = "eceba";
		System.out.println("Length: " + lengthOfLongestSubstringKDistinct(s, 2)); //3
		System.out.println("Length: " + lengthOfLongestSubstringKDistinct2(s, 2)); //3
		
	    String s1 = "a";
		System.out.println("Length2: " + lengthOfLongestSubstringKDistinct(s1, 2)); //1
		System.out.println("Length2: " + lengthOfLongestSubstringKDistinct2(s1, 2)); //1
		
	   String s2 = "aabbcabc";
	   System.out.println("Length3: " + lengthOfLongestSubstringKDistinct(s2, 3)); //8
	   System.out.println("Length3: " + lengthOfLongestSubstringKDistinct2(s2, 3)); //8
	   
	   String s3 = "abaccc";
	   System.out.println("Length3: " + lengthOfLongestSubstringKDistinct(s3, 2)); //4
	   System.out.println("Length3: " + lengthOfLongestSubstringKDistinct2(s3, 2)); //4
	
	}

}
	
	