package design;
/*
 * 211. Add and Search Word - Data structure design ( Medium level)
https://leetcode.com/problems/add-and-search-word-data-structure-design/
	
Design a data structure that supports the following two operations:

void addWord(word)
boolean search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. 
A . means it can represent any one letter.

Example:

	addWord("bad")
	addWord("dad")
	addWord("mad")
	search("pad") -> false
	search("bad") -> true
	search(".ad") -> true
	search("b..") -> true

Time complexity : O(m)
Space complexity : O(1)

Same with Trie_InsertSearch.java
*/

import java.util.HashMap;
import java.util.Map;

public class Trie_AddSearchWord {
	
	class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;
        
        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }
    }

    private TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie_AddSearchWord() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        
        TrieNode cur = root;
        
        for (char c : word.toCharArray()) {
        	
            TrieNode next = cur.children.get(c);
            
            if (next == null) {
                next = new TrieNode();
                cur.children.put(c, next);
            }
            cur = next;
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, 0, root);
    }
    
    private boolean search(String word, int idx, TrieNode node) {
    	if (node == null) return false;
    	
    	if (idx == word.length()) {
            return node.isWord;
        }
    	
    	// 2 cases - if cur char is . or not .
        if (word.charAt(idx) == '.') {
            for (char c : node.children.keySet()) {
                if (search(word, idx + 1, node.children.get(c))) { // increase index + 1 
                    return true;
                }
            }
        }
        else {
            if (node.children.get(word.charAt(idx)) != null) {
                return search(word, idx + 1, node.children.get(word.charAt(idx)));
            }
        }
		return false;
    }
        
      
    public static void main(String args[])
    {
    	Trie_AddSearchWord tree = new Trie_AddSearchWord();
        tree.addWord("bad");
        tree.addWord("dad");     
    	tree.addWord("mad");   
    	
    	System.out.println(tree.search("pad"));     // return false
    	System.out.println(tree.search("bad"));   // true
    	System.out.println(tree.search(".ad"));   // true
    	System.out.println(tree.search("b.."));   //true
        
    }
}
