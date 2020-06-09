package string;

import java.util.HashMap;
import java.util.HashSet;

/*
 * 819. Most Common Word  (easy level)
 * https://leetcode.com/problems/most-common-word/
 * 
Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  
It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive. 
The answer is in lowercase.


Example:
Input: 

paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]

Output: "ball"

Explanation: 
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 

"hit" occurs 3 times, but it is a banned word. So, "hit" isn't the answer even though it occurs more because it is banned.

 */


public class MostCommonWord {
	
	// Create a HashSet to store all words from banned array 
	// Check every word in paragrap which is not in banned, put it in HashMap and increment count for same word
	// check key of HashMap to see which word occurs the most
	 
	// ^[a-zA-Z] means any a-z or A-Z at the start of a line because ^ ouside[...]
    // [^a-zA-Z] means any character that IS NOT a-z OR A-Z. (It matches any character not appearing inside the [])

	
	public static String mostCommonWord(String paragraph, String[] banned) {
        
		HashSet<String> bannedWords = new HashSet<>();
        for(String word: banned) {
        	bannedWords.add(word);
        }
        
        HashMap<String, Integer> map = new HashMap<>();
        
        //replace non character , . to empty space , convert uppercase to lower case, split " " to get each word individually
        for(String word: paragraph.replaceAll("[^a-zA-Z]"," ").toLowerCase().split(" ")  ) {   //
        	
        	//Need to check if word is not in bannedWords [hit]. It means count only for words are not in banned array.
        	if (!bannedWords.contains(word)) {   
        	 	System.out.println("Word: " + word);
        		map.put(word, map.getOrDefault(word, 0) + 1);  // add word to HashMap {a=1, bob=1}
         	 	
        		//int count = map.getOrDefault(word, 0); //put 1 first time it has seen, put 1
        		//map.put(word, count + 1);              // if word already exist, increase count by 1
        												       
        	}
        }
        
        //map = {=1, the=1, a=1, ball=2, bob=1, far=1, flew=1, was=1, after=1, it=1}
        String result = "";
        for (String key : map.keySet()) { // For every key in HashMap, I check the value, compare with result and update result
        	
        	if(result.equals("") || map.get(key) > map.get(result)) {  //ap.get(key) to get value  ball=2
        		result = key;    // update result to store new key
        	}
        }
        return result;
    } 
	
	public static void main(String[] args) {
		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = {"hit"};
		
		System.out.println("Common word: " + mostCommonWord(paragraph, banned));
	}
}
	
