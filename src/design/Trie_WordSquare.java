package design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 425. Word Squares
 * https://leetcode.com/problems/word-squares/

Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y


Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:


Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

 * 
 * //https://leetcode.com/problems/word-squares/discuss/91333/Explained.-My-Java-solution-using-Trie-126ms-1616


 better approach is to check the validity of the word square while we build it.
	Example: ["area","lead","wall","lady","ball"]

	We know that the sequence contains 4 words because the length of each word is 4.
	Every word can be the first word of the sequence, let's take "wall" for example.
	Which word could be the second word? Must be a word start with "a" (therefore "area"), because it has to match the second letter of word "wall".
	Which word could be the third word? Must be a word start with "le" (therefore "lead"), because it has to match the third letter of word "wall" and the third letter of word "area".
	What about the last word? Must be a word start with "lad" (therefore "lady"). For the same reason above.


Algorithm

We build upon the backtracking algorithm that we listed above, and tweak two parts.

In the first part, we add a new function buildTrie(words) to build a Trie out of the input words.

in the second part, in the function getWordsWithPrefix(prefix) we simply query the Trie to retrieve all the words that possess the given prefix.


 */
 
public class Trie_WordSquare {
	
	//Map and Trie
	private static class TrieNode {
        private final List<String> startsWith;
        private final Map<Character, TrieNode> children;
        
        public TrieNode() {
            startsWith = new ArrayList<>();
            children = new HashMap<>();
        }
    }
    
	class Trie {
	    private final TrieNode root;
	    
	    public Trie(String[] words) {
	        this.root = buildTrie(words);
	    }
	    
	    private TrieNode buildTrie(String[] words) {
	        TrieNode root = new TrieNode();
	        
	        for (String word : words) {   // add words to startsWith list
	        	root.startsWith.add(word);
	        }
	        
	        TrieNode curr; //create reference
	        for (String word : words) {
	            curr = root;
	            for (char c : word.toCharArray()) {
	                curr.children.putIfAbsent(c, new TrieNode());
	                curr = curr.children.get(c);
	                curr.startsWith.add(word);
	            }
	        }
	        
	        return root;
	    }
	    
	    public List<String> findWordsByPrefix(StringBuilder prefix) {
	        TrieNode curr = root;
	        
	        for (int i = 0; i < prefix.length(); i++) {
	        	
	            curr = curr.children.get(prefix.charAt(i));
	            if (curr == null) return new ArrayList<>();
	        }
	        
	        return curr.startsWith;
	    }
	} 
	
	public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) return new ArrayList<>();
        
        List<List<String>> result = new ArrayList<>();
        
        wordSquares(result, new Trie(words), new ArrayList<>());
        return result;
    }
    
    public void wordSquares(List<List<String>> result, Trie trie, List<String> currResult) {
        
    	if (currResult.size() > 0 && currResult.size() == currResult.get(0).length()) {
            result.add(new ArrayList<>(currResult));
            return;
        }
        
        // get prefix by getting the char for each word that's at the col of curr result size
        StringBuilder prefix = new StringBuilder(currResult.size());
        
        for (String word : currResult)
            prefix.append(word.charAt(currResult.size()));
        
        List<String> foundWords = trie.findWordsByPrefix(prefix);
        for (String word : foundWords) {
            currResult.add(word);
            
            wordSquares(result, trie, currResult);
            
            currResult.remove(currResult.size() - 1);
        }
    }
    
    public static void main(String[] args) {
    	String[] words = {"area","lead","wall","lady","ball"};
    	Trie_WordSquare obj = new Trie_WordSquare();
    	System.out.println(obj.wordSquares(words));
    }
  
   
}
