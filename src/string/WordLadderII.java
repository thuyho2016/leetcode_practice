package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * 126. Word Ladder II (hard level)

Given two words (beginWord and endWord), and a dictionary's word list, 
find all shortest transformation sequence(s) from beginWord to endWord, such that:

- Only one letter can be changed at a time
- Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

Note:
Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
 
 
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]

Input 2:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

To do:

1). Use BFS to find the shortest distance between start and end, tracing the distance of crossing nodes from start node to end node,
 and store node's next level neighbors to HashMap;

2). Use DFS to output paths with the same distance as the shortest distance from distance HashMap: 
compare if the distance of the next level node equals the distance of the current node + 1.   
*/

/*Steps:
	1. Create a HashSet to store words of wordList
	2. Add beginWord to Queue
	3. Remove a word from queue and  check a new word by change each character from a ...to z
	4. Set first char from a , like [a, i, t] .
	5. if tmpStr is the same endWorks, increase count by 1
*/

public class WordLadderII {
	
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		//HashSet<String> words = new HashSet<>(wordList);
		HashSet<String> words = new HashSet<>();  //1. Create a HashSet to store words of wordList
        for (String word : wordList) {            //add words to HashSet
        	words.add(word);
        }
	        
		List<List<String>> result = new LinkedList<>();
		Queue<List<String>> queue = new LinkedList<>();
		queue.offer(Arrays.asList(beginWord));  //2. Add beginWord to Queue
		
		words.remove(beginWord);   //3. Remove a word from queue and  

        while (!queue.isEmpty()) {
        	int n = queue.size(); // must have , otherwise program hangs
			Set<String> toRemove = new HashSet<>();
			
			for (int k = 0; k < n; k++) {
				List<String> path = queue.poll();
				String word = path.get(path.size() - 1); //path.get(0)
				System.out.println("Path size: " + path.size() + " word " + word);		
				
				char[] chars = word.toCharArray();
				for (int i = 0; i < word.length(); i++) {
					char ch = chars[i];
					for (char c = 'a'; c <= 'z'; c++) {  //3. check a new word by change each character from a ...to z
						if (ch != c) {
							chars[i] = c;   // set first letter of chars to 'a','b',...'z' - Step 4.
							String nextWord = String.valueOf(chars);  // ait , bit,
							
							//[lot, log, dot, cog, hot, dog]
							if (words.contains(nextWord)) {  //nextWord = hot
								toRemove.add(nextWord);
						
								List<String> newPath = new ArrayList<>(path);
								System.out.println("newPath: " + newPath);
								newPath.add(nextWord);
								System.out.println("newPath after add: " + newPath);
								queue.offer(newPath);
								
								if (nextWord.equals(endWord)) {
									System.out.println("ADD to result: " + newPath);
									result.add(newPath);
								}
							}
						}
					}
					chars[i] = ch;
				}
			}
			if (!result.isEmpty()) {
				break;
            }
			if (!toRemove.isEmpty()) {
				words.removeAll(toRemove);
			}
		}

        return result;
    }
    
    
    public static void main (String args[]) {
	    
	    String beginWord = "hit";
	    String endWord = "cog";
	    List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");

	    /*Output:
		     ["hit","hot","dot","dog","cog"],
		     ["hit","hot","lot","log","cog"]
		*/		
	    				  	    
	    List<List<String>> res = findLadders(beginWord, endWord, wordList);
	    for (int i = 0; i < res.size(); i++) {
	    	System.out.println(res.get(i));
	    }
	    
	/*    List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log");
	    List<List<String>> res2 = findLadders(beginWord, endWord, wordList2);
	    System.out.println("Expected Empty " + res2);
	 */   
	    
    }
}
	