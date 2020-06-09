package string;

import java.util.HashMap;
import java.util.Map;

/*
 * 76. Minimum Window Substring  (hard )
 https://leetcode.com/problems/minimum-window-substring/
 
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"


Solution:

1. Use two pointers: start and end to represent a window.
2. Move end to find a valid window.
3. When a valid window is found, move start to find a smaller window.
To check if a window is valid, we use a map to store (char, count) for chars in t. And use counter for the number of chars of t to be found in s. 
The key part is m[s[end]]--;. 
We decrease count for each char in s. If it does not exist in t, the count will be negative.


Find minium window of substring:
- Use Two Pointers & Tracking Character Mappings With A Hashtable
- When the window is not satisfy, expand the window by shifting right pointer and keep searching it

azjskzts  , T= "sz"
L
R->> 
L   <---- when to move L  (shrink window) when window is desirable 
  ^
  R  <--- move R when window doesn't contain all character in T


- When substring is "azjs". It contain T. I already know it satisfy, 
- Next, Go back to the Left pointer and shift it to the right. Then see if the window still satisfy.
( there is no benefit to move Right pointer because It will keep adding more character and make the window is longer)

- After I move Left pointer to the right, I find new possible answer "azjs"
Then, move Left pointer again, substring is "js", but no z. 

Now, I need to make a choice to move forward Right pointer until the window is satisfy . 
Substring "skfz", but it is long. Because I want to shorten, I have to move Left pointer.
After I shift Left pointer to "k", substring "kfz" again miss character s. 
so, I have to shift Right point until I have s,  substring = "kfzts"

Now, the substring satisfy, but it is longer than the "zjs". So I don't want to take it.
So , shift Left pointer to make the substring shortness. 
When Left points to z, the window "zts" satisfy and has the same length of the "zjs"
Now, I am good. I find both has the minimum length.


To do:
1. Create a HashMap to store characters of target string t ="sz" - count as value 
2. 2 pointers and tracking char mappings with a hashmap

expand window to satisfy sz by moving R pointer forward.

When window is satisfy, then care about minimum window. There is no benefit to move R pointer because I already know window is satisfy.
so I should move L pointer forward.


Time Complexity : O(|S| + |T|) where |S| and |T| represent the lengths of strings S and T. - O(n)
Space Complexity : O(|S| + |T|). 

*/
public class MinimumSlidingWindow {
	
	/**
	 *  First count how many times each char appears in target string t.
		Then using a sliding window in string s
			1. When we move the right side of the sliding window and we see a char c appearing in t 
			(and when char c has not appeared more times than it in t), 
			   we increment a counter.
			2. When that counter equals t.length(), we got a sliding window that contains all chars in t.
				- Now we need to shrink the sliding windows from left side. This is because left side may have included unnecessary chars. 
				   For example, when s = "XAAXAAAB" and t = "AB",
				- Once left side is shrinked, we update the sliding window's min length if needed.
		
		 The sliding window check ends when the right side reaches the end of the string (and the left side is shrinked)
	 */
	public static String minWindow(String s, String t) {
		if(s == null || s.length() < t.length() || s.length() == 0){
	        return "";
	    }
		
		Map<Character, Integer> map = new HashMap<>(); //to store character count of string T
	    for(char c : t.toCharArray()) {
        	// int val = map.getOrDefault(c, 0);
        	// map.put( c, val + 1);
        	 map.put(c, map.getOrDefault(c, 0) + 1); // increase value if same character
        }
		
	     //pointer left
        int left = 0;
        
        int minLeft = 0;
        int minLen = s.length()+1;
        int count = 0;
        
        // 2nd pointers right
        for(int right = 0; right < s.length(); right++){
        	
            if(map.containsKey(s.charAt(right))){
                map.put(s.charAt(right),map.get(s.charAt(right))-1);
                if(map.get(s.charAt(right)) >= 0){
                    count ++;
                }
                
                while(count == t.length()){
                	
                	  //checking if  current window length is smaller
                    if(right-left+1 < minLen){
                        minLeft = left;
                        minLen = right-left+1;
                    }
                    
                    if(map.containsKey(s.charAt(left))){ // character exist in T, so decrement count
                        map.put(s.charAt(left),map.get(s.charAt(left))+1); 
                        if(map.get(s.charAt(left)) > 0){
                            count--;
                        }
                    }
                    left ++ ;  // shift left pointer
                }
            }
        }
        
        if(minLen>s.length())  
        {  
            return "";  
        } 
        return s.substring(minLeft,minLeft+minLen);
	}
	
	

	  public static String minWindow2(String s, String t) {
	        
		    if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";
		  
	        int[] map = new int[256];
	        for (char c : t.toCharArray()) {
	            map[c]++;
	        }
	        
	        int count = 0;
	        int left = 0;
	        
	        int start = 0;
	        int end = 0;
	        int minLen = Integer.MAX_VALUE;
	        
	        for (int right = 0; right < s.length(); right++) {
	        	
	            if (map[s.charAt(right)]-- > 0) {
	                count++;
	            }
	            
	            while(count == t.length()) { // must use while
	            	
	            	  //checking if  current window length is smaller
	                if (minLen > right - left + 1) {
	                    minLen = right - left + 1;
	                    start = left;
	                    end = right;
	                }
	                
	                if (++map[s.charAt(left++)] > 0) {
	                    count--;
	                }
	            }
	        }
	        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end + 1); 
	    }

	  
	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
	    String t = "ABC";
	    String out = minWindow2(s, t);
	    System.out.println(out);	//BANC
	    
		String s2 = "azjskzts";
		String t2 = "sz";
		System.out.println( minWindow2(s2, t2));	//zjs
	    
	}
}