package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 140. Word Break II
 * https://leetcode.com/problems/word-break-ii/
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]

Output:
[]


Solution of DP:

String S = "catsanddog" , one of its substring = "catsand"  (T)
Suppose I know T can be broken into ['cat', 'sand'] or ['cats', 'and']
and S = T + "dog"

We only need to check if "dog" is in the wordDict.

dp(S) = dp(T) + "dog" 
      =  ["cat", "sand", dog"],
    or   ["cats", "and", dog"] 

Let di(i) denote all valid breads for S(0,i)

In this case, S(0,6) = 'catsand'
dp[6] = ['cat', 'sand'],
        ['cats', 'and']
        
      
        Index : 0	1	2	3	4	5	6	7	8	9
            S:  c	a	t	s	a	n	d	d	o	g	
                							i
                                                j
      dp[0] = []
      dp[1] = []
      dp[2] = ["cat"]
      dp[3] = ["cats"]
      dp[4] = []
      dp[5] = []
      dp[6] = ["cat sand", "cats and"]
      dp[7] = []
	  dp[8] = []
	  dp[9] = ["cat sand dog","cats and dog"]

>> recurrence relation:
dp[i] = 
for (int j = 0; j < i; j++) {
	substr = s[j, i];
  if (dp[j].size() > 0 && wordDict.contains(substr)) {
      for (String l : dp[j]) {
          dp[i].add(l + substr);
      }
  }
}
            

 */
public class WordBreakII_DFS {
	
	//DFS - recursive JAVA with memoization - use a HashMap to store previously computed results.
	// Call DFS on every substring
	//Time Complexity: O(n^3).
	
		 
    public static List<String> wordBreak(String s, List<String> wordDict) {
      //  Set<String> words = new HashSet<>(wordDict);
       
        Map<Integer, List<String>> result = new HashMap<>();
        return helper(s, 0, wordDict, result);
    }
    
    //use index, Map contains index as Key, values is a list of String - { 7=[dog]},  {3=[sand dog], 7=[dog]}
    private static List<String> helper(String s, int index, List<String> wordDict, Map<Integer, List<String>> map) {

        if ( map.containsKey(index)) return  map.get(index);;  
        
        List<String> result = new ArrayList<>();
    	if(s == null || s.length() == 0) {
	         return result;
    	}
        
        for (int i= index; i < s.length(); i++) {   // i = 10, s.length() = 10, exit for log
            String subStr = s.substring(index, i+1); //index = 0, i =0: s.substring(0,1) = c
            
            if (wordDict.contains(subStr)) { // subStr = cat, cats, sand, and, or dog if condition meets
            	
                if (i == s.length() - 1) { // i = 9: i goes to the end of string, s.length() = 10 - 1 = 9, then add current subStr to result
                    result.add(subStr);
                    
                } else {
                	
                    List<String> sub = helper(s, i + 1, wordDict, map); // increment i++
                    for (String w : sub) {
                        result.add(subStr + " " + w); //{7=[dog]}
                    }
                }                
            }
        }
        
        map.put(index, result); //{3=[sand dog], 7=[dog]} , {3=[sand dog], 4=[and dog], 7=[dog]}, {0=[cat sand dog, cats and dog], 3=[sand dog], 4=[and dog], 7=[dog]}
        return result;  //[cat sand dog, cats and dog]
        
    }
		
    // Masp constain String as Key
    public static List<String> wordBreak_DFS(String s, List<String> wordDict) {
        //  Set<String> words = new HashSet<>(wordDict);
         
          Map<String, List<String>> result = new HashMap<>();
          return dfs(s, wordDict, result);
      }
      
      private static List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> map) {
      	
      	if(map.containsKey(s)) {
            return map.get(s);
      	}
      	
      	List<String> result = new ArrayList<>();
      	if(s == null || s.length() == 0) {
  	         return result;
      	}
      	 
      	for(String word : wordDict){
      		if(s.startsWith(word)){
              
      			String nextWord = s.substring(word.length());  // word = dog, s.substring(3) = 0
              	
                  if (nextWord.length() == 0) {
                      result.add(word); //result =[dog]
                      
                  } else {
                      List<String> subStr = dfs(nextWord, wordDict, map); 
                      for (String w : subStr) {
                          result.add(word + " " + w); 
                      }
                  }                
              }
          }
          
          map.put(s, result); //{sanddog=[sand dog], dog=[dog]}; {anddog=[and dog], sanddog=[sand dog], dog=[dog]}
          return result;  //[cat sand dog, cats and dog]
          
      }
      
	
	 public static void main(String[] args) {
	    List<String> words = Arrays.asList("cat", "cats", "and", "sand", "dog");
	    String s = "catsanddog";
	  /*  Output:
	    	[
	    	  "cats and dog",
	    	  "cat sand dog"
	    	]
	    */
	    System.out.println(wordBreak(s, words));
	    
	   // List<String> output = wordBreak_DFS(s, words); // true
	   // System.out.println("By DFS " + output);
	    
	    //  List<String> ans = wordBreak_DP(s, words); 
		 //   System.out.println("By DP "+ ans);
	 }
}
