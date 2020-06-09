package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
 * 472. Concatenated Words
 * https://leetcode.com/problems/concatenated-words/
 * 
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 */
public class WordsConcatenate_DP {

	/**
A concatenated word must be formed by 2 or more shorter words. We will iterate through each word 
and see if this is a concatenated word.

Let's try for a dynamic programming (DP) solution. Given the input

Words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
We are going to check, for example, if the word W = ratcatdogcat is concatenated.
Given a prefix of size K of the word W, dp[k] returns a boolean if this prefix can be formed by other words in the dictionary. We have:

dp[0] = True, (empy string) base case
dp[1] = False, prefix "r" is not formed by other words
dp[2] = False, prefix "ra" is not formed by other words
dp[3] = True, prefix "rat" exists in W (dictionary)
dp[4] = False, prefix "ratc" is not formed by other words
dp[5] = False, prefix "ratca" is not formed by other words
dp[6] = False, prefix "ratcat" Its formed by 2 words in W rat + cat
dp[7] = dp[8] =False,
dp[9] = True, prefix "ratcatdog" Its formed by 3 words in W rat + cat +dog
dp[10] = dp[11] =False,
dp[12] = True, prefix "ratcatdogcat" Its formed by 4 words in W rat + cat +dog + cat (It's allowed to repeat a word as cat).
A word is concatenated if dp[N] is true where N is the size of W.

Concatenated Words is the reverse process of Word Break I
*/
	public static List<String> findAllConcatenatedWordsInADict(String[] words) {
		Arrays.sort(words, (a,b) -> a.length() - b.length());
		
		List<String> result = new ArrayList<>();
	
		//list of shorter words 
		 List<String> preWords = new ArrayList<>();
		 
		 for(int i=0; i< words.length; i++){
			//call Word Break-I problem.
			 
			 if (wordBreak_DP(words[i], preWords) ) {
				 result.add(words[i]); //[dogcatsdog]; [dogcatsdog, catsdogcats];[dogcatsdog, catsdogcats, ratcatdogcat]
			 }
			 
			 preWords.add(words[i]); //[cat]; [cat, dog]; [cat, dog, rat];[cat, dog, rat, cats]; [cat, dog, rat, cats, dogcatsdog];[cat, dog, rat, cats, dogcatsdog, catsdogcats]
		 }
				
		 return result;
	}

	
	
	public static boolean wordBreak_DP(String s, List<String> wordDict) {
		//Set<String> wordDictSet = new HashSet<String>(wordDict);  //[code, leet]
        
		boolean[] isWordBreak = new boolean[s.length() + 1];
        
		isWordBreak[0] = true; // initialize the first element dp[0] as true. dp=[true, false, false, false, false, false, false, false, false]
        
		// for each character
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {            	
            	
                if (isWordBreak[j] ) {  // try right part first - use index j
                	String subSt = s.substring(j, i);   // l, le, lee, leet,..
                	System.out.println("Substring "+ subSt);  
                	
                	if( wordDict.contains(subSt)) {  //leet , code
                		System.out.println("Contains");
                		isWordBreak[i] = true;     // check left part - index i
	                    break;
                	}
                }
            }
        }
	    return isWordBreak[s.length()];  //dp[s.length()];
    }
	
	public static void main (String args[]) {
    
	    String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};  //Arrays.asList(
	    
	    List<String> out = findAllConcatenatedWordsInADict(words);
	    System.out.println("Concated " + out); //["catsdogcats","dogcatsdog","ratcatdogcat"]
	}
	
}
