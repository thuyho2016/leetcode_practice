package string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * 139. Word Break
 * https://leetcode.com/problems/word-break/
 * 
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true

Explanation: Return true because "leetcode" can be segmented as leet and code

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false



Problem 2:  Similar to WordBreak

Given a non-empty string s and map containing non-empty string as keys and count as values.
Determine if s can be segmented into a sequence of one or more words.

s = "abcd", map = {"abc":1, "ab":1, "cd":1} return true because s can be broken into "ab" and "cd"

s = "aaab", map = {"a":2, "b":2} return false as not enough a


For the worst case, for example, we take s = "abcd" and wordDict = ["a", "b", "c", "bc", "ab", "abc"]
Time complexity is O(2^n)
 
 */

public class WordBreakValidation_DP_BFS {
	
	/** Best Solution: Using Dynamic Programming (Top down)
		
	 isWordBreak[8] = true 
	 isWordBreadk[4] = true 
	 s.substring(4,8) in wordDict
	
	Index j    i
	   leet/code     j point to t   , i go from 1 to s.length
	 Make general rule:  isWordBreal[j] = true && wordDict.contains(s.substring(j,i)
	 
	 1. create boolean arry has the same length with given string
	 2. use 2 pointers i and j 
	 3. for each character i and j
	    if isWordBread[j] is not false, continue : if (!isWordBreak[j]) continue;
	      
	 3. partition the current string s into smaller substring(0,0)= l and (1, 0) = le,...
	    if wordDic contains substring (j,i), set isWordBreak = true
	
	//Time complexity : O(n^2) - two for loops
    //Space complexity : O(n)
	*/
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
                		isWordBreak[i] = true;     // check left part - index i
	                    break;
                	}
                }
            }
        }
	    return isWordBreak[s.length()];  //dp[s.length()];
    }
    
    /**Using Breadth-First-Search: Create Queue -> while queue is not empty -> 
     * remove head element to exam -> satisfy condition -> add new index to queue
	             cat
	 - check if substring contains in HashSet. If it does, add the substring to Queue.            
	 - when length of string has the same length of substrings, return true
	 
	  //s.substring = leet, if condition meets, then add end index to queue
        //s.substring = code,  end = 4
        //s.substring = leetcode, end = 6
         * 
         * 
	 Time complexity : O(n^2)
	 space complexity : O(n). Queue of atmost nn size is needed.
	*/
    public static boolean wordBreak_BFS(String s, List<String> wordDict) {  //s = "leetcode",
    	// No need to put all words into a hashset
      //  Set<String> wordDictSet = new HashSet<>(wordDict); //[code, leet]
        
        Queue<Integer> q = new LinkedList<Integer>(); // store length of substring which match with word from Dict 
        q.add(0);   //q=[0]
        
        int[] visited = new int[s.length()];   //[0, 0, 0, 0, 0, 0, 0, 0]
        
       
        while (!q.isEmpty()) {
        	int start = q.remove();  //start index = 0, 1,...4
        	
        	if (visited[start] == 0) {
        		for ( int end = start + 1; end <= s.length(); end++) {  //end = 1
        			String subSt = s.substring(start, end);
        			System.out.println("Substring "+  subSt);  // l, le, lee, 
        			if (wordDict.contains(subSt)) { //s.substring = leet, if condition meets, add end index to queue
        				System.out.println("found");   //leet , code
        				q.add(end); // end = 4 when substring = leet , end = 8 when substring = 8
        			
        			    if ( end == s.length()) { // when end = 8, then return true
        			    	return true;
        			    }
        			}
        		}
        		visited[start] = 1;  //[1, 0, 0, 0, 0, 0, 0, 0]
        	}
        }
        return false;
    }
   
	  public static void main(String[] args) {
	    List<String> words = Arrays.asList("leet", "code");
	    String s = "leetcode";
	  //  boolean actual = wordBreak_DP(s, words); // true
	  //  System.out.println("By DP "+ actual);
	    
	    boolean actual1 = wordBreak_BFS(s, words); // true
	    System.out.println("By BFS " + actual1);
	    
	/*    List<String> words2 = Arrays.asList("cats", "dog", "sand", "and", "cat");
	    String s2 = "catsandog";
	    boolean actual2 = wordBreak_DP(s2, words2); // false
	    System.out.println(actual2);
	    
	    
	    //s = "abcd", map = {"abc":1, "ab":1, "cd":1}
	    List<String> words3 = Arrays.asList("abc", "ab", "cd");
	    String s3 = "abcd";
	    System.out.println(wordBreak_BFS(s3, words3)); // true
	*/ 	   
	  }
}
	