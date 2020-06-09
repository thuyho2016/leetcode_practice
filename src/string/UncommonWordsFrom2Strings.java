package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/*
884. Uncommon Words from Two Sentences
https://leetcode.com/problems/uncommon-words-from-two-sentences/
  
We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Return a list of all uncommon words. You may return the list in any order.

 

Example 1:

Input: A = "this apple is sweet", B = "this apple is sour"
Output: ["sweet","sour"]
Example 2:

Input: A = "apple apple", B = "banana"
Output: ["banana"]

map.getOrDefault(key defaultValue)

Solution1: Ideal is to find unique word from 2 strings. Ignore duplicate words
- combine two strings into one. 
- create a hash map which has key is word and count how many times words is seen.
- if word count = 1, add this word to List --> That means add unique word to List.

NOTE: Similar FindUniqueNumber.java
*/

public class UncommonWordsFrom2Strings {
	
	//Return a list of all uncommon words
	public static String[] uncommonFromSentences(String A, String B) {
		List<String> result = new ArrayList<String>();
		
		String combinedStr = A + " " + B;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//count words
        for (String word: combinedStr.split(" ")) {
        	map.put(word, map.getOrDefault(word,0) + 1);  //{apple=1, this=1}
        	// add word to hashmap and count 1. If word is seen repeatly, increase count {apple=2}
        }
		
   /*     for (Map.Entry<String,Integer> entry: map.entrySet()) {  //{apple=2, this=2, is=2, sweet=1, sour=1}
        	if (entry.getValue() == 1) {   //if word count = 1, add this word to List
        		result.add(entry.getKey());
        	}
        }
    */    
        map.forEach( (key,value) -> { if ( value == 1) {
        								result.add(key);
                                      } 
        }); 
        
        //convert List to array
        return result.toArray(new String[result.size()]);
	        
    }
	
	
	public static void main (String[] agrs) {	
		
		String A = "this apple is sweet";
		String B = "this apple is sour";
		
		String[] res = uncommonFromSentences(A, B);
		System.out.println("Uncommon words: " + Arrays.deepToString(res));
		
		
		String A1 = "apple apple";
		String B1 = "banana";
				
		res = uncommonFromSentences(A1, B1);
		System.out.println("Uncommon words: " + Arrays.deepToString(res));
	}
}
