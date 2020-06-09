package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * 438. Find All Anagrams in a String
https://leetcode.com/problems/find-all-anagrams-in-a-string/
 
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

 
Time complexity : O(N_s + N_p)O
s
​	
 +N 
p
​	
 ) s
Space complexity : O(1)

 */

public class Anagram_FindAllInString {
	
	public static List<Integer> findAnagrams(String s, String t) {
		List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result;
        
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1); //map={a=1, b=1, c=1}
        }
        
        int counter = map.size();  //3
        
        int begin = 0, end = 0; //slow, fast
        
        while(end < s.length()){
            char c = s.charAt(end);  //c, b
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1); //update value , so map={a=1, b=1, c=0}, map={a=1, b=0, c=0}
                if(map.get(c) == 0) 
                	counter--; //reduce counter --, 2,1,0
            }
            end++;  //move fast pointer
            
            while(counter == 0){
                char tempc = s.charAt(begin);
                
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == t.length()){
                    result.add(begin);
                }
                begin++;
            }
            
        }
        return result;
  	}
	
	public static List<Integer> findAnagrams2(String s, String p) {
        int[] pChars = new int[26];
        List<Integer> pIndices = new ArrayList<>();
        
        // Initialize with String p frequencies.
        for (char c: p.toCharArray())
        {
            pChars[c - 'a']++;
        }
        
        int start = 0, end = 0;
        int pLen = p.length(); // Number of matches needed.

        while(end < s.length())
        {
           if (pChars[s.charAt(end++) - 'a']-- >= 1) 
                pLen--; // If current char is a match.
           
            if (pLen == 0 && (end - start) == p.length())
                pIndices.add(start); // Anagram Matched. 
            
            if (end - start == p.length()) 
            {
            	if (pChars[s.charAt(start)-'a']++ >= 0) {
            		pLen++; // Reset the count and increment the matches needed.
            	}
            	start++;
            }
        }
        return pIndices;
    }
	  
	public static void main(String[] args) {
		String s = "cbaebabacd";
		String p = "abc";
		System.out.println(findAnagrams(s,p));
		
	}
}
