import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/* 
347. Top K Frequent Elements (Medium) 
https://leetcode.com/problems/top-k-frequent-elements/

Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Explain: 1 has 3 frequency, 2 has 2 frequency

Example 2:

Input: nums = [1], k = 1
Output: [1]


Similar, TopKWordsFrequent.java, SortCharactersByFrequency.java

time complexity O(Nlog(k))  
Space complexity : O(N) to store the hash map
 */

public class TopKFrequentElements
{
	//prefer use  PriorityQueue
	public static List<Integer> topKFrequent_PQ(int[] nums, int k) {
		List<Integer> result = new ArrayList<>(); // Space : O(k)
        if (nums == null || nums.length == 0 || k <= 0) return result;

        Map<Integer,Integer> map = new HashMap<>(); // /*count each number frequency*/
        for (int n : nums) // Time : O(n)
            map.put(n, map.getOrDefault(n, 0) + 1 ); // increase 1 if number exists , {1=3, 2=2, 3=1} 
        
        // sort value in ascending
        PriorityQueue<Map.Entry<Integer,Integer>> q = new PriorityQueue<>       
        ((a, b) -> b.getValue() - a.getValue()); // sort value in descending      
        
        q.addAll(map.entrySet());    //Queue is sorted : q = [1=3, 2=2, 3=1] 
       
       /*   for (Map.Entry<Integer,Integer> entry : map.entrySet()) {          
        	q.add(entry);           
        } */
       
        /** find k most frequent element */    
        for(int i = 0; i < k; i++){  // while (result.size() < k) {     // k = 2, print top 2 elements
        	Map.Entry<Integer, Integer> entry = q.poll(); //remove head of queue
            result.add(entry.getKey()); // add key 1, 2 to arraylist
        }
        return result;
    }
	
	//Collections.sort does not have better than nlogn time complexity.
	public static List<Integer> topKFrequent(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k <= 0) return null;

        Map<Integer,Integer> map = new HashMap<>(); // /*count each number frequency*/
        for (int n : nums) // Time : O(n)
            map.put(n, map.getOrDefault(n, 0) + 1 ); // increase 1 if number exists , {1=3, 2=2, 3=1} 
        
        //get list of allKey
        List<Integer> frequentList = new ArrayList<Integer>(map.keySet());
        
        // sort value in ascending by sort method
        Collections.sort(frequentList, (k1, k2) -> map.get(k1).equals(map.get(k2)) ?
    		            k1.compareTo(k2):  map.get(k2) - map.get(k1) ); // sort value in descending      
        
        
        return frequentList.subList(0,k);
    }
  
	  
   public static void main(String args[])
   {
     int[] input = {1,1,1,2,2,3};
     System.out.println("top elements: " + topKFrequent_PQ(input, 2));  //[1, 2]
     
     System.out.println("top elements2: " + topKFrequent(input,2));
   
     
   }
}