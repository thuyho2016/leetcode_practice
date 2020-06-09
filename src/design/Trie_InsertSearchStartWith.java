package design;
import java.util.HashMap;

/*
 * 208. Implement Trie (Prefix Tree) ( Medium level)
https://leetcode.com/problems/implement-trie-prefix-tree/
	
Implement a trie with insert, search, and startsWith methods.

	Example:

	Trie trie = new Trie();

	trie.insert("apple");
	trie.search("apple");   // returns true
	trie.search("app");     // returns false
	trie.startsWith("app"); // returns true
	trie.insert("app");   
	trie.search("app");     // returns true

NOTE:
Trie supports search, insert and delete operations in O(L) time where L is length of key.
we can easily print all words in alphabetical order which is not easily possible with hashing.
We can efficiently do prefix search with Trie
Disadvantage of tries is that they need lot of memory for storing the strings

Time complexity : O(m)

Space complexity : O(1)

Same with Trie_AddSearchWord.java
*/

public class Trie_InsertSearchStartWith {
	
	class TrieNode {
		
		HashMap<Character, TrieNode> children;
		boolean isWord;
		
		public TrieNode() {
			children = new HashMap<Character, TrieNode>();
			isWord = false;
		}
	}
	
	private TrieNode root;
	
	/** Initialize your data structure here. */
    public Trie_InsertSearchStartWith() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
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
         cur.isWord = true;;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = find(word);
        return cur == null ? false : cur.isWord;
    }
    
    private TrieNode find(String word) {
    	TrieNode p = root;
    	for (char c : word.toCharArray()) {
    		 
    		TrieNode child = p.children.get(c);   
    	    if (child == null) return null;
    	    else p = child;
    	
    	}
    	return p;
    }
    
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	
        TrieNode cur = find(prefix);
        return cur == null ? false : (cur.isWord || cur.children.keySet().size() > 0 );
    }
    
    public static void main(String args[])
    {
        Trie_InsertSearchStartWith tree = new Trie_InsertSearchStartWith();
        
        tree.insert("apple");
        System.out.println(tree.search("apple"));   // returns true
        System.out.println(tree.search("app"));     // returns false
    	System.out.println( tree.startsWith("app")); // returns true
    	tree.insert("app");   
    	System.out.println(tree.search("app"));     // return true
        
    }
}
