package string;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/* 
692. Top K Frequent Words (Medium) 
https://leetcode.com/problems/top-k-frequent-words/

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.


Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.

time complexity O(Nlog(k))  
Space complexity : O(N) to store the hash map

Same with TopKFrequentElements.java
 */

public class TopKWordsFrequent
{
	//prefer use PriorityQueue
	//Time Complexity: O(Nlogk), where NN is the length of words. We count the frequency of each word in O(N) time, 
	//then we add N words to the heap, each in O(logk) time
	//Space Complexity: O(N)
	public static List<String> topKFrequent_PQ(List<String> input, int k) {

        if (input == null || input.size() == 0 || k <= 0) return null;

        Map<String,Integer> map = new HashMap<>(); // to store count each word frequency
        for (String s : input) 
            map.put(s, map.getOrDefault(s, 0) + 1 ); // increase 1 if word exists , {1=3, 2=2, 3=1} 
        
        //if count (value) equals, then sort word (key) by alphabet 
        //compare two values by equals method. if two values are equals, then compare two keys by compareTo() 
        PriorityQueue<String> pq = new PriorityQueue<String>( 
        		(w1, w2) -> map.get(w1).equals(map.get(w2)) ?
    		                     w1.compareTo(w2) :  map.get(w2) - map.get(w1) ); // sort value in descending      
        		        // number count is equals, then compare key
       
        //map = {love=3, coding=1, i=3, leetcode=1}
        
        //Add keyset to to pq
       // for(String w : map.keySet()) pq.offer(w);
        
        pq.addAll( map.keySet()); //[i, coding, love, leetcode]
        
        List<String> result = new ArrayList<String>(); 
        for ( int i = 0; i < k; i++) {
        	 String cur = pq.poll(); //after i is removed, love is the head of queue [love, coding, leetcode]
        	 System.out.println(cur);
        	 result.add(cur);
        }
        return result;
    }
	
	public static List<String> topKFrequent(List<String> input, int k) {

        if (input == null || input.size() == 0 || k <= 0) return null;

        Map<String,Integer> map = new HashMap<>(); // to store count each word frequency
        for (String s : input) 
            map.put(s, map.getOrDefault(s, 0) + 1 ); // increase 1 if word exists , {1=3, 2=2, 3=1} 
        
 
        //add keyset to ArrayList
        List<String> frequentList = new ArrayList<String>(map.keySet());
        
        // sort value in ascending by sort method. if count equals, then sort word by alphabet 
        //compare two values by equals. if two values are equals, then compare two keys by compareTo() 
        
        Collections.sort(frequentList, (k1, k2) -> map.get(k1).equals(map.get(k2)) ?
    		                                          k1.compareTo(k2) :  map.get(k2) - map.get(k1) ); // sort value in descending      
        		        // number count is equals, then compare key
        
        return frequentList.subList(0,k);
    }
  

	
   public static void main(String args[])
   {
     List<String> input = Arrays.asList("i", "love", "i", "leetcode", "i", "love", "coding", "love");
     System.out.println("top elements: " + topKFrequent(input, 2));  //["i", "love"]  i three times, love
 
     System.out.println("top elements: " + topKFrequent_PQ(input, 2));  //["i", "love"]  i three times, love
   }
}