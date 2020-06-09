package string;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
243. Shortest Word Distance
https://leetcode.com/problems/shortest-word-distance/
 
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3

Input: word1 = "makes", word2 = "coding"
Output: 1

The time complexity is O(n^2)  since for every element of word1, we traverse the entire array in search for the closest occurrence of word2
 Space complexity is O(1), since no additional space is used.
 
 
 */

public class ShortestWordDistance{
	//Approach: One=pass Math.min to choose the shortest difference indexes between 2 words
	// time complexity is O(n) and Space complexity is O(1) since no additional space is allocated.
	public static int shortestDistance2(String[] words, String word1, String word2) {
		if (words == null || words.length == 0) {
	        return 0;
	    }
		
	    int minDistance = words.length; //5
	    
	    int index1 = -1; 
	    int index2 = -1;
	    
	    for (int i = 0; i < words.length; ++i) {
	    	
	        if(words[i].equals(word1)){
	            index1 = i; // i = 3
	            
	        } else if(words[i].equals(word2)){
	             index2 = i; //i = 0
	        }
	        
	        if(index1 != -1 && index2 != -1){
	        	minDistance = Math.min(minDistance, Math.abs(index1 - index2));
	        }
	    }
	     return minDistance;
	}
		
	
	// Create a HashMap - key is word, Value is to store indexes of the word. 
	// Example, makes has 2 indexes 1 and 4, so  map = {makes=[1, 4]};
	// and find shortest distance indexes of word1 and word2

	//Time complexity : O(m + n)) considering there were N words in the original list.
	//Space complexity: O(1) 
	
	public static int shortestDistance(String[] words, String word1, String word2) {
        Map<String, List<Integer> > map = new HashMap<>();
        
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], new ArrayList<Integer>()); //{practice=[]}, {practice=[0], makes=[]}
            }
            map.get(words[i]).add(i);  //add indexes to list of value - {practice=[0]}
                                       //{coding=[3], practice=[0], perfect=[2], makes=[1, 4]}
        }
    
        List<Integer> l1 = map.get(word1);  // get index of coding = 3
        List<Integer> l2 = map.get(word2);  // get index of practice = 0
        
        int min = Integer.MAX_VALUE;
        int i = 0, j = 0;
        
        while (i < l1.size() && j < l2.size()) {   // when j = 1 < 1 , exit while loop
        	
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
	     String[] words  = {"practice", "makes", "perfect", "coding", "makes"};
	     String word1 = "coding";
	     String word2 = "practice";
	    
	     System.out.println("Shortest Word Distance: " +  shortestDistance(words, word1, word2));//3
	     System.out.println("Shortest Word Distance2: " +  shortestDistance2(words, word1, word2));//3
	     
	     
	     String[] words2 = {"makes", "coding"};
	     word1 = "coding";
	     word2 = "makes";
	     int dis = shortestDistance(words2, word1, word2);
	     System.out.println("Shortest Word Distance: " +  dis); //1
	     System.out.println("Shortest Word Distance2: " +  shortestDistance2(words2, word1, word2));//1
	      
	}
	
}
	
	