package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* Amazon | OA 2019 | Substrings of size K with K distinct chars
Given a string s and an int k, return all unique substrings of s of size k with k distinct characters.

Example 1:

Input: s = "abcabc", k = 3
Output: ["abc", "bca", "cab"]
Example 2:

Input: s = "abacab", k = 3
Output: ["bac", "cab"]
Example 3:

Input: s = "awaglknagawunagwkwagl", k = 4
Output: ["wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag"]

Explanation: 
Substrings in order are: "wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag", "wagl" 
"wagl" is repeated twice, but is included in the output once.


Solution: 
Similar with https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

The idea is to maintain a window and keep a count of every character in the window. 
Whenever the size of the window is equaled to k, add it to the set.

 */

public class SubstringSizeK_withKdistinctChars {
	
	//return a list of unique substring with size k  - find distinct character of size K 
	public static List<String> substringKdistinctChars(String s, int k) {
		Set<Character> window = new HashSet<>();
		
		ArrayList<String> result = new ArrayList<String>(); // List<String> result = new ArrayList<String>();
		
		 int start = 0; 
	     int end = 0; 
	        
		while (end < s.length()) { // or  for(int end = 0; end <s.length(); end++) {
			
			while (window.contains(s.charAt(end)) ){  // if hashset contains char, remove  remove at leftmost
				window.remove(s.charAt(start));
				start++;
			}
			window.add(s.charAt(end)); //If hashSet doesn't contains character, add new character and move right pointer to the right
			
			if(window.size() == k) { // if(end - start + 1 == k) {
				//String tmp = s.substring(start, end + 1);
				result.add(s.substring(start, end + 1));
				
				window.remove(s.charAt(start));
				start++;
			}
			
			end++;
		}
		
		return result; // convert HashSet to ArrayList  new ArrayList<>(result);
	}
	
	public static void main(String[] args) {
		String s = "abcabc";
		System.out.println("Substring with K distinct character: " + substringKdistinctChars(s, 3)); //[bca, abc, cab]
		
		//size = 3
		System.out.println("Substring with K distinct character: " + substringKdistinctChars("abacab", 3));
		
	}
}
