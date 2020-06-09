
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/* 
 * Find top number repeating / occur frequently
 *   int[] arr = { 1,2,2,2,3,3,4};
 *  
 *   Output = 2 . 2 has 3 times repeating
 * 
 *   
similar 347. Top K Frequent Elements (Medium) 
https://leetcode.com/problems/top-k-frequent-elements/

Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Explain: 1 has 3 frequency, 2 has 2 frequency

time complexity O(Nlog(k))  
Space complexity : O(N) to store the hash map

 */

public class TopFrequentNumber
{
	  
	//find top repeating 
	public static int findMaxRepeating( int[] arr) {
		// List<Integer> result = new ArrayList<>(); 
		 
	    if (arr == null || arr.length == 0) return 0;
	    
	    HashMap<Integer, Integer> map = new HashMap<>();
	    
	    for (int i = 0; i < arr.length; i++) {
	       map.put(arr[i], map.getOrDefault (arr[i], 0) + 1);
	    }
	    // map = {1=1, 2=3, 3=2, 4=1}
	    // sort value in ascending
    //    PriorityQueue<Map.Entry<Integer,Integer>> q = new PriorityQueue<>       
    //    								((a, b) -> b.getValue() - a.getValue()); // sort value in descending 
       
	    //compare value. if they equals, them compare key
        PriorityQueue<Integer> q = new PriorityQueue<Integer>( 
        		(w1, w2) -> map.get(w1).equals(map.get(w2)) ?
    		                     w1.compareTo(w2) :  map.get(w2) - map.get(w1) ); // sort value in descending      
     
        
	    //add all Entry to queue
        // q.addAll(map.entrySet()); // Queue is sorted [2=3, 1=1, 3=2, 4=1]
        // Map.Entry<Integer, Integer> entry = q.peek();  // [2= 3]
        // return entry.getKey(); 
        
        q.addAll(map.keySet()); //[2, 1, 3, 4]
        return q.peek();
  
	  }
		
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
	  
   public static void main(String args[])
   {
     
     int[] arr = { 1,1,2,2,2,3,3,3,4};
     System.out.println(findMaxRepeating(arr)); //2
     
     int[] input = {1,2,2,2,3,3,3};
     System.out.println("top elements: " + topKFrequent_PQ(input, 2));  //[2,3]
  
 
     
   }
}