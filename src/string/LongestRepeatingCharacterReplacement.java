package string;
/*
424. Longest Repeating Character Replacement
https://leetcode.com/problems/longest-repeating-character-replacement/

Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. 
Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Example 1:

Input:
s = "ABAB", k = 2

Output: 4


Explanation:
Replace the two 'A's with two 'B's or vice versa-> AAAA, BBBB 

Example 2:

Input:
s = "AABABBA", k = 1

Output: 4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 
** Steps: Sliding Window
 
left: left index thats in window
right: right index thats in window

We start with a left and right pointer that will be used to define our window. 
The window is all the letters between the right and left pointers.

First we increment the right pointer, and add new letter to our "window". We add this letter to the counter. 
Next, we want to find which letter has the highest frequency in the window. 
returns most frequent letter back as [ (letter, freq) ].

right - left + 1 is the size of our current window. 
max_count is the count of the most frequent letter in the window. 

Think of the right - left + 1 as the "total possible edits" and max_count as "edits already done".
 So to find the remaining edits, we do "total possible edits" - "edits already done".
 
 In if condition, we increment our left pointer, and take that letter out of the counter, because it is no longer inside our window. We shrink our window until remaining edits is equal to k.
*/

public class LongestRepeatingCharacterReplacement{
			
	//best solution - 2 pointers
	public static int characterReplacement(String s, int k) {
	    int left = 0, maxCount = 0;
	    int maxLength = 0;
	    
	    int[] counts = new int[26];
	    
	    for (int right = 0; right< s.length(); right++) {
	    	counts[s.charAt(right) - 'A']++;  //[1, 0, 0,...], [1, 1, 0, ...], [2, 1, 0,..]
	    	System.out.println(" Substract: " + counts[s.charAt(right) - 'A']);
	        maxCount = Math.max(maxCount, counts[s.charAt(right) - 'A']); //max(0,1) = 1
	        
	     // if max character frequency + distance between start and end is greater than k
            // this means we have considered changing more than k charactres. So time to shrink window
	        
	        if ( right - left + 1 > maxCount + k) { //  > 4
	            counts[s.charAt(left) - 'A']--;  //reduce count by 1
	            left++;    // move left pointer
	        }
	        maxLength = Math.max(maxLength, right - left + 1);
	    }

	    return maxLength;
	}
	
	//Easy to understand
	public static int characterReplacement2(String s, int k) {
	    char[] map = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	    int length = 0;
	   
	    for(int i = 0; i < 26; i++){
	        int count = 0;
	        char c = map[i]; //i = 0, c = A; i = 1, c = B, i = 2, c = C
	        int left = 0;
	        
	        for(int right = 0; right < s.length(); right++){// We try every letter
	            if(s.charAt(right) != c){ // A != A, B != A, then count++ = 1
	                count++;
	            }
	            while(count > k){  // keeps a valid window always count = 3 > 2
	                if(s.charAt(left) != c){ // A !=C, reduce count--
	                    count--;
	                }
	                left++;  // shift left to 1
	            }
	            length = Math.max(length, right - left + 1); //window size = 1
	        }
	    }
	    return length;
	}

	

	 
	public static void main(String[] args) {
	   String s = "ABAB";
	 //  System.out.println("Length: " + characterReplacement(s, 2));
	   System.out.println("Length: " + characterReplacement2(s, 2)); //4
	  
	   
	   String s2 = "AABABBA";
	   System.out.println("Length: " + characterReplacement(s2, 1));
	 //  System.out.println("Length: " + characterReplacement2(s2, 1));
	}

}
	
	