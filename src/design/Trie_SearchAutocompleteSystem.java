package design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 642. Design Search Autocomplete System
 * https://leetcode.com/problems/design-search-autocomplete-system/
 * 
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). 
 * For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. 
	Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). 
Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.

 
Example:

Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times

Now, the user begins another search:
Operation: input('i')

Output: ["i love you", "island","i love leetcode"]

Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". 
Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 */

//Trie for search, PQ for match, hashMap for counting

public class Trie_SearchAutocompleteSystem {
	private class TrieNode {
        Map<Character, TrieNode> next = new HashMap<>();
        List<String> words = new ArrayList<>();
    }
    
    private TrieNode root;
    private final int N = 3;
    private Map<String, Integer> cnts = new HashMap<>();
    private TrieNode curNode;
    private StringBuilder sb = new StringBuilder();
    
    public Trie_SearchAutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        curNode = root;  // need this to avoid java.lang.NullPointerException
       
        for (int i = 0; i < sentences.length; ++i) {
            insert(sentences[i], times[i]);
        }
    }
    
    //dfs - build Trie
    private void insert(String word, int cnt) {
        TrieNode cur = root;
        
        cnts.put(word, cnts.getOrDefault(word, 0) + cnt);  //{i love you=5, iroman=2, island=3, i love leetcode=2}
        for (char c : word.toCharArray()) {
            
        	insert(word, cnt, cur);
            cur.next.putIfAbsent(c, new TrieNode()); //if ( cur.next.get(c) == null)) cur.next.put(c, new TrieNode());
            cur = cur.next.get(c);
        }
        insert(word, cnt, cur);
    }
    
    //find top 3 from current node 
    private void insert(String word, int cnt, TrieNode cur) {
        
    	if (!cur.words.contains(word)) {
            cur.words.add(word);
        }
        Collections.sort(cur.words, (a, b) ->  cnts.get(a).equals(cnts.get(b)) ?
        		a.compareTo(b) : cnts.get(b) - cnts.get(a) );
        
        if (cur.words.size() > N) {
            cur.words.remove(cur.words.size() - 1);
        }
    }
    
    
    public List<String> input(char c) {
        if (c != '#') {
            sb.append(c);
            curNode.next.putIfAbsent(c, new TrieNode());
            curNode = curNode.next.get(c);
            return curNode.words;
        }
        
        String text = sb.toString();
        insert(text, 1);
        curNode = root;
        return new ArrayList<String>();
    }
    
    public static void main(String[] args) { 
    	
    	String[] sentences =new String[] {"i love you","island","iroman","i love leetcode"};
    	int[] times = {5,3,2,2};
    	
    	Trie_SearchAutocompleteSystem a = new Trie_SearchAutocompleteSystem(sentences, times);
    	
    	// operation - "i"," ","a","#" 
    	List<String> list = a.input('i');
    	System.out.println(list);    // ["i love you", "island","i love leetcode"]
    	
    	//List<String> list2 = a.input('');
    //	System.out.println(list2);   //["i love you","i love leetcode"]
    	
    	List<String> list3 = a.input('a');
    	System.out.println("Empty: " + list3);  // empty
    	
    	List<String> list4 = a.input('#');
    	System.out.println("Finished: " +list4); 
    	
    }
}
