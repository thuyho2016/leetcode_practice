
package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * 1268. Search Suggestions System
 * https://leetcode.com/problems/search-suggestions-system/
 * 
Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return list of lists of the suggested products after each character of searchWord is typed. 

 

Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]


Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]

Example 3:

Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]

Example 4:

Input: products = ["havana"], searchWord = "tatiana"
Output: [[],[],[],[],[],[],[]]


Hint: Use Trie data structure to store the best three matching. Traverse the Trie.

We can keep list of atmost 3 words at each character node with prefix. 
This way we don't have to traverse all the nodes to find words with prefix.

Solution:

First we use the array of products to build a Trie where each node represents a character in the prefix tree.
Each TrieNode stores the list of String that has the same prefix from root node (exclusive because root node is just a ficticious helper node) to the current node (inclusive).

The list of String also has a maximum size constraint of 3. To ensure that the product contained in the list are lexigraphically minimal, 
the input product array is sorted so that the product are inserted into the list in lexigraphical order until the list reaches size of 3.

The rest is just building the prefix from the search word character by character and insert the list of the Trie node into the result.

One caveat is that when the prefix doesn't have a corresponding Trie node, we need to mark it as ended and just insert empty list for the remaining characters of the searchWord.

 */
public class WordSearchSuggestion_Trie {
	//Optimmize solution is Trie
	class TrieNode {
        Map<Character, TrieNode> map; // children;
        List<String> words;
        
        TrieNode() {
        	 map = new HashMap<>();
        	 words = new ArrayList<>();
        }
    }


	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> answer = new ArrayList<>();
		
		Arrays.sort(products);
		TrieNode root = new TrieNode();
        
		 for (String product : products) {
           
            TrieNode cur = root;
            
            //add to TrieNode
            for(char c: product.toCharArray()){
            	
            	if(!cur.map.containsKey(c)) {
            		cur.map.put(c, new TrieNode());
            	}
            	
            	cur = cur.map.get(c);
            	if (cur.words.size() < 3) {
            		cur.words.add(product);
            	}
            }
        }    

  
      //search top 3 from current node 
        TrieNode cur = root;
        boolean isEnd = false;
        
        for (char c : searchWord.toCharArray()) { // c = m, o, u, s, e
            if(!cur.map.containsKey(c)){  
               isEnd = true;
            }
            
            if (isEnd) {
            	answer.add(new ArrayList<>());
            } else { // if map contains char, add whole list of words to answer
           
                cur = cur.map.get(c);  //c = m, o --> cur.words = [mobile, moneypot, monitor], c = u,s e --> cur.words = [mouse, mousepad]
                answer.add(cur.words); //[[mobile, moneypot, monitor], [mobile, moneypot, monitor]]; c = u, cur.words = [mouse, mousepad], this will be added to answer
            }
        }    
        
        return answer; //[[mobile, moneypot, monitor], [mobile, moneypot, monitor], [mouse, mousepad], [mouse, mousepad], [mouse, mousepad]]
    }

	//simple solution
	public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        Arrays.sort(products);
        int start=0;
        List<List<String>> res= new ArrayList<>();
        
        for(int i=1;i <=searchWord.length();i++){
            int count=0;
            ArrayList<String> temp = new ArrayList<String>();
            
            for(int j=start; j<products.length;j++){
                if(products[j].startsWith(searchWord.substring(0,i))){
                    count++;
                    temp.add(products[j]);
                    
                    if(count == 1){
                        start = j;
                    }
                    if(count==3){
                        break;
                    }
                }
            }
            res.add(temp);
        }
        return res;
    }
	
	public static void main (String args[]) {
		WordSearchSuggestion_Trie o = new  WordSearchSuggestion_Trie();
	    String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};  
	    String searchWord = "mouse";
	    
	    List<List<String>> out = o.suggestedProducts(products, searchWord);
	    System.out.println( out);
	}
	
}
