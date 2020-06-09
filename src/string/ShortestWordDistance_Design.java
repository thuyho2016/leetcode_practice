package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 244. Shortest Word Distance II
https://leetcode.com/problems/shortest-word-distance-ii/

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 
and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3

Input: word1 = "makes", word2 = "coding"
Output: 1

Time complexity is O(n^2)  since for every element of word1, we traverse the entire array in search for the closest occurrence of word2
Space complexity is O(1), since no additional space is used.

 Steps:
 Use Math.min and Math.abs
 */
class ShortestWordDistance_Design {

	   private Map<String, List<Integer>> map;
	    
	    public ShortestWordDistance_Design(String[] words) {
	        map = new HashMap<>();
	        
	        for (int i = 0; i < words.length; i++) {
	            map.putIfAbsent(words[i], new ArrayList<>());
	            map.get(words[i]).add(i); //index as value and if word is seen repeatly, add/append  the index to list of Integer
	        }                               //map = {coding=[3], practice=[0], perfect=[2], makes=[1,4]}
	    }
	   
	    public int shortest(String word1, String word2) {
	        List<Integer> l1 = map.get(word1);  //index of coding = 3
	        List<Integer> l2 = map.get(word2);  //index of practice = 0
	        
	        int min = Integer.MAX_VALUE;  // minDistance
	        int i = 0, j = 0;				// 2 pointers
	        
	        while (i < l1.size() && j < l2.size()) {  // when j = 1 < 1 , exit while loop
	        	int num1 = l1.get(i);
	        	int num2 = l2.get(j);
	            min = Math.min(min, Math.abs(num1 - num2)); //3 - 0 = 3
	            
	            if (num1 < num2) {
	                i++;
	            } else { // since 3 > 0 , increase j . 
	                j++;
	            }
	        }
	        
	        return min;
	    }


	public static void main(String[] args) {
	     String[] words  = {"practice", "makes", "perfect", "coding", "makes"} ;
     
	     ShortestWordDistance_Design obj = new ShortestWordDistance_Design(words); //obj = {coding=[3], practice=[0], perfect=[2], makes=[1, 4]}
	     
	     String word1 = "coding";
	     String word2 = "practice";
	     
	     int dis = obj.shortest(word1, word2);
	     System.out.println("Shortest Word Distance: " +  dis);//[null,3,1]
	}
}
	
	